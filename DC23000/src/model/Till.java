package model;

import utilities.CircularArrayQueue;

/**
 * A Class to represent a Till at a Petrol Station
 * @author Jake Woakes
 */
public class Till {

	/**
	 * A queue of Customers for the Till
	 */
	private CircularArrayQueue<Customer> queue;

	/**
	 * Constructor to build a Till
	 */
	public Till(int maxQueueSize) {
		queue = new CircularArrayQueue<Customer>(maxQueueSize);
	}

	/**
	 * Add customer to queue
	 * @param customer A Customer at the till
	 */
	public void enqueue(Customer customer) {
		if (!queue.add(customer)) {
			System.out.println("Till Full");
		}
	}

	/**
	 * Take payment from the front of the queue
	 * @return
	 */
	public Payment collectPayment() {
		if (queue.peek() != null) {
			return queue.peek().customerPayment();
		}
		return null;
	}
	
	/**
	 * If the queue is empty or first customer hasn't paid 
	 * don't return anything, if those conditions are not met
	 * call remove() on the queue
	 * 
	 * @return Customer
	 */

	/**
	 * If customer has finished paying remove them from queue
	 * @return removed customer
	 */
	public Customer dequeueWhenDone() {
		if (queue.isEmpty() || !queue.peek().getHasPaid()) {
			return null;
		}
		return queue.remove();
	}

	/**
	 * Access the queue of Customers
	 * @return the customers in the queue
	 */
	public CircularArrayQueue<Customer> getQueue() {
		return this.queue;
	}

	/**
	 * Access the size of the queue
	 * @return the size of the queue
	 */
	public double getQueueSize() {
		return queue.getSize();
	}

}