package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import interfaces.ItemsInQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A class to represent a Circular Array Queue
 * @author Jake Woakes
 */
public class CircularArrayQueue<T extends ItemsInQueue> implements Iterable<T> {
	
	/**
	 * The Array that holds things inside the queue
	 */
	private ObservableList<T> queueItems;
	/**
	 * The maximum capacity size of the queue
	 */
	private double maxQueueCapacity;
	/**
	 * The free space in a queue
	 */
	private double freeSpace;

	/**
	 * Constructor to build the CircularArrayQueue
	 * @param capacity The maximum capacity size of the queue
	 */
	public CircularArrayQueue(double capacity) {
		queueItems = FXCollections.observableList(new ArrayList<T>());
		this.maxQueueCapacity = capacity;
		this.freeSpace = capacity;
	}
	
	/**
	 * Add an element to the rear of the queue
	 * @param element What we want added to the queue
	 * @return whether the element was added
	 * 
	 */
	public boolean add(T element) {
		if (isFull() || element.getSize() > freeSpace) {
			return false;
		}
		queueItems.add(element);
		freeSpace -= element.getSize();
		return true;
	}
	
	/**
	 * Remove the element at the front of the queue
	 * @return the removed element
	 */
	public T remove() {
		T element = queueItems.remove(0);
		freeSpace += element.getSize();
		return element;
	}

	/**
	 * If queue is empty
	 * @return true
	 */
	public boolean isEmpty() {
		return queueItems.isEmpty();
	}

	/**
	 * If the queue is full
	 * @return true
	 */
	public boolean isFull() {
		return freeSpace == 0;
	}

	/**
	 * Get element at front of the queue without removing it
	 * @return the element at the front of the queue
	 */
	public T peek() {
		try {
			return queueItems.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Check if the queue contains a specified element
	 * @param element The element we want to look for
	 * @return true if element is found
	 */
	public boolean contains(T element) {
		return queueItems.contains(element);
	}
	
	/**
	 * Returns an iterator for the queue
	 */
	public Iterator<T> iterator() {
		return queueItems.iterator();
	}
	
	/**
	 * Access the size of the queue
	 * @return the current size of the queue
	 */
	public double getSize() {
		return maxQueueCapacity - freeSpace;
	}
	
	/**
	 * Access listeners to track change
	 * @return the array
	 */
	public ObservableList<T> getObservable() {
		return queueItems;
	}
}