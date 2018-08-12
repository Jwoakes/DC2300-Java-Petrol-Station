package model;

import java.util.UUID;

import controller.StationSimulator;
/**
 * Vehicle class represents a vehicle that you will find at the petrol station
 * A vehicle is defined by it's size and tank
 * @author Jake Woakes
 *
 */
public class Vehicle {

	//size of vehicle tank
	private final int tankSize;
	
	//size of the vehicle
	private final double vehicleSize;
	
	//money spent in the shop
	private final double shopSpend;
	
	//number of ticks if they shop
	private final int numShoppingTicks;

	//probability they will shop if fueled in a certain time
	private final double shopProbability;
	
	// time taken to complete payment at till
	private final int payTicks;

	//if they do not fuel fast enough they do not shop
	private final double ticksBeforeNoShop;

	// amount of fuel in vehicle
	private double currentFuel;

	//vehicle fuel is full
	private Boolean isFuelFull = false;

	//driver has paid for fuel
	private Boolean hasPaid = false;

	//is driver in vehicle?
	private Boolean isVehicleOccupied = true;

	//unique registration of the vehicle
	private UUID registration;

	// number of ticks vehicle has spent at station
	private int ticksSinceArrival = 0;
	
	private boolean didCustomerShop;


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
	
	private double randomValue(double minValue, double maxValue) {
		double capacityDifference = maxValue - minValue;
		if (capacityDifference < 0)
			return maxValue;
		else
			return (StationSimulator.random.nextDouble() * (maxValue - minValue)) + minValue;
	}
	
	/**
	 * Attempt to fill the Vehicle
	 * 
	 * @param amountToFill The number of gallons to fuel the Vehicle
	 * @return
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
	
	public boolean getHasPaid() {
		return hasPaid;
	}
	
	/**
	 * Retrieve's the size of a vehicle
	 * @return double
	 */
	public double getSize() {
		return vehicleSize;
	}

	
	public int getTankSize() {
		return tankSize;
	}


	public UUID getRegistration() {
		return registration;
	}

	/**
	 * Increase the number of ticks since arrival
	 */
	public void increaseTicks() {
		this.ticksSinceArrival++;
	}

	public Boolean getIsOccupied() {
		return isVehicleOccupied;
	}

	private void setisVehicleOccupied(Boolean isVehicleOccupied) {
		this.isVehicleOccupied = isVehicleOccupied;
	}
	
	public boolean getDidCustomerShop() {
		return didCustomerShop;
	}
	/**
	 * the customer will shop depending upon the number of ticks since arriving
	 */
	public void decideToShop() {
		if (ticksSinceArrival <= ticksBeforeNoShop) {
			didCustomerShop = StationSimulator.random.nextDouble() < shopProbability;
		} else {
			didCustomerShop = false;
		}
	}
	
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
	
	public void customerBackInVehicle(Customer cust) {
		if (cust.getRegistration().equals(registration)) {
			if (cust.getHasPaid()) {
				hasPaid = cust.getHasPaid();
				isVehicleOccupied = true;
			} else {
				setisVehicleOccupied(true);

			}

		}
	}
}
