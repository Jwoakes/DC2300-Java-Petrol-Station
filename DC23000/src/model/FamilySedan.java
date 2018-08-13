package model;
/**
 * 
 * Class to represent a Family Sedan.
 * 
 * 
 * 
 * @author Jake Woakes
 * 
 */

public class FamilySedan extends Vehicle {

	/**
	 * The size of the Family Sedan vehicle
	 */
	private static final double SEDAN_SIZE = 1.5;
	/**
	 * The minimum fuel capacity for a Family Sedan vehicle
	 */
	private static final int SEDAN_MIN_CAPACITY = 12;
	/**
	 * The maximum fuel capacity for a Family Sedan vehicle
	 */
	private static final int SEDAN_MAX_CAPACITY = 18;
	/**
	 * The probability that the customer will enter the shop
	 */
	private static final double SEDAN_SHOP_PROBABILITY = 0.4;
	/**
	 * The number of ticks before the customer will not visit the Shop
	 */
	private static final int SEDAN_TICKS_BEFORE_SHOP = 60;
	/**
	 * The minimum number of ticks the customer will spend in the Shop
	 */
	private static final int SEDAN_MIN_SHOP_TICKS = 12;
	/**
	 * The maximum number of ticks the customer will spend in the Shop
	 */
	private static final int SEDAN_MAX_SHOP_TICKS = 30;
	/**
	 * The minimum amount of money a customer will spend in the Shop
	 */
	private static final double SEDAN_MIN_SHOP_SPEND = 8;
	/**
	 * The maximum amount of money a customer will spend in the Shop
	 */
	private static final double SEDAN_MAX_SHOP_SPEND = 16;

	/**
	 * Constructor to build a Family Sedan
	 */
	public FamilySedan() {

		super(SEDAN_SIZE, SEDAN_MIN_CAPACITY, SEDAN_MAX_CAPACITY, SEDAN_SHOP_PROBABILITY, SEDAN_TICKS_BEFORE_SHOP, SEDAN_MIN_SHOP_TICKS, SEDAN_MAX_SHOP_TICKS, SEDAN_MIN_SHOP_SPEND, SEDAN_MAX_SHOP_SPEND);

	}

}