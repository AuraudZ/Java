public class LinkedListOfStrings {
    // What is our underlying data structure...not a Node[]!  A linked list!
    // Well, what does a LinkedList need?
    Node head;
    int nodeCount;

    // iteratively traverse the Linked List, printing out the String + " --> "
    // unless it's the last node, in which case print out the String + " --> null"
    public String toString() {
        String listString = "Head: ";
        Node runner = head; // copy head reference to runner

        while (runner != null) {
            listString += runner.name + " --> ";
            runner = runner.next;
        }
        return listString + " null";
    }

    // iteratively traverse the Linked List, counting out the number of Nodes
    // whose String contains str
    public int countNodesWithString(String str) {
        int count = 0;
        Node runner = head;
        while (runner != null) {
            if (runner.name.contains(str)) {
                count++;
            }
            runner = runner.next;
        }
        return count;
    }

    // recursively traverse the Linked List, counting out the number of Nodes
    // whose String contains str
    public int recursivelyCountNodesWithString(Node head, String str) {
        if (head == null) {
            return 0;
        }
        if (head.name.contains(str)) {
            return 1 + recursivelyCountNodesWithString(head.next, str);
        } else {
            return recursivelyCountNodesWithString(head.next, str);
        }
    }

    // Prints the nodes in reverse, iteratively
    public void printReversed(Node head) {
        String listString = "";
        Node runner = head; // copy head reference to runner

        while (runner != null) {
            listString = runner.name + "\n" + listString;
            runner = runner.next;
        }
        System.out.println(listString);
    }

    // Prints the nodes in reverse, recursively
    public void recursivelyPrintReversed(Node head) {
        if (head == null) {
            return;
        }
        recursivelyPrintReversed(head.next);
        System.out.println(head.name);
    }


    //********* Some of the LinkedList operations specified by Interface List ********
    // (not all of them, that's why we aren't implementing the actual interface)


    // Appends the specified element to the end of this list.
    // Returns true if this collection changed as a result of the call.
    // (Returns false if this collection does not permit duplicates and already contains the specified element.)
    public boolean add(Node n) {
        Node runner = head;
        if (head == null) {
            head = n;
            nodeCount++;
            return true;
        }
        while (runner.next != null) {
            runner = runner.next;
        }
        runner.next = n;
        nodeCount++;
        return true;
    }

    // Inserts the specified element at the specified position in this list.
    // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
    public void add(int index, Node n) {
        // Cases to check for:
        // 1. index == 0
        // 2. index == size()
        // 3. index > size()
        Node runner = head;
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            n.next = head;
            head = n;
            nodeCount++;
            return;
        }

        for (int i = 0; i < index - 1; i++) {
            runner = runner.next;
        }

        Node temp = runner.next;
        runner.next = n;
        n.next = temp;
        nodeCount++;
    }


    // Removes all of the elements from this list.
    public void clear() {
        while (head != null) {
            head = head.next;
        }
        nodeCount = 0;
    }

    // Returns true if this list contains the specified element.
    public boolean contains(Node n) {
        Node runner = head;
        for (int i = 0; i < size(); i++) {
            if (runner.name.equals(n.name)) {
                return true;
            } else {
                runner = runner.next;
            }
        }
        return false;
    }

    // Returns the element at the specified position in this list
    // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
    public Node get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node runner = head;
        for (int i = 0; i < index; i++) {
            runner = runner.next;

        }
        return runner;

    }

    // Removes the element at the specified position in this list.
    // Returns the element previously at the specified position
    // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
    public Node remove(int index) {
        if (index < 0 || index >= nodeCount) {
            throw new IndexOutOfBoundsException();
        }
        // We need the rest of the list to be able to remove the node and we need to node we're removing to be able to return it
        // So to do this we need to go to index - 1 and grab the node after the one we're removing and set the node after the one we're removing to null
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
        if (head.name.equals(n.name)) {
            head = head.next;
            nodeCount--;
            return true;
        }
        Node runner = head;
        Node temp = head;
        for (int i = 0; i < size(); i++) {
            if (runner.name.equals(n.name)) {
                temp.next = runner.next;
                runner = runner.next;
                nodeCount--;
                return true;
            }
            temp = runner;
            runner = runner.next;
        }
        return false;
    }


    // Replaces the element at the specified position in this list with the specified element.
    // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())

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
