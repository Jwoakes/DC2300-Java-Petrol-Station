package model;

import java.util.UUID;
import interfaces.ItemsInQueue;

/**
 * A class to represent a Customer in the Petrol Station
 * @author Jake Woakes
 *
 */
public class Customer implements ItemsInQueue {

	/**
	 * Whether the customer will enter the shop
	 */
	private boolean enterShop;
	/**
	 * The number of ticks the Customer will shop for
	 */
	private int shopTicks;
	/**
	 * The total amount of fuel the Customer purchased
	 */
	private double amountFuelPurchased;
	/**
	 * The amount of money the Customer will spend in the Shop
	 */
	private double shopSpend;
	/**
	 * The number of ticks it will take for a Customer to pay
	 */
	private int customerPayTicks;
	/**
	 * A unique registration assigned to a Customer Vehicle
	 */
	private UUID registration;
	/**
	 * The minimum number of ticks it takes for a Customer to pay
	 */
	public static final int MINIMUM_PAY_TICKS = 12;
	/**
	 * The maximum number of ticks it takes for a Customer to pay
	 */
	public static final int MAXIMUM_PAY_TICKS = 18;
	/**
	 * Whether the Customer has paid for their fuel
	 */
	private boolean hasPaid = false;

	/**
	 * Constructor to build a Customer
	 * @param shopTicks number of ticks the Customer will shop for
	 * @param amountFuelPurchased total amount of fuel the Customer purchased
	 * @param shopSpend amount of money the Customer will spend in the Shop
	 * @param enterShop whether the customer will enter the shop
	 * @param customerPayTicks number of ticks it will take for a Customer to pay
	 * @param registration unique registration assigned to a Customer Vehicle
	 */
	public Customer(int shopTicks, double amountFuelPurchased, double shopSpend, Boolean enterShop,
			int customerPayTicks, UUID registration) {
		this.amountFuelPurchased = amountFuelPurchased;
		this.shopSpend = shopSpend;
		this.shopTicks = shopTicks;
		this.enterShop = enterShop;
		this.customerPayTicks = customerPayTicks;
		this.registration = registration;

	}
	
	/**
	 * Calculates the Customers total to pay
	 * @return the cost of customers payment
	 */
	public Payment customerPayment() {
			double shopMoney = shopSpend;
			double fuel = amountFuelPurchased;
			shopSpend = 0;
			amountFuelPurchased = 0;
			hasPaid = true;
			return new Payment(fuel, shopMoney);
	}

	/**
	 * Access the unique registration of a Customer Vehicle
	 * @return the unique code
	 */
	public UUID getRegistration() {
		return registration;
	}
	
	/**
	 * Access whether the Customer has paid
	 * @return true or false
	 */
	public Boolean getHasPaid() {
		return hasPaid;
	}

	/**
	 * Access whether the Customer has entered the shop
	 * @return true or false
	 */
	public Boolean getEnterShop() {
		return enterShop;
	}

	/**
	 * Access the number of ticks the Customer is in the shop for
	 * @return the number of ticks
	 */
	public int getNumShopTicks() {
		return shopTicks;
	}

	/**
	 * Reduce the number of ticks taken for a Customer to pay
	 * @return the new value of customerPayTicks
	 */
	public int reduceCustomerPayTicks() {
		return customerPayTicks--;
	}

	/**
	 * Reduce the number of ticks taken for a Customer in Shop
	 * @return the new value of numShopTicks
	 */
	public int reduceNumShopTicks() {
		return shopTicks--;
	}

	/**
	 * The customer has filled their car and they are not entering the shop
	 */
	public void finishShopping() {
		enterShop = false;
	}

	/**
	 * Access the amount of money the Customer has spent in the shop
	 * @return the number spent
	 */
	public double getShopSpend() {
		return shopSpend;
	}
	
	/**
	 * Access the number of ticks taken to pay
	 * @return the number of ticks
	 */
	public int getCustomerPayTicks() {
		return customerPayTicks;
	}

	/**
	 * Access the amount of money a Customer has spent
	 * @return the amount of money spent
	 */
	public double getCustomerSpend() {
		return shopSpend;
	}

}
