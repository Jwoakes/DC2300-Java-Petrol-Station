package controller;

import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import model.FamilySedan;
import model.Motorbike;
import model.PetrolStation;
import model.SmallCar;
import model.Truck;
import model.Vehicle;

/**
 * A Simulator for a Petrol Station
 * 
 * @author Jake Woakes
 *
 */
public class StationSimulator {

	/**
	 * A constant to store the maximum queue size for a single till
	 */
	public final static int MAX_QUEUE_SIZE = 5;

	/**
	 * A constant to store the minimum size of a vehicle
	 */
	public final static double SMALLEST_VEHICLE = 0.75;
	/**
	 * A constant to store the initial value of variable t
	 */
	private static final double INITIAL_T = 0.02;
	/**
	 * The Petrol Station model
	 */
	private PetrolStation station;
	/**
	 * The probability that a Small Car or Motorbike will arrive
	 */
	private final double p;
	/**
	 * The probability that a Family Sedan will arrive
	 */
	private final double q;
	/**
	 * The probability that a Truck will arrive
	 */
	private double t;
	/**
	 * A value to represent if the Petrol Station allows Trucks
	 */
	private boolean trucksAllowed;
	/**
	 * A Randomiser for deciding on if a Vehicle will spawn and which type it will
	 * be
	 */
	public static Random random = new Random();

	/**
	 * Initializing a simulator will initialize a model Petrol Station and a User
	 * Interface It will prompt the User Interface to gather the user chosen
	 * variables
	 */
	public StationSimulator(double p, double q, boolean trucksAllowed, int numPumps, int numTills) {

		this.p = p;
		this.q = q;
		this.t = INITIAL_T;
		this.trucksAllowed = trucksAllowed;
		this.station = new PetrolStation(numPumps, numTills, SMALLEST_VEHICLE, MAX_QUEUE_SIZE);
	}

	/**
	 * Run the simulation for a given number of ticks
	 */
	public void runSimulation(int ticks) {
		for (int i = 0; i < ticks; i++) {
			station.tick(rollForVehicle());
		}
	}

	/**
	 * Generates a random number and based on the result compared to the chances of
	 * Vehicles spawning will either return a new Vehicle of the appropriate type or
	 * null
	 * 
	 * @return Either null or a Vehicle
	 * @throws MinGreaterThanMaxException
	 */
	private Vehicle rollForVehicle() {
		double chance = random.nextDouble();

		if (chance <= p) {
			return new SmallCar();
		} else if (chance <= 2 * p) {
			return new Motorbike();
		} else if (chance <= (2 * p) + q) {
			return new FamilySedan();
		} else if (trucksAllowed && chance <= (2 * p) + q + t) {
			Truck truck = new Truck();
			truck.getHappyToShop().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (!newValue) {
						t *= 0.8;
					} else if (t <= INITIAL_T) {
						t *= 1.05;
					}
				}
			});

			return truck;
		} else {
			return null;
		}
	}

	/**
	 * Getter for the Simulators model Petrol Station
	 * 
	 * @return
	 */
	public PetrolStation getStation() {
		return this.station;
	}

	public void flush() {
		for (int i = 0; i < 50; i++) {
			station.tick(null);
		}
	}

	/**
	 * 
	 */
	public String toString() {
		StringBuilder output = new StringBuilder();
		int ticks = station.getTicks().intValue();
		output.append("Simulation run for " + ticks);
		output.append(" ticks (" + ticks * 10 + " sec)\r\n");
		output.append("Probabilities : ");
		output.append("(Small Vehicles " + p + ") ");
		output.append("(Family Sedans " + q + ") ");
		output.append("(Trucks final " + (trucksAllowed ? t : "Restricted"));
		output.append(")\r\n");
		output.append("Income : ");
		double gallons = station.getGallonsSold().doubleValue();
		double shopIncome = station.getShopIncome().doubleValue();
		double lostGallons = station.getLostGallons().doubleValue();
		double lostShopIncome = station.getLostShopIncome().doubleValue();
		output.append("(" + gallons + " gallons sold) ");
		output.append("(£" + shopIncome + " in shop sales) ");
		output.append("(" + lostGallons + " gallons turned away) ");
		output.append("(£" + lostShopIncome + " potential income lost) ");
		output.append("\r\n\r\n");
		return output.toString();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private static void launch(String[] args) {
		
	}

}