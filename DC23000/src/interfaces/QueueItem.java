package interfaces;

/**
 * An interface for the items in a queue
 * 
 * @author Jake Woakes
 *
 */
public interface QueueItem {

	public default double getSize() {
		return 1;
	}
}
