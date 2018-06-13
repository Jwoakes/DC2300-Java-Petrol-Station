import java.util.ArrayList;

import java.util.List;

public class StationSimulator {

	public static void main(String[] args) {

		double pricePerGallon = 0;

		double periodOfTime = 0;

		int numOfPumps = 0;
		
		int numOfTills = 0;

		if (args.length < 4) {

			System.err.println("Please provide gallon price, time period and number of pumps required.");

		} else {

			pricePerGallon = new Double(args[0]).doubleValue();

			periodOfTime = new Double(args[1]).doubleValue();

			numOfPumps = Integer.valueOf(args[2]);
			
			numOfTills = Integer.valueOf(args[3]);

		}

		List<Pump> pumps = new ArrayList<Pump>();

		for (int i = 0; i < numOfPumps; i++) {

			Pump aPump = new Pump();

			pumps.add(aPump);

		}
		
		List<Till> tills = new ArrayList<Till>();

		for (int i = 0; i < numOfTills; i++) {

			Till aTill = new Till();

			tills.add(aTill);

		}

		PetrolStation station = new PetrolStation();

		station.setPumps(pumps);
		station.setTills(tills);

		System.out.println("Creating new petrol station...");

		System.out.println("Price per gallon set to: " + pricePerGallon);

		System.out.println("Period of time set to: " + periodOfTime);

		System.out.println("Number of pumps set to: " + station.getPumps().size());
		
		System.out.println("Number of tills set to: " + station.getTills().size());


	}

}
