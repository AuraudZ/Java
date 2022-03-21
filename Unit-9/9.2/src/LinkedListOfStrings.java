public class LinkedListOfStrings {
	// What is our underlying data structure...not a Node[]!  A linked list!
	// Well, what does a LinkedList need?
	Node head;
	int nodeCount;
	// iteratively traverse the Linked List, printing out the String + " --> "
	// unless it's the last node, in which case print out the String + " --> null"
	public String toString() {
		String listString = "";
		Node current = head;
		while (current != null) {
			listString += current.name + " --> ";
			current = current.next;
		}
		return  listString + "null";
	}
	
	// iteratively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int countNodesWithString(String str) {
		int count = 0;
		for (Node current = head; current != null; current = current.next) {
			if (current.name.contains(str)) {
				count++;
			}
		}
		return count;
	}
	
	// recursively traverse the Linked List, counting out the number of Nodes
	// whose String contains str
	public int recursivelyCountNodesWithString(Node head, String str) {
		// Base Case: if head is null reutrn
		int count = 0;
		if(head == null) {
			return 0;
		}
		// Recursive Case: if head.name contains str, add 1 to count
		if(head.name.contains(str)) {
			return 1 + recursivelyCountNodesWithString(head.next, str);
		}
		return recursivelyCountNodesWithString(head.next, str);
	}
	
	// Prints the nodes in reverse, iteratively
	public void printReversed(Node head) {
		Node current = head;
		while (current != null) {
			System.out.println(current.name);
			current = current.next;

		}
	}
	
	// Prints the nodes in reverse, recursively
	public void recursivelyPrintReversed(Node head) {
		// Base Case: if head is null return
		if(head == null) {
			return;
		}
		// Recursive Case: print the next node, then recursively print the next node
		recursivelyPrintReversed(head.next);
		System.out.println(head.name);
	}
	
	
	
	//********* Some of the LinkedList operations specified by Interface List ********
	// (not all of them, that's why we aren't implementing the actual interface)
	
	
	// Appends the specified element to the end of this list.
	// Returns true if this collection changed as a result of the call. 
	// (Returns false if this collection does not permit duplicates and already contains the specified element.) 
	public boolean add(Node n) {
		return false;
	}
	
	// Inserts the specified element at the specified position in this list.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
	public void add(int index, Node n) {
		
	}
	
	// Removes all of the elements from this list.
	public void clear() {

	}
	
	// Returns true if this list contains the specified element.
	public boolean contains(Node n) {
		return false;
	}
	
	// Returns the element at the specified position in this list
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node get(int index) throws IndexOutOfBoundsException {
	Node current = head;
	for (int i = 0; i < index; i++) {
		current = current.next;
	}
	return current;
	}
	
	// Removes the element at the specified position in this list.
	// Returns the element previously at the specified position
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node remove(int index) throws IndexOutOfBoundsException {
		return null;
	}
	
	
	// Removes the first occurrence of the specified element from this list, if it is present.
	// Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
	public boolean remove(Node n) {
		return false;
	}
	
	// Replaces the element at the specified position in this list with the specified element.
	// Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
	public Node set(int index, Node n) {
		return null;
	}
	
	// Returns the number of elements in this collection.
	public int size() {
		return nodeCount;
	}
	

	
}
