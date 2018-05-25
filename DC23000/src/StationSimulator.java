
public class StationSimulator {

	public static void main(String[] args) {

		double pricePerGallon = 0;
		double periodOfTime = 0;

		if (args.length < 2) {
			System.err.println("Please provide gallon price and time period.");
		} else {
			pricePerGallon = new Double(args[0]).doubleValue();
			periodOfTime = new Double(args[1]).doubleValue();
		}

		PetrolStation station = new PetrolStation();
		System.out.println("Creating new petrol station...");
		System.out.println("Price per gallon set to: " + pricePerGallon);
		System.out.println("Period of time set to: " + periodOfTime);

	}

}
