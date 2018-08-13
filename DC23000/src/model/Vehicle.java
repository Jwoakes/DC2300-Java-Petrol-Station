package model;

import java.util.UUID;

import controller.StationSimulator;
import interfaces.ItemsInQueue;
/**
 * A class to represent a vehicle at a Petrol Station
 * @author Jake Woakes
 */
public class Vehicle implements ItemsInQueue {

	/**
	 * The size of the vehicle's tank
	 */
	private final int tankSize;
	/**
	 * The size of the vehicle
	 */
	private final double vehicleSize;
	/**
	 * The amount of money spent in the shop
	 */
	private final double shopSpend;
	/**
	 * The number of ticks if the customer shops
	 */
	private final int numShoppingTicks;
	/**
	 * The probability the customer will shop if fueled in a certain time
	 */
	private final double shopProbability;
	/**
	 * The time taken to complete payment at the till
	 */
	private final int payTicks;
	/**
	 * Amount of ticks passed before the customer decides not to shop
	 */
	private final double ticksBeforeNoShop;
	/**
	 * The amount of fuel currently in a vehicle
	 */
	private double currentFuel;
	/**
	 * Whether the vehicle's tank is full or not
	 */
	private Boolean isFuelFull = false;
	/**
	 * Whether the driver has paid for their fuel or not
	 */
	private Boolean hasPaid = false;
	/**
	 * Whether the driver is inside of their vehicle
	 */
	private Boolean isVehicleOccupied = true;
	/**
	 * A unique registration code for a Vehicle
	 */
	private UUID registration;
	/**
	 * The number of ticks that the vehicle has spent at the petrol station
	 */
	private int ticksSinceArrival = 0;
	/**
	 * Whether the customer entered the shop or not
	 */
	private boolean didCustomerShop;

	/**
	 * A constructor to build a Vehicle
	 * @param vehicleSize The size of the vehicle
	 * @param minCapacity The minimum capacity of a Vehicle fuel tank
	 * @param maxCapacity The maximum capacity of a Vehicle fuel tank
	 * @param shopProbability The probability a Customer will enter the shop
	 * @param ticksBeforeNoShop The time before the driver will not enter the shop
	 * @param minShopTicks The minimum time the Customer will spend in the shop
	 * @param maxShopTicks The maximum time the Customer will spend in the shop
	 * @param minShopSpend The minimum amount the Customer will spend in the shop
	 * @param maxShopSpend The maximum amount the Customer will spend in the shop
	 */
	public Vehicle(double vehicleSize, int minCapacity, int maxCapacity, double shopProbability, int ticksBeforeNoShop, int minShopTicks, int maxShopTicks, double minShopSpend, double maxShopSpend) {
		this.vehicleSize = vehicleSize;
		this.registration = UUID.randomUUID();
		this.currentFuel = 0;
		this.shopProbability = shopProbability;
		this.ticksBeforeNoShop = ticksBeforeNoShop;
		this.tankSize = (int) Math.round(randomValue(minCapacity, maxCapacity));
		this.numShoppingTicks = (int) Math.round(randomValue(minShopTicks, maxShopTicks));
		this.shopSpend = (double) Math.round(randomValue(minShopSpend, maxShopSpend) * 100) / 100;
		this.payTicks = (int) Math.round(randomValue(Customer.MINIMUM_PAY_TICKS, Customer.MAXIMUM_PAY_TICKS));
	}
	
	/**
	 * Create a random value for the use within a vehicle
	 * @param minValue smallest value 
	 * @param maxValue largest value
	 * @return
	 */
	private double randomValue(double minValue, double maxValue) {
		double capacityDifference = maxValue - minValue;
		if (capacityDifference < 0)
			return maxValue;
		else
			return (StationSimulator.random.nextDouble() * (maxValue - minValue)) + minValue;
	}
	
	/**
	 * Attempt to fill the Vehicle
	 * @param amountToFill The number of gallons to fuel the Vehicle
	 * @return whether the vehicle was filled or not
	 */
	public Boolean attemptVehicleFill(double amountToFill) {
		if (!isFuelFull) {
			currentFuel += amountToFill;
			if (currentFuel >= tankSize) {
				isFuelFull = true;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Access whether the Vehicle has paid
	 * @return whether the Vehicle has paid
	 */
	public boolean getHasPaid() {
		return hasPaid;
	}
	
	/**
	 * Access the size of a Vehicle
	 * @return the size of the Vehicle
	 */
	public double getSize() {
		return vehicleSize;
	}

	/**
	 * Access the size of a Vehicle's tank
	 * @return the size of a Vehicle's tank
	 */
	public int getTankSize() {
		return tankSize;
	}

	/**
	 * Access the unique registration code of a vehicle
	 * @return the unique registration code
	 */
	public UUID getRegistration() {
		return registration;
	}

	/**
	 * Increase the number of ticks since vehicle arrived at the Petrol Station
	 */
	public void increaseTicks() {
		this.ticksSinceArrival++;
	}

	/**
	 * Access whether the customer is inside of their vehicle
	 * @return whether the customer is inside their vehicle
	 */
	public Boolean getIsOccupied() {
		return isVehicleOccupied;
	}

	/**
	 * Mutator to set the vehicle as occupied
	 * @param isVehicleOccupied 
	 */
	private void setisVehicleOccupied(Boolean isVehicleOccupied) {
		this.isVehicleOccupied = isVehicleOccupied;
	}
	
	/**
	 * Access whether the Customer entered the shop
	 * @return whether the customer entered the shop
	 */
	public boolean getDidCustomerShop() {
		return didCustomerShop;
	}
	
	/**
	 * Access the total shop spend by a Customer
	 * @return the total shop spend
	 */
	public double getShopSpend() {
		return shopSpend;
	}
	
	/**
	 * The customer will enter the shop depending upon number of ticks since arrival
	 */
	public void decideToShop() {
		if (ticksSinceArrival <= ticksBeforeNoShop) {
			didCustomerShop = StationSimulator.random.nextDouble() < shopProbability;
		} else {
			didCustomerShop = false;
		}
	}
	
	/**
	 * The customer exit's their Vehicle and enters the Shop
	 * @return Customer in the shop
	 */
	public Customer exitVehicle() {
			isVehicleOccupied = false;
			decideToShop();
			int changeShopTicks = numShoppingTicks;
			double changeShopSpend = shopSpend;
			if (!didCustomerShop) {
				changeShopTicks = 0;
				changeShopSpend = 0;
			}
			return new Customer(changeShopTicks, changeShopSpend, currentFuel, didCustomerShop, payTicks, registration);
		}
	
	/**
	 * The Customer has finished shopping and is now back inside their Vehicle
	 * @param customer A customer at the Petrol Station
	 */
	public void customerBackInVehicle(Customer customer) {
		if (customer.getRegistration().equals(registration)) {
			if (customer.getHasPaid()) {
				hasPaid = customer.getHasPaid();
				isVehicleOccupied = true;
			} else {
				setisVehicleOccupied(true);

			}

		}
	}
}
