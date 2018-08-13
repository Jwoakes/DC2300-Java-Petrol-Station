package controller;

import java.util.LinkedList;
import java.util.List;

import model.Customer;
import model.Payment;
import model.Till;

/**
 * A controller for any number of Tills
 * 
 * @author Jake Woakes
 */
public class TillController {

	/**
	 * An array containing all the Tills belonging to the controller
	 */
	private Till[] tills;

	/**
	 * Constructor to build a Till Controller
	 * 
	 * @param numTills
	 */
	public TillController(int numTills, int maxQueueSize) {
		tills = new Till[numTills];
		for (int i = 0; i < numTills; i++) {
			tills[i] = new Till(maxQueueSize);
		}
	}

	/**
	 * Pass Customer to the Till with the shortest queue
	 * 
	 * @param customer
	 *            The customer at the till
	 */

	public void enqueue(Customer customer) {
		int shortestQueueIndex = 0;
		for (int i = 0; i < tills.length; i++) {
			if (tills[i].getQueueSize() < tills[shortestQueueIndex].getQueueSize()) {
				shortestQueueIndex = i;
			}
		}
		tills[shortestQueueIndex].enqueue(customer);
	}

	/**
	 * Add each customer to the List
	 * 
	 * @param customers
	 *            The customers at the till
	 */
	public void enqueue(List<Customer> customers) {
		for (Customer customer : customers) {
			enqueue(customer);
		}
	}

	/**
	 * Remove Customer at the front of queue if paid
	 * 
	 * @return List of paid customers
	 */
	public List<Customer> dequeueFullyPaid() {
		LinkedList<Customer> completedCustomers = new LinkedList<Customer>();
		for (Till till : tills) {
			Customer customer = till.dequeueWhenDone();
			if (customer != null) {
				completedCustomers.add(customer);
			}
		}
		return completedCustomers;
	}

	/**
	 * Collect payments from Ready Customers
	 * 
	 * @return the list of payments
	 */
	public List<Payment> collectPayments() {
		LinkedList<Payment> payments = new LinkedList<Payment>();
		for (Till t : tills) {
			Payment p = t.collectPayment();
			if (p != null) {
				payments.add(p);
			}
		}
		return payments;
	}

	/**
	 * Access the array of Tills
	 * 
	 * @return the configured tills at the petrol station
	 */
	public Till[] getTills() {
		return this.tills;
	}
}