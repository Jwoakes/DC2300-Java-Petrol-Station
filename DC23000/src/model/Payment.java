package model;

public class Payment {

	//amount of fuel bought
	private double amountFuelPurchased;
	//amount spent in shop
	private double shopMoney;
	
/**
 * Constructor to create a Payment for a customer
 * @param amountFuelPurchased Amount of fuel the customer has purchased
 * @param amountSpent Amount of money the customer has spent in the shop
 */
	public Payment(double amountFuelPurchased, double shopMoney) {
		this.amountFuelPurchased = amountFuelPurchased;
		this.shopMoney = shopMoney;
	}	
	
	public double getAmountFuelPurchased() {
		return amountFuelPurchased;
	}

	public double getShopSpend() {
		return shopMoney;
	}
}
