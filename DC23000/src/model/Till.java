package model;

import utilities.CircularArrayQueue;

/**
 * A Till
 * 
 * @author Jake Woakes
 *
 */
public class Till {

	/**
	 * A queue of Customers for the Till
	 */
	private CircularArrayQueue<Customer> queue;

	/**
	 * Constructor for a Till
	 */
	public Till(int maxQueueSize) {
		queue = new CircularArrayQueue<Customer>(maxQueueSize);
	}

	public void enqueue(Customer c) {
		if (!queue.add(c)) {
			System.out.println("Till Full");
		}
	}

	/**
	 * Collect a Payment from the Customer at the front of the queue
	 * 
	 * @return
	 * @throws HasPaidException
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

	public Customer dequeueWhenDone() {
		if (queue.isEmpty() || !queue.peek().getHasPaid()) {
			return null;
		}
		return queue.remove();
	}

	/**
	 * Getter for the queue of Customers
	 * 
	 * @return
	 */
	public CircularArrayQueue<Customer> getQueue() {
		return this.queue;
	}

	public double getQueueSize() {
		return queue.getSize();
	}

}