package model;

import java.util.List;

import controller.PumpController;
import controller.TillController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

    
    /**
     * @author Jake Woakes
     */
    public class PetrolStation {

    	/**
    	 * A controller for all of the pumps at the Petrol Station
    	 */
    	private PumpController pumps;
    	/**
    	 * A controller for all of the tills at the Petrol Station
    	 */
    	private TillController tills;
    	/**
    	 * The Shop in the Petrol Station
    	 */
    	private Shop shop = new Shop();
    	/**
    	 * The number of ticks that have passed at this Petrol Station
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

    	private SimpleDoubleProperty lostGallons;

    	private SimpleDoubleProperty lostShopIncome;

    	/**
    	 * Constructor for a petrol station
    	 * 
    	 * @param numPumps = the number of Pumps at the Petrol Station
    	 * @param numTills = the number of Tills at the Petrol Station
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
    	 * Getter for fuel income
    	 * 
    	 * @return
    	 */
    	public SimpleDoubleProperty getGallonsSold() {
    		return gallonsSold;
    	}
    	
    	/**
    	 * Getter for lostGallons
    	 * 
    	 * @return
    	 */
    	public SimpleDoubleProperty getLostGallons() {
    		return lostGallons;
    	}
    	
    	/**
    	 * Getter for lostShopincome
    	 * 
    	 * @return
    	 */
    	public SimpleDoubleProperty getLostShopIncome() {
    		return lostShopIncome;
    	}

    	/**
    	 * Getter for shop income
    	 * 
    	 * @return
    	 */
    	public SimpleDoubleProperty getShopIncome() {
    		return shopIncome;
    	}

    	/**
    	 * Collects payments from any Customer that is ready to pay at any of the Tills
    	 * in the Petrol Station
    	 * 
    	 * @throws HasPaidException
    	 */
    	public void collectPayments() {
    		List<Payment> payments = tills.collectPayments();
    		for (Payment p : payments) {
    			gallonsSold.setValue(gallonsSold.get() + p.getAmountFuelPurchased());
    			shopIncome.setValue(shopIncome.get() + p.getShopSpend());
    		}
    	}

    	/**
    	 * Getter for the Shop
    	 * 
    	 * @return
    	 */
    	public Shop getShop() {
    		return this.shop;
    	}

    	/**
    	 * Getter for the Pump Controller
    	 * 
    	 * @return
    	 */
    	public PumpController getPumpController() {
    		return this.pumps;
    	}

    	/**
    	 * Getter for the Till Controller
    	 * 
    	 * @return
    	 */
    	public TillController getTillController() {
    		return this.tills;
    	}

    	/**
    	 * Takes a Vehicle and passes it to the Pump Controller
    	 * 
    	 * @param v
    	 * @return
    	 */
    	public Boolean recieveVehicle(Vehicle v) {
    		return pumps.enqueue(v);
    	}

    	/**
    	 * Takes a Customer and passes it to either the Shop or the Till Controller
    	 * according to what the Customer wants
    	 * 
    	 * @param c
    	 */
    	public void recieveCustomer(Customer c){
    		if (c.getHasPaid()) {
    			pumps.recieveCustomer(c);
    		} else if (c.getEnterShop()) {
    			shop.add(c);
    		} else {
    			tills.enqueue(c);
    		}
    	}

    	/**
    	 * loops through a list of customer and call receive customer
    	 * on each customer in the list
    	 * 
    	 * @param customers
    	 * @throws CustomerCouldNotFindVehicleException
    	 * @throws CustomerHasNotPaidException
    	 * @throws CustomerCarMismatchException
    	 * @throws TillFullException
    	 */
    	public void recieveCustomers(List<Customer> customers) {
    		for (Customer c : customers) {
    			recieveCustomer(c);
    		}
    	}

    	/**
    	 * Asks the Pump Controller to dequeue any Vehicles that are ready to leave
    	 * 
    	 * @return
    	 */
    	public List<Vehicle> dispatchComplete() {
    		return pumps.dequeueAllFullyPaid();
    	}

    	/**
    	 * Progress time at the Petrol Station by 1 tick This will alert the Pump
    	 * Controller, Till Controller and Shop that time has Passed;
    	 */
    	public void tick(Vehicle v) {
    		for (Vehicle leaving: pumps.dequeueAllFullyPaid()) {
    			if (!leaving.getDidCustomerShop()) {
    				lostShopIncome.set(lostShopIncome.get() + leaving.getShopSpend());
    			}
    		}
    		List<Customer> finishedPaying = tills.dequeueFullyPaid();
    		collectPayments();
    		recieveCustomers(shop.tick());
    		recieveCustomers(pumps.tick());
    		recieveCustomers(finishedPaying);
    		if (v != null) {
    			recieveVehicle(v);
    		}
    		ticksPassed.set(ticksPassed.get() + 1);
    	}

    	/**
    	 * Getter for the number of ticks that have passed
    	 * 
    	 * @return
    	 */
    	public SimpleIntegerProperty getTicks() {
    		return this.ticksPassed;
    	}
    }