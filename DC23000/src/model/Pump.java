package model;

import java.util.Iterator;

import model.Vehicle;
import utilities.CircularArrayQueue;
import controller.PumpController;

/**
 * A Class to represent a Pump at a Petrol Station
 * 
 * @author Jake Woakes
 */
public class Pump {

	/**
	 * The capacity of the queue for the Pump
	 */
	public final static double VEHICLE_PUMP_CAPACITY = 3;
	/**
	 * The speed at which Fuel is dispensed
	 */
	public final static double FUEL_RATE = 1;
	/**
	 * Space unused in the queue at Pump
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
	 * Adds a vehicle to the pump queue
	 * 
	 * @param vehicle
	 *            a vehicle at the petrol station
	 * @return whether vehicle was added
	 */
	public boolean enqueue(Vehicle vehicle) {
		if (queue.add(vehicle)) {
			freeSpace -= vehicle.getSize();
			return true;
		}
		return false;
	}

	/**
	 * Attempt to fill the next vehicle in the queue
	 * 
	 * @return boolean whether it was filled or not
	 */
	public boolean fill() {
		if (queue.iterator().hasNext()) {
			return queue.peek().attemptVehicleFill(FUEL_RATE);
		} else {
			return false;
		}
	}

	/**
	 * Progress ticks at the pump
	 */
	public Customer tick() {
		Iterator<Vehicle> i = queue.iterator();
		while (i.hasNext()) {
			i.next().increaseTicks();
		}
		if (!fill()) {
			if (queue.iterator().hasNext() && queue.peek().getIsOccupied()) {
				return queue.peek().exitVehicle();
			}
		}
		return null;
	}

	/**
	 * Access the free space in the queue
	 * 
	 * @return the amount of space free
	 */
	public double getSpaceUnused() {
		return freeSpace;
	}

	/**
	 * Access the queue for the Pump
	 * 
	 * @return the queue
	 */
	public CircularArrayQueue<Vehicle> getQueue() {
		return this.queue;
	}

	/**
	 * Access the size of the queue
	 * 
	 * @return the size of the queue
	 */
	public double getQueueSize() {
		return queue.getSize();
	}

	/**
	 * Check if the vehicle has paid and remove them if they have
	 * 
	 * @return Vehicle
	 */
	public Vehicle dequeueWhenFullyPaid() {
		Vehicle vehicle = queue.peek();
		if (vehicle != null && vehicle.getHasPaid() && vehicle.getIsOccupied()) {
			vehicle = queue.remove();
			freeSpace = VEHICLE_PUMP_CAPACITY - queue.getSize();
			return vehicle;
		}
		return null;
	}
}
