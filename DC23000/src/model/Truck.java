package model;

import javafx.beans.property.SimpleBooleanProperty;

/**
 * Class to represent a truck
 * @author Jake Woakes
 *
 */
public class Truck extends Vehicle {
	

	private static final double TRUCK_SIZE = 2;
	private static final int TRUCK_MIN_FUEL = 30;
	private static final int TRUCK_MAX_FUEL = 40;
	private static final int TRUCK_MIN_SHOP_SPEND = 15;
	private static final int TRUCK_MAX_SHOP_SPEND = 20;
	private static final int TRUCK_SHOP_PROBABILITY = 1;
	private static final int TRUCK_TICKS_BEFORE_SHOP = 48;
	private static final int TRUCK_MIN_SHOP_TICKS = 24;
	private static final int TRUCK_MAX_SHOP_TICKS = 36;
	private SimpleBooleanProperty happyToShop;

	
	public Truck() {
		super(TRUCK_SIZE, TRUCK_MIN_FUEL, TRUCK_MAX_FUEL, TRUCK_SHOP_PROBABILITY, TRUCK_TICKS_BEFORE_SHOP, TRUCK_MIN_SHOP_TICKS, TRUCK_MAX_SHOP_TICKS, TRUCK_MIN_SHOP_SPEND, TRUCK_MAX_SHOP_SPEND);
	}
	
	public void decideToShop() {
		super.decideToShop();
		if (getDidCustomerShop() == false) {
			happyToShop.set(false);
		}
	}
	
	public SimpleBooleanProperty getHappyToShop() {
		return happyToShop;
	}
}
