package interfaces;

/**
 * An interface for the items in a queue
 * @author Jake Woakes
 */
public interface ItemsInQueue {

	/**
	 * Access the size of the items in the Queue
	 * @return 1
	 */
	public default double getSize() {
		return 1;
	}
}
