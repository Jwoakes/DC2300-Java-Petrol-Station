package model;
import java.util.List;

/**
 * 
 * Petrol Station class
 * 
 * @author Jake Woakes
 * 
 */

public class PetrolStation {

	private List<Pump> pumps;
	
	/**
	 * The number of ticks that have passed at this Petrol Station
	 */
	private int numTicksPassed = 0;

	private List<Till> tills;

	/**
	 * 
	 * @return List of pumps.
	 * 
	 */

	public List<Pump> getPumps() {

		return pumps;

	}

	/**
	 * 
	 * Sets the pumps of the petrol station.
	 * 
	 * 
	 * @param pumps List
	 * 
	 */

	public void setPumps(List<Pump> pumps) {

		this.pumps = pumps;

	}

	/**
	 * 
	 * @return List of tills
	 * 
	 */

	public List<Till> getTills() {

		return tills;

	}

	/**
	 * 
	 * Sets the tills of the petrol station.
	 * 	 
	 * @param tills List
	 * 
	 */

	public void setTills(List<Till> tills) {

		this.tills = tills;

	}

	/**
	 * Increase Petrol Station time by 1 tick
	 */
	public void tick() {
		
	}
	
	/**
	 * Returns the number of ticks that have passed
	 */
	public int getTicks() {
		return this.numTicksPassed;
	}
	
}
