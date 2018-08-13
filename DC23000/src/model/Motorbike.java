package model;
/**
 * 
 * Class to represent a Motorbike.
 * @author Jake Woakes
 * 
 */

public class Motorbike extends Vehicle {

	/**
	 * The size of the Motorbike vehicle
	 */
	private static final double MOTORBIKE_SIZE = 0.75;
	/**
	 * The minimum fuel capacity for a Motorbike vehicle
	 */
	private static final int MOTORBIKE_MIN_CAPACITY = 5;
	/**
	 * The maximum fuel capacity for a Motorbike vehicle
	 */
	private static final int MOTORBIKE_MAX_CAPACITY = 5;
	/**
	 * The number of ticks before the customer will not visit the Shop
	 */
	private static final int MOTORBIKE_TICKS_BEFORE_SHOP = 0;
	/**
	 * The probability that the customer will enter the shop
	 */
	private static final int MOTORBIKE_SHOP_PROBABILITY = 0;
	/**
	 * The minimum number of ticks the customer will spend in the Shop
	 */
	private static final int MOTORBIKE_MIN_SHOP_TICKS = 0;
	/**
	 * The maximum number of ticks the customer will spend in the Shop
	 */
	private static final int MOTORBIKE_MAX_SHOP_TICKS = 0;
	/**
	 * The minimum amount of money a customer will spend in the Shop
	 */
	private static final int MOTORBIKE_MIN_SHOP_SPEND = 0;
	/**
	 * The maximum amount of money a customer will spend in the Shop
	 */
	private static final int MOTORBIKE_MAX_SHOP_SPEND = 0;

	/**
	 * Constructor to build a Motorbike
	 */
	public Motorbike() {
		super(MOTORBIKE_SIZE, MOTORBIKE_MIN_CAPACITY, MOTORBIKE_MAX_CAPACITY, MOTORBIKE_SHOP_PROBABILITY, MOTORBIKE_TICKS_BEFORE_SHOP, MOTORBIKE_MIN_SHOP_TICKS, MOTORBIKE_MAX_SHOP_TICKS, MOTORBIKE_MIN_SHOP_SPEND, MOTORBIKE_MAX_SHOP_SPEND);
	}

}