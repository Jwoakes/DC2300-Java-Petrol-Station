package model;

/**
 * Class to represent a truck
 * 
 * @author Jake Woakes
 *
 */
public class SmallCar extends Vehicle {

	/**
	 * The size of the Small Car vehicle
	 */
	private static final int SMALL_CAR_SIZE = 1;
	/**
	 * The minimum fuel capacity for a Small Car vehicle
	 */
	private static final int SMALL_CAR_MIN_CAPACITY = 7;
	/**
	 * The maximum fuel capacity for a Small Car vehicle
	 */
	private static final int SMALL_CAR_MAX_CAPACITY = 9;
	/**
	 * The probability that the customer will enter the shop
	 */
	private static final double SMALL_CAR_SHOP_PROBABILITY = 0.3;
	/**
	 * The number of ticks before the customer will not visit the Shop
	 */
	private static final int SMALL_CAR_TICKS_BEFORE_SHOP = 30;
	/**
	 * The minimum number of ticks the customer will spend in the Shop
	 */
	private static final int SMALL_CAR_MIN_SHOP_TICKS = 12;
	/**
	 * The maximum number of ticks the customer will spend in the Shop
	 */
	private static final int SMALL_CAR_MAX_SHOP_TICKS = 24;
	/**
	 * The minimum amount of money a customer will spend in the Shop
	 */
	private static final double SMALL_CAR_MIN_SHOP_SPEND = 5;
	/**
	 * The maximum amount of money a customer will spend in the Shop
	 */
	private static final double SMALL_CAR_MAX_SHOP_SPEND = 10;

	/**
	 * Constructor to build a Small Car
	 */
	public SmallCar() {
		super(SMALL_CAR_SIZE, SMALL_CAR_MIN_CAPACITY, SMALL_CAR_MAX_CAPACITY, SMALL_CAR_SHOP_PROBABILITY,
				SMALL_CAR_TICKS_BEFORE_SHOP, SMALL_CAR_MIN_SHOP_TICKS, SMALL_CAR_MAX_SHOP_TICKS,
				SMALL_CAR_MIN_SHOP_SPEND, SMALL_CAR_MAX_SHOP_SPEND);
	}

}
