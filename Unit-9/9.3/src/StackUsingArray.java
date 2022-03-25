import java.util.Arrays;

public class StackUsingArray implements StackBehavior {  
	
	private int[] lifoItems = new int[10];  // Holds the items on the stack.
	private int top = 0;  // The number of items currently on the stack.
	
	/**
	 * Add N to the top of the stack.
	 */
	public void push( int N ) {
		if (top == lifoItems.length) {
			// The array is full, so make a new, larger array and
			// copy the current stack lifoItems into it.  
			lifoItems = Arrays.copyOf( lifoItems, 2*lifoItems.length );
		}
		lifoItems[top] = N;  // Put N in next available spot.
		top++;           // Number of items goes up by one.
	}
	
	/**
	 * Remove the top item from the stack, and return it.
	 * Throws an IllegalStateException if the stack is empty when
	 * this method is called.
	 */
	public int pop() {
		if ( top == 0 )
			throw new IllegalStateException("Can’t pop from an empty stack.");
		int topItem = lifoItems[top - 1];  // Top item in the stack.
		top--;    // Number of items on the stack goes down by one.
		return topItem;
	}
	
	/**
	 * Returns true if the stack is empty.  Returns false
	 * if there are one or more items on the stack.
	 */
	public boolean isEmpty() {
        return (top == 0);
     }
	
	public String toString() {
		String s = "";
		for(int i = top - 1; i >= 0; i--) {
			if(i == top - 1) {
				s += "**" + lifoItems[i] + "** ";
			}
			else {
				s += lifoItems[i] + " ";
			}
		}
		return s;	
	}
	
  } 
