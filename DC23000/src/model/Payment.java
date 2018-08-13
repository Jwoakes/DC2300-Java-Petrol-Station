package model;

/**
 * A class to create the Payment for a Petrol Station
 * @author Jake Woakes
 *
 */
public class Payment {

	/**
	 * The amount of fuel purchased by a Customer
	 */
	private double amountFuelPurchased;
	/**
	 * The amount of money spent in the shop
	 */
	private double shopMoney;
	
	/**
	 * Constructor to create a Payment for a customer
	 * 
	 * @param amountFuelPurchased
	 *            Amount of fuel the customer has purchased
	 * @param amountSpent
	 *            Amount of money the customer has spent in the shop
	 */
	public Payment(double amountFuelPurchased, double shopMoney) {
		this.amountFuelPurchased = amountFuelPurchased;
		this.shopMoney = shopMoney;
	}

	/**
	 * Access the amount of money a Customer has spent in the shop
	 * @return the amount of money spent
	 */
	public double getShopSpend() {
		return shopMoney;
	}
	
	/**
	 * Access the amount of fuel a Customer has purchased
	 * @return the amount of fuel
	 */
	public double getAmountFuelPurchased() {
		return amountFuelPurchased;
	}
}
