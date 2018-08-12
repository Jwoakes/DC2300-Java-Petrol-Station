package model;

import java.util.Iterator;

import model.Vehicle;
import utilities.CircularArrayQueue;
import controller.PumpController;

/**
 * A fuel Pump
 * 
 * @author Jake Woakes
 *
 */
public class Pump {

	/**
	 * The capacity of the queue for the Pump
	 */
	public final static double VEHICLE_PUMP_CAPACITY = 3;
	/**
	 * The speed at which fuel is dispensed
	 */
	public final static double FUEL_RATE = 1;
	/**
	 * Space unused in the queue at pump
	 */
	private double freeSpace;
	/**
	 * The queue of Vehicles for the Pump
	 */
	private CircularArrayQueue<Vehicle> queue;

	/**
	 * Constructor for a Pump
	 */
	public Pump(double smallestVehicleSize) {
		this.freeSpace = VEHICLE_PUMP_CAPACITY;
		this.queue = new CircularArrayQueue<Vehicle>(VEHICLE_PUMP_CAPACITY);
	}
	
	/**
	 * Takes a vehicle and adds it to the pump queue
	 * 
	 * @param v
	 * @return boolean
	 */
	public boolean enqueue(Vehicle v) {
		if (queue.add(v)) {
			freeSpace -= v.getSize();
			return true;
		}
		return false;
	}

	/**
	 * try to fill the next vehicle in the queue
	 * 
	 * @return boolean
	 */
	public boolean fill() {
		if (queue.iterator().hasNext()) {
			return queue.peek().attemptVehicleFill(FUEL_RATE);
		}
		else {
			return false;
		}
	}

	/**
	 * Progress time at the pump This will alert the Vehicle at the front of the
	 * queue that time has passed
	 * 
	 * @throws VehicleNotFullException
	 * @throws VehicleAlreadyPaidException
	 * @throws VehicleIsNotOccupiedException
	 */
	public Customer tick() {
		Iterator<Vehicle> i = queue.iterator();
		while (i.hasNext()) {
			i.next().increaseTicks();
		}
		if(!fill()) {
			if (queue.iterator().hasNext() && queue.peek().getIsOccupied()) {
				return queue.peek().exitVehicle();
			}
		}
		return null;
	}
	
	/**
	 * Getter for space unused
	 * 
	 * @return
	 */
	public double getSpaceUnused() {
		return freeSpace;
	}

	/**
	 * Getter for the queue for the Pump
	 * 
	 * @return
	 */
	public CircularArrayQueue<Vehicle> getQueue() {
		return this.queue;
	}

	/**
	 * Getter for queue size
	 * 
	 * @return
	 */
	public double getQueueSize() {
		return queue.getSize();
	}

	/**
	 * loop through vehicles in the queue and check if the 
	 * vehicle has paid, if so then remove from the queue
	 * 
	 * @return Vehicle
	 */
	public Vehicle dequeueWhenFullyPaid() {
		Vehicle v = queue.peek();
		if (v != null && v.getHasPaid() && v.getIsOccupied()) {
			v = queue.remove();
			freeSpace = VEHICLE_PUMP_CAPACITY - queue.getSize();
			return v;
		}
		return null;
	}
}
