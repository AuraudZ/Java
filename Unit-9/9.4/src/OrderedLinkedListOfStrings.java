
public class OrderedLinkedListOfStrings {
	// What is our underlying data structure...not a Node[]!  A linked list!
	// Well, what does a OrderedLinkedListOfStrings need? 
	Node head;
	
	// Iteratively traverse the Linked List, printing out the String + " --> "
	public String toString() {
		String listString = "Head: ";
		Node runner = head;
		while(runner != null) {
			listString += runner.name + " --> ";
			runner = runner.next;  
		}
		listString += "null";
		return listString;
	}

	// Add to the Ordered Linked List of Strings..but it must be inserted such that
	// it maintains an alphabetical order
	// str.compareTo(anotherStr) > 0 if str is "later" anotherStr lexicographically, based on the Unicode.
	// str.compareTo(anotherStr) < 0 if str is "before" anotherStr
	// str.compareTo(anotherStr) == 0 if they are equal -- or you can use .equals()
	public void insert(String str) {
		Node n = new Node(str);
		Node runner = head;
		Node prev = null;
		
		if(runner == null) { // empty list

			
		}
		
		// Advance to the correct location in the Linked List
		while(runner != null && n.name.compareTo(runner.name) >= 0) { 
			prev = runner;  // update 'prev' in case you need to insert 
			runner = runner.next; // update 'runner'
		}

		// Insert Node now that we are in the correct spot
		prev.next = n;
		n.next = runner;
	}
}
