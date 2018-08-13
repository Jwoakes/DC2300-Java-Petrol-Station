package model;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * A class to represent the Shop at the Petrol Station
 * 
 * @author Jake Woakes
 */
public class Shop {

	/**
	 * All customers stored in the shop
	 */
	private ObservableSet<Customer> allCustomers;

	/**
	 * Constructor to build the shop
	 */
	public Shop() {
		this.allCustomers = FXCollections.observableSet();
	}

	/**
	 * Add a Customer to the Shop
	 */
	public boolean add(Customer c) {
		if (c.getEnterShop()) {
			return true;
		} else
			return false;
	}

	/**
	 * Remove a Customer from the Shop
	 * 
	 * @return the list without the customer in
	 */
	public List<Customer> remove(List<Customer> finishedShopping) {
		List<Customer> removed = new LinkedList<Customer>();
		for (Customer customer : finishedShopping) {
			if (allCustomers.remove(customer)) {
				removed.add(customer);
			}
		}
		return removed;
	}

	/**
	 * Reduce the shopping timer by 1
	 * 
	 * @return customers finished shopping
	 */
	public List<Customer> reduceAllTimers() {
		List<Customer> finishedShopping = new LinkedList<Customer>();
		for (Customer customer : allCustomers) {
			if (customer.reduceCustomerPayTicks() < 1) {
				finishedShopping.add(customer);
				customer.finishShopping();
			}
		}
		return finishedShopping;
	}

	/**
	 * Progress time
	 * 
	 * @return
	 */
	public List<Customer> tick() {
		return remove(reduceAllTimers());
	}

	/**
	 * Access the Customers in the Shop
	 * 
	 * @return the customers in the shop
	 */
	public ObservableSet<Customer> getContents() {
		return this.allCustomers;
	}
}