package model;
/**
 * Class to represent a truck
 * @author Jake Woakes
 *
 */
public class SmallCar extends Vehicle {
	
	private static final int SMALL_CAR_SIZE = 1;
	private static final int SMALL_CAR_MIN_CAPACITY = 7;
	private static final int SMALL_CAR_MAX_CAPACITY = 9;
	private static final double SMALL_CAR_SHOP_PROBABILITY = 0.3;
	private static final int SMALL_CAR_TICKS_BEFORE_SHOP = 30;
	private static final int SMALL_CAR_MIN_SHOP_TICKS = 12;
	private static final int SMALL_CAR_MAX_SHOP_TICKS = 24;
	private static final double SMALL_CAR_MIN_SHOP_SPEND = 5;
	private static final double SMALL_CAR_MAX_SHOP_SPEND = 10;
	
	public SmallCar() {
		super(SMALL_CAR_SIZE, SMALL_CAR_MIN_CAPACITY, SMALL_CAR_MAX_CAPACITY, SMALL_CAR_SHOP_PROBABILITY, SMALL_CAR_TICKS_BEFORE_SHOP, SMALL_CAR_MIN_SHOP_TICKS, SMALL_CAR_MAX_SHOP_TICKS, SMALL_CAR_MIN_SHOP_SPEND, SMALL_CAR_MAX_SHOP_SPEND);
	}

}
