package model;

import java.util.List;

import controller.PumpController;
import controller.TillController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A class to represent the entire Petrol Station
 * 
 * @author Jake Woakes
 */
public class PetrolStation {

	/**
	 * The Shop inside of the Petrol Station
	 */
	private Shop shop = new Shop();
	/**
	 * A controller used for the pumps at the Petrol Station
	 */
	private PumpController pumps;
	/**
	 * A controller used for the tills at the Petrol Station
	 */
	private TillController tills;
	/**
	 * The number of ticks that have passed at the Petrol Station
	 */
	private SimpleIntegerProperty ticksPassed;
	/**
	 * The total income from fuel sales
	 */
	private SimpleDoubleProperty gallonsSold;
	/**
	 * The total income from shop sales
	 */
	private SimpleDoubleProperty shopIncome;
	/**
	 * The total number of lost fuel
	 */
	private SimpleDoubleProperty lostGallons;
	/**
	 * The total number of lost income
	 */
	private SimpleDoubleProperty lostShopIncome;

	/**
	 * Constructor to build a Petrol Station
	 * 
	 * @param numPumps
	 *            the number of Pumps at the Petrol Station
	 * @param numTills
	 *            the number of Tills at the Petrol Station
	 * @param smallestVehicle
	 *            the smallest size a vehicle can be
	 * @param maxQueueSize
	 *            the maximum size a queue can be
	 */
	public PetrolStation(int numPumps, int numTills, double smallestVehicle, int maxQueueSize) {
		this.pumps = new PumpController(numPumps, smallestVehicle);
		this.tills = new TillController(numTills, maxQueueSize);
		ticksPassed = new SimpleIntegerProperty(0);
		gallonsSold = new SimpleDoubleProperty(0);
		shopIncome = new SimpleDoubleProperty(0);
		lostGallons = new SimpleDoubleProperty(0);
		lostShopIncome = new SimpleDoubleProperty(0);
	}

	/**
	 * Access the amount of fuel sold
	 * 
	 * @return the amount of fuel sold
	 */
	public SimpleDoubleProperty getGallonsSold() {
		return gallonsSold;
	}

	/**
	 * Access the amount of fuel lost
	 * 
	 * @return the amount of fuel lost
	 */
	public SimpleDoubleProperty getLostGallons() {
		return lostGallons;
	}

	/**
	 * Access the amount of income lost
	 * 
	 * @return the amount of income lost
	 */
	public SimpleDoubleProperty getLostShopIncome() {
		return lostShopIncome;
	}

	/**
	 * Access the total shop income
	 * 
	 * @return the total shop income
	 */
	public SimpleDoubleProperty getShopIncome() {
		return shopIncome;
	}

	/**
	 * Takes Payment from Customer at Till
	 */
	public void collectPayments() {
		List<Payment> payments = tills.collectPayments();
		for (Payment p : payments) {
			gallonsSold.setValue(gallonsSold.get() + p.getAmountFuelPurchased());
			shopIncome.setValue(shopIncome.get() + p.getShopSpend());
		}
	}

	/**
	 * Access the created shop
	 * 
	 * @return the shop
	 */
	public Shop getShop() {
		return this.shop;
	}

	/**
	 * Access the Pump Controller
	 * 
	 * @return the pump array
	 */
	public PumpController getPumpController() {
		return this.pumps;
	}

	/**
	 * Access the Till Controller
	 * 
	 * @return the till array
	 */
	public TillController getTillController() {
		return this.tills;
	}

	/**
	 * Passes a vehicle to the Pump Controller
	 * 
	 * @param vehicle
	 *            a vehicle
	 * @return vehicle in shortest queue
	 */
	public Boolean recieveVehicle(Vehicle vehicle) {
		return pumps.enqueue(vehicle);
	}

	/**
	 * Passes a Customer to the Shop or the Till Controller depending upon their
	 * preference
	 * 
	 * @param customer
	 *            a customer at the petrol station
	 */
	public void recieveCustomer(Customer customer) {
		if (customer.getHasPaid()) {
			pumps.recieveCustomer(customer);
		} else if (customer.getEnterShop()) {
			shop.add(customer);
		} else {
			tills.enqueue(customer);
		}
	}

	/**
	 * Loop on all customers in the list
	 * 
	 * @param customers
	 *            customers at the petrol station
	 */
	public void recieveCustomers(List<Customer> customers) {
		for (Customer customer : customers) {
			recieveCustomer(customer);
		}
	}

	/**
	 * Remove vehicles from the queue if ready
	 * 
	 * @return the new queue
	 */
	public List<Vehicle> dispatchComplete() {
		return pumps.dequeueAllFullyPaid();
	}

	/**
	 * Increase time by 1 tick
	 * 
	 * @param vehicle
	 *            a vehicle at the petrol station
	 */
	public void tick(Vehicle vehicle) {
		for (Vehicle leaving : pumps.dequeueAllFullyPaid()) {
			if (!leaving.getDidCustomerShop()) {
				lostShopIncome.set(lostShopIncome.get() + leaving.getShopSpend());
			}
		}
		List<Customer> finishedPaying = tills.dequeueFullyPaid();
		collectPayments();
		recieveCustomers(shop.tick());
		recieveCustomers(pumps.tick());
		recieveCustomers(finishedPaying);
		if (vehicle != null) {
			recieveVehicle(vehicle);
		}
		ticksPassed.set(ticksPassed.get() + 1);
	}

	/**
	 * Access the number of ticks that have passed
	 * 
	 * @return the number of ticks passed
	 */
	public SimpleIntegerProperty getTicks() {
		return this.ticksPassed;
	}
}