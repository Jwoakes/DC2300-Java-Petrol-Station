package model;
/**
 * Vehicle class represents a vehicle that you will find at the petrol station
 * A vehicle is defined by it's size and tank
 * @author Jake Woakes
 *
 */
public class Vehicle {

	private int tank;

	private double size;

	/**
	 * Unit of space vehicle occupies
	 * @param size
	 */
	public Vehicle(double size) {
		this.size = size;
	}
	
	/**
	 * Retrieve's the size of a vehicle
	 * @return double
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Sets the size of a vehicle
	 * @param size
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * Retrieve's the size of the vehicle's tank
	 * @return
	 */
	public int getTank() {
		return tank;
	}

	/**
	 * Sets the tank size for the vehicle
	 * @param tank
	 */
	public void setTank(int tank) {
		this.tank = tank;
	}

}
