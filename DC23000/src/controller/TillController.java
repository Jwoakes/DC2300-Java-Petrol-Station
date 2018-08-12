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
 *
 */
public class TillController {

	/**
	 * An array containing all the Tills belonging to the controller
	 */
	private Till[] tills;

	/**
	 * Constructor for a Till Controller
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
	 * Give a Customer to the Till with the shortest queue
	 * 
	 * @throws TillFullException
	 * @throws CustomerPresentException
	 */
	public void enqueue(Customer customer){
		int shortestQueueIndex = 0;
		for (int i = 0; i < tills.length; i++) {
			if (tills[i].getQueueSize() < tills[shortestQueueIndex].getQueueSize()) {
				shortestQueueIndex = i;
			}
		}
		tills[shortestQueueIndex].enqueue(customer);
	}

	public void enqueue(List<Customer> customers) {
		for (Customer customer : customers) {
			enqueue(customer);
		}
	}

	/**
	 * Remove the Customer at the front of each Tills queue if they have already
	 * paid
	 */
	public List<Customer> dequeueFullyPaid() {
		LinkedList<Customer> completedCustomers = new LinkedList<Customer>();
		for (Till t : tills) {
			Customer c = t.dequeueWhenDone();
			if (c != null) {
				completedCustomers.add(c);
			}
		}
		return completedCustomers;
	}

	/**
	 * Collect payments from the Customers at the front of each Tills queue if they
	 * are ready to pay
	 * 
	 * @return
	 * @throws HasPaidException
	 * @throws NoCustomerPresentException
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
	 * Getter for the array of Tills
	 * 
	 * @return
	 */
	public Till[] getTills() {
		return this.tills;
	}

}