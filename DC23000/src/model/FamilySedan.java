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

	private static final double SEDAN_SIZE = 1.5;
	private static final int SEDAN_MIN_CAPACITY = 12;
	private static final int SEDAN_MAX_CAPACITY = 18;
	private static final double SEDAN_SHOP_PROBABILITY = 0.4;
	private static final int SEDAN_TICKS_BEFORE_SHOP = 60;
	private static final int SEDAN_MIN_SHOP_TICKS = 12;
	private static final int SEDAN_MAX_SHOP_TICKS = 30;
	private static final double SEDAN_MIN_SHOP_SPEND = 8;
	private static final double SEDAN_MAX_SHOP_SPEND = 16;

	public FamilySedan() {

		super(SEDAN_SIZE, SEDAN_MIN_CAPACITY, SEDAN_MAX_CAPACITY, SEDAN_SHOP_PROBABILITY, SEDAN_TICKS_BEFORE_SHOP, SEDAN_MIN_SHOP_TICKS, SEDAN_MAX_SHOP_TICKS, SEDAN_MIN_SHOP_SPEND, SEDAN_MAX_SHOP_SPEND);

	}

}