import java.util.ArrayList;

public class QueueUsingArrayList implements QueueBehavior {

	private ArrayList<Integer> fifoItems = new ArrayList<Integer>();

	/**
	 * Add num to the back of the queue.
	 */
	public void enqueue(int num) {
		fifoItems.add(num);
	}


	/**
	 * Remove and return the front item in the queue.
	 * Throws an IllegalStateException if the queue is empty.
	 */
	public int dequeue() {
		if(fifoItems.isEmpty()) {
			throw new IllegalStateException("Can’t dequeue from an empty queue.");		
		}
		return fifoItems.remove(0);
	}


	public boolean isEmpty() {
		return fifoItems.isEmpty();
	}
	
	public String toString() {
		return "Oldest " + fifoItems.toString() + " Newest";
	}

}

