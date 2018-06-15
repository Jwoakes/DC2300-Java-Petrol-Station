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

}
