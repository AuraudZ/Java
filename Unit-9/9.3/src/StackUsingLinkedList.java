
public class StackUsingLinkedList implements StackBehavior {
	/**
	 * An object of type Node holds one of the items in the linked list
	 * that represents the stack.
	 */
	private static class Node {
		int item;
		Node next; 
		
		private Node(int n) {
			item = n;
		}
	}

	// Pointer to the Node that is at the top of the stack. 
	// If top == null, then the stack is empty.
	private Node top; 

	//Add N to the top of the stack.
	public void push(int num) {
		Node newTop = new Node(num); 	// A Node to hold the new item.
		newTop.next = top; 			// The new Node points to the old top.
		top = newTop; 				// The new item is now on top.
	}

	/**
	 * Remove the top item from the stack, and return it.
	 * Throws an IllegalStateException if the stack is empty when
	 * this method is called.
	 */
	public int pop() {
		if ( top == null )
			throw new IllegalStateException("Can’t pop from an empty stack.");
		int topItem = top.item;  // The item that is being popped.
		top = top.next;          // The previous second item is now on top.
		return topItem;
	}
	
	/**
	 * Returns true if the stack is empty.  Returns false
	 * if there are one or more items on the stack.
	 */
	public boolean isEmpty() {
		return (top == null);
	}
	
	public String toString() {
		String listString = "";
		Node runner = top;
		while(runner != null) {
			if(runner.equals(top)) {
				listString += "*Top:" + runner.item + "*\n";
			}
			else {
				listString += runner.item + "\n";
			}
			runner = runner.next;  
		}
		return listString;
	}
} // end class StackOfInts 
