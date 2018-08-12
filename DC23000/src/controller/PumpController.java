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
	 */
	public List<Vehicle> dequeueAllFullyPaid() {
		List<Vehicle> complete = new LinkedList<Vehicle>();
		for (int i = 0; i < pumps.length; i++) {
			Vehicle v = pumps[i].dequeueWhenFullyPaid();
			if (v != null) {
				complete.add(v);
			}
		}
		return complete;
	}

	/**
	 * Progress time This will alert each Pump that time has passed
	 * @return 
	 * 
	 * @throws VehicleNotFullException
	 * @throws VehicleAlreadyPaidException
	 * @throws VehicleIsNotOccupiedException
	 * @throws CustomerHasNotPaidException
	 * @throws CustomerPresentException
	 * @throws CustomerCarMismatchException
	 * @throws CustomerCouldNotFindVehicleException
	 */
	public List<Customer> tick() {
		ArrayList<Customer> ready = new ArrayList<Customer>();
		for (int i = 0; i < pumps.length; i++) {
			Customer customer = pumps[i].tick();
			if (customer != null) {
				ready.add(customer);
			}
		}
		return ready;
	}

	/**
	 * Getter for the array of Pumps
	 * 
	 * @return
	 */
	public Pump[] getPumps() {
		return this.pumps;
	}

	public void recieveCustomer(Customer customer) {
		boolean found = false;
		for (Pump p : pumps) {
			if (p.getQueue().peek() != null && p.getQueue().peek().getRegistration() == customer.getRegistration()) {
				p.getQueue().peek().customerBackInVehicle(customer);
				found = true;
				break;
			}
		}
	}
}