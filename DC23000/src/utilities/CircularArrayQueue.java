package utilities;

import java.util.ArrayList;
import java.util.Iterator;

import interfaces.QueueItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CircularArrayQueue<T extends QueueItem> implements Iterable<T> {
	
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
	 * Constructor for CircularArrayQueue
	 * @param capacity The maximum size of the queue
	 */
	public CircularArrayQueue(double capacity) {
		queueItems = FXCollections.observableList(new ArrayList<T>());
		this.maxQueueCapacity = capacity;
		this.freeSpace = capacity;
	}
	
	/**
	 * Add an element to the rear of the queue
	 * @param t
	 * @return
	 * 
	 */
	public boolean add(T t) {
		if (isFull() || t.getSize() > freeSpace) {
			return false;
		}
		queueItems.add(t);
		freeSpace -= t.getSize();
		return true;
	}
	
	/**
	 * Remove the element at the front of the queue
	 * @return
	 */
	public T remove() {
		T t = queueItems.remove(0);
		freeSpace += t.getSize();
		return t;
	}

	/**
	 * Returns true if there is nothing in the queue
	 * @return
	 */
	public boolean isEmpty() {
		return queueItems.isEmpty();
	}

	/**
	 * Returns true if the queue is full
	 * @return
	 */
	public boolean isFull() {
		return freeSpace == 0;
	}

	/**
	 * Return the first element in the queue without removing it
	 * @return
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
	 * @param t
	 * @return
	 */
	public boolean contains(T t) {
		return queueItems.contains(t);
	}

	/*
	 * Returns an iterator for the queue
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<T> iterator() {
		return queueItems.iterator();
	}
	
	public double getSize() {
		return maxQueueCapacity - freeSpace;
	}
	
	public ObservableList<T> getObservable() {
		return queueItems;
	}

}