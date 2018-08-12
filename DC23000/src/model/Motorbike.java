package model;
/**
 * 
 * Class to represent a Motorbike.
 * 
 * 
 * 
 * @author Jake Woakes
 * 
 */

public class Motorbike extends Vehicle {

	private static final double MOTORBIKE_SIZE = 0.75;
	private static final int MOTORBIKE_MIN_CAPACITY = 5;
	private static final int MOTORBIKE_MAX_CAPACITY = 5;
	private static final int MOTORBIKE_TICKS_BEFORE_SHOP = 0;
	private static final int MOTORBIKE_SHOP_PROBABILITY = 0;
	private static final int MOTORBIKE_MIN_SHOP_TICKS = 0;
	private static final int MOTORBIKE_MAX_SHOP_TICKS = 0;
	private static final int MOTORBIKE_MIN_SHOP_SPEND = 0;
	private static final int MOTORBIKE_MAX_SHOP_SPEND = 0;

	public Motorbike() {
		super(MOTORBIKE_SIZE, MOTORBIKE_MIN_CAPACITY, MOTORBIKE_MAX_CAPACITY, MOTORBIKE_SHOP_PROBABILITY, MOTORBIKE_TICKS_BEFORE_SHOP, MOTORBIKE_MIN_SHOP_TICKS, MOTORBIKE_MAX_SHOP_TICKS, MOTORBIKE_MIN_SHOP_SPEND, MOTORBIKE_MAX_SHOP_SPEND);
	}

}