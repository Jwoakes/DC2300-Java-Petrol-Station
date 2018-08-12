package model;

import java.util.UUID;

public class Customer {

	public static final int MAXIMUM_PAY_TICKS = 0;
	public static final int MINIMUM_PAY_TICKS = 0;

	// will the customer visit the shop
	private Boolean enterShop;
	// has the customer has paid for their fuel
	private Boolean hasPaid = false;
	// amount of fuel the customer purchased
	private double amountFuelPurchased;
	// number of ticks the customer will shop for
	private int shopTicks;
	// amount of money the customer will spend in the shop
	private double shopSpend;
	// number of ticks this Customer will take to pay
	private int customerPayTicks;
	// customer registration number
	private UUID registration;


	public Customer(int shopTicks, double amountFuelPurchased, double shopSpend, Boolean enterShop,
			int customerPayTicks, UUID registration) {
		this.amountFuelPurchased = amountFuelPurchased;
		this.shopSpend = shopSpend;
		this.shopTicks = shopTicks;
		this.enterShop = enterShop;
		this.customerPayTicks = customerPayTicks;
		this.registration = registration;

	}
	
	public Payment customerPayment() {
		if (hasPaid) {
			reduceCustomerPayTicks();
			return null;
		} else {
			double shopMoney = shopSpend;
			double fuel = amountFuelPurchased;
			shopSpend = 0;
			amountFuelPurchased = 0;
			hasPaid = true;
			return new Payment(fuel, shopMoney);
		}
	}

	public UUID getRegistration() {
		return registration;
	}
	
	public Boolean getHasPaid() {
		return hasPaid;
	}

	public Boolean getEnterShop() {
		return enterShop;
	}

	public int getNumShopTicks() {
		return shopTicks;
	}
	
	public void finishShopping() {
		enterShop = false;
	}

	public double getShopSpend() {
		return shopSpend;
	}
	
	public int getCustomerPayTicks() {
		return customerPayTicks;
	}
	
	public double getCustomerSpend() {
		return shopSpend;
	}

	public int reduceCustomerPayTicks() {
		return customerPayTicks--;
	}

	public int reduceNumShopTicks() {
		return shopTicks--;
	}

}
