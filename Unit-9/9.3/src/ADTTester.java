

public class ADTTester {
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Abstract Data Type Tester.  "
				+ "Today we will test Stacks and Queues.");

		int choice = -1;
		while(choice != 1 && choice != 2) {
			System.out.print("Press 1 to test the Stack (LIFO: Last in, First out), 2 to test the Queue (FIFO: First in, First out): ");
			choice = TextIO.getlnInt();
		}
		
		if(choice == 1) {
			stackTest();
		}
		else {
			queueTest();
		}
		System.out.println("Goodbye! Thanks for using the ADT Tester!");
	}
	
	private static void stackTest() {
		// ***Could implement a Stack with an array, ArrayList, or LinkedList***
		//StackBehavior stack = new StackUsingArray();
		StackBehavior stack	= new StackUsingLinkedList();
		while(true) {
			System.out.print("Enter an int to push onto stack, or type \"p\" to pop off the top number (\"q\" to quit): ");
			String input = TextIO.getlnString();
			if(input.toLowerCase().equals("p")) {
				try{
					stack.pop();
					System.out.println(stack);
				}
				catch(IllegalStateException e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
			if(input.toLowerCase().equals("q")) {
				return;
			}
			else {
				try {
					int num = Integer.parseInt(input);
					stack.push(num);
					System.out.println(stack);
				}
				catch(NumberFormatException e) {
					continue;
				}
			}
		}
	}
	
	private static void queueTest() {
		// ***Could implement a Queue with an array, ArrayList, or LinkedList***
		QueueBehavior queue = new QueueUsingLinkedList();
		//QueueBehavior queue = new QueueUsingArrayList();
		while(true) {
			System.out.print("Enter an int to enqueue to the back of the line, or type \"d\" to dequeue the longest-waiting int (\"q\" to quit): ");
			String input = TextIO.getlnString();
			if(input.toLowerCase().equals("d")) {
				try{
					queue.dequeue();
					System.out.println(queue);
				}
				catch(IllegalStateException e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
			if(input.toLowerCase().equals("q")) {
				return;
			}
			else {
				try {
					int num = Integer.parseInt(input);
					queue.enqueue(num);
					System.out.println(queue);
				}
				catch(NumberFormatException e) {
					continue;
				}
			}
		}
	}
		
	
	
}
