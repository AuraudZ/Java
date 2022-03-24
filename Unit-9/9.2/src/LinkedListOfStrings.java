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
        return listString + "null";
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
        if (head == null) {
            return 0;
        }
        // Recursive Case: if head.name contains str, add 1 to count
        if (head.name.contains(str)) {
            return 1 + recursivelyCountNodesWithString(head.next, str);
        }
        return recursivelyCountNodesWithString(head.next, str);
    }

    // Prints the nodes in reverse, iteratively
    public void printReversed(Node head) {
        String reversedName = "";
        Node current = head;
        while (current != null) {
            reversedName = current.name + "\n" + reversedName;
            current = current.next;
        }
        reversedName += "null\n";
        System.out.println(reversedName);
    }

    // Prints the nodes in reverse, recursively
    public void recursivelyPrintReversed(Node head) {
        // Base Case: if head is null return
        if (head == null) {
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
        if (head == null) {
            head = n;
            nodeCount++;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = n;
        nodeCount++;
        return true;
    }

    // Inserts the specified element at the specified position in this list.
    // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
    public void add(int index, Node n) throws IndexOutOfBoundsException {
        Node current = head;
        // If the index is invalid, throw an exception
        if (index <= -1 || index > nodeCount) {
            throw new IndexOutOfBoundsException();
        }
        // Make sure that if the index is 0 that we add the node to the front
        if (index == 0) {
            n.next = head;
            head = n;
        }
        // Iterate through the list until we get to the index
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        // Update the next node
        if (index > 0) {
            n.next = current.next;
            current.next = n;
        }
        nodeCount++;
    }

    // Removes all the elements from this list.
    public void clear() {
        while (head != null) {
            head = head.next;
        }
        nodeCount = 0;
    }

    // Returns true if this list contains the specified element.
    public boolean contains(Node n) {
        // Run through the list, checking each node for equality
        for (Node current = head; current != null; current = current.next) {
            if (current.equals(n)) {
                return true;
            }
            if (current.name.equals(n.name)) {
                return true;
            }
            if (current.next == null) {
                return false;
            }
        }
        return false;
    }

    // Returns the element at the specified position in this list
    // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
    public Node get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= nodeCount) {
            throw new IndexOutOfBoundsException();
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException();
            }
            current = current.next;
        }
        return current;
    }

    // Removes the element at the specified position in this list.
    // Returns the element previously at the specified position
    // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
    public Node remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= nodeCount) {
            throw new IndexOutOfBoundsException();
        }
        // If the index is 0, remove the head
        if (index == 0) {
            Node temp = head;
            head = head.next;
            nodeCount--;
            return temp;
        }
        // Iterate through the list until we get to the index
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
            // Once we get to the index, we need to save a reference to the node we want to remove to return it later
            if (i == index - 2) {
                Node temp = current.next;
                current.next = current.next.next;
                nodeCount--;
                return temp;
            }
        }
        return null;
    }

    // Removes the first occurrence of the specified element from this list, if it is present.
    // Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
    public boolean remove(Node n) {
        // If the head is the node we want to remove it but it is not working and return 0 index instead of 1
        // But when we try to remove  node from the front of the list, it is not working

        if (head.equals(n)) {
            head = head.next;
            nodeCount--;
            return true;
        }
        if (head.next.equals(n)) {
            head.next = head.next.next;
            nodeCount--;
            return true;
        }
        // Run through the list, checking each node for equality
        for (Node current = head; current != null; current = current.next) {
            if (current.equals(n)) {
                current.next = current.next.next;
                nodeCount--;
                return true;
            }
            if (current.next == null) {
                return false;
            }
        }
        return false;
    }

    // Replaces the element at the specified position in this list with the specified element.
    // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
    // Return the element previously at the specified position
    public Node set(int index, Node n) throws IndexOutOfBoundsException {
        Node current = head;
        if (index < 0 || index >= nodeCount) {
            throw new IndexOutOfBoundsException();
        }
        if (current == null) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            // Loop through the list until we get to the index
            // once we get to the index, we need to save a reference to the node we want to remove to return it later
            if (i == index - 1) {
                Node list = current.next.next;
                Node temp = current.next; // Reference to the node we want to remove to return it later
                n.next = list;
                current.next = n;
                return temp;
            }
            current = current.next;

        }
        if (current.next == null) {
            throw new IndexOutOfBoundsException();
        }

        // Make sure we are setting the node at the correct index
        if (index == 0) {
            Node list = current.next; // Temp is the rest of the list
            Node previous = current;  // Previous is the node before the node we want to replace
            // We need to change the next node to the rest of the list
            n.next = list;
            // Set the current node to the new node
            current = n;
            head = current;
            return previous;
        }
        return null;
    }

    // Returns the number of elements in this collection.
    public int size() {
        return nodeCount;
    }
}

