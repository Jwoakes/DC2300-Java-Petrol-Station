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
	 * The maximum queue size of an individual Till
	 */
	public final static int MAX_QUEUE_SIZE = 5;

	/**
	 * The minimum size of a vehicle
	 */
	public final static double SMALLEST_VEHICLE = 0.75;
	/**
	 * The initial value of variable t
	 */
	private static final double INITIAL_T = 0.02;
	/**
	 * The Petrol Station model
	 */
	private PetrolStation station;
	/**
	 * The probability that a Small Car or Motorbike will arrive at the Petrol
	 * Station
	 */
	private final double p;
	/**
	 * The probability that a Family Sedan will arrive at the Petrol Station
	 */
	private final double q;
	/**
	 * The probability that a Truck will arrive at the Petrol Station
	 */
	private double t;
	/**
	 * A value to represent if the Petrol Station allows Trucks
	 */
	private boolean trucksAllowed;
	/**
	 * A Random Number Generator to spawn vehicles
	 */
	public static Random random = new Random();

	/**
	 * Initialize the Petrol Station
	 * 
	 * @param p
	 *            Probability that a Small Car or Motorbike will arrive
	 * @param q
	 *            Probability that a Family Sedan will arrive
	 * @param trucksAllowed
	 *            Whether trucks are allowed
	 * @param numPumps
	 *            The number of Pumps
	 * @param numTills
	 *            The number of Tills
	 */
	public StationSimulator(double p, double q, boolean trucksAllowed, int numPumps, int numTills) {

		this.p = p;
		this.q = q;
		this.t = INITIAL_T;
		this.trucksAllowed = trucksAllowed;
		this.station = new PetrolStation(numPumps, numTills, SMALLEST_VEHICLE, MAX_QUEUE_SIZE);
	}

	/**
	 * Execute the simulation for X amount of ticks
	 */
	public void runSimulation(int ticks) {
		for (int i = 0; i < ticks; i++) {
			station.tick(rollForVehicle());
		}
	}

	/**
	 * Generates a random number and will spawn a new Vehicle or Null
	 * 
	 * @return null or a Vehicle
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
	 * Access the Model Petrol Station
	 * 
	 * @return the petrol station
	 */
	public PetrolStation getStation() {
		return this.station;
	}

	/**
	 * Builds up a String to display variables about the Petrol Station
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

	/**
	 * Main Method (Tris you need to implement your GUI)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	private static void launch(String[] args) {

	}

}