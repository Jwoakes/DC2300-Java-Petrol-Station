import java.util.List;

public class PetrolStation {
	
	private List<Pump> pumps;
	private List<Till> tills;

	public List<Pump> getPumps() {
		return pumps;
	}

	public void setPumps(List<Pump> pumps) {
		this.pumps = pumps;
	}
	
	public List<Till> getTills() {
		return tills;
	}

	public void setTills(List<Till> tills) {
		this.tills = tills;
	}
}
