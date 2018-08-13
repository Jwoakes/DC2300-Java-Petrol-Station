package model;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Class to represent a truck
 * 
 * @author Jake Woakes
 *
 */
public class Truck extends Vehicle {

	/**
	 * The size of the Small Car vehicle
	 */
	private static final double TRUCK_SIZE = 2;
	/**
	 * The minimum fuel capacity for a Small Car vehicle
	 */
	private static final int TRUCK_MIN_FUEL = 30;
	/**
	 * The maximum fuel capacity for a Small Car vehicle
	 */
	private static final int TRUCK_MAX_FUEL = 40;
	/**
	 * The minimum amount of money a customer will spend in the Shop
	 */
	private static final int TRUCK_MIN_SHOP_SPEND = 15;
	/**
	 * The maximum amount of money a customer will spend in the Shop
	 */
	private static final int TRUCK_MAX_SHOP_SPEND = 20;
	/**
	 * The probability that the customer will enter the shop
	 */
	private static final int TRUCK_SHOP_PROBABILITY = 1;
	/**
	 * The number of ticks before the customer will not visit the Shop
	 */
	private static final int TRUCK_TICKS_BEFORE_SHOP = 48;
	/**
	 * The minimum number of ticks the customer will spend in the Shop
	 */
	private static final int TRUCK_MIN_SHOP_TICKS = 24;
	/**
	 * The maximum number of ticks the customer will spend in the Shop
	 */
	private static final int TRUCK_MAX_SHOP_TICKS = 36;
	/**
	 * Customer is happy enough to visit the shop
	 */
	private SimpleBooleanProperty happyToShop;

	/**
	 * Constructor to build the Truck Vehicle
	 */
	public Truck() {
		super(TRUCK_SIZE, TRUCK_MIN_FUEL, TRUCK_MAX_FUEL, TRUCK_SHOP_PROBABILITY, TRUCK_TICKS_BEFORE_SHOP,
				TRUCK_MIN_SHOP_TICKS, TRUCK_MAX_SHOP_TICKS, TRUCK_MIN_SHOP_SPEND, TRUCK_MAX_SHOP_SPEND);
	}

	/**
	 * Access whether the truck driver was happy enough to visit the shop
	 * 
	 * @return whether they were happy enough
	 */
	public SimpleBooleanProperty getHappyToShop() {
		return happyToShop;
	}

	/**
	 * Whether the truck driver was happy enough to visit the shop
	 */
	public void decideToShop() {
		super.decideToShop();
		if (getDidCustomerShop() == false) {
			happyToShop.set(false);
		}
	}
}
