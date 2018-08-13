package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Customer;
import model.Pump;
import model.Vehicle;

/**
 * A controller for any number of Pumps
 * 
 * @author Jake Woakes
 *
 */
public class PumpController {

	/**
	 * An array containing all Pumps belonging to the Controller
	 */
	private Pump[] pumps;

	/**
	 * Constructor for a Pump Controller
	 * 
	 * @param numPumps
	 */
	public PumpController(int numPumps, double smallestVehicle) {
		pumps = new Pump[numPumps];
		for (int i = 0; i < numPumps; i++) {
			pumps[i] = new Pump(smallestVehicle);
		}
	}

	/**
	 * Give a Vehicle to the Pump with the shortest queue
	 * 
	 * @param v
	 * @return
	 */
	public Boolean enqueue(Vehicle vehicle) {
		int shortestQueueIndex = 0;
		for (int i = 0; i < pumps.length; i++) {
			if (pumps[i].getQueueSize() < pumps[shortestQueueIndex].getQueueSize()) {
				shortestQueueIndex = i;
			}
		}
		return pumps[shortestQueueIndex].enqueue(vehicle);
	}

	/**
	 * Remove all Vehicles that have paid for fuel and any Shop purchases
	 * 
	 * @return
	 */
	public List<Vehicle> dequeueAllFullyPaid() {
		List<Vehicle> finished = new LinkedList<Vehicle>();
		for (int i = 0; i < pumps.length; i++) {
			Vehicle vehicle = pumps[i].dequeueWhenFullyPaid();
			if (vehicle != null) {
				finished.add(vehicle);
			}
		}
		return finished;
	}

	/**
	 * Progress time This will alert each Pump that time has passed
	 * 
	 * @return available
	 */
	public List<Customer> tick() {
		ArrayList<Customer> available = new ArrayList<Customer>();
		for (int i = 0; i < pumps.length; i++) {
			Customer customer = pumps[i].tick();
			if (customer != null) {
				available.add(customer);
			}
		}
		return available;
	}

	/**
	 * Getter for the array of Pumps
	 * 
	 * @return
	 */
	public Pump[] getPumps() {
		return this.pumps;
	}

	/**
	 * 
	 * @param customer
	 */
	public void recieveCustomer(Customer customer) {
		boolean valid = false;
		for (Pump pump : pumps) {
			if (pump.getQueue().peek() != null
					&& pump.getQueue().peek().getRegistration() == customer.getRegistration()) {
				pump.getQueue().peek().customerBackInVehicle(customer);
				valid = true;
				break;
			}
		}
	}
}