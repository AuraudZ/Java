
public class DataStructureTester {

	public static void main(String[] args) {
		int choice;
		do {
			System.out.print("Which do you want to test?  (1) Linked List (2) Binary Tree: ");
			choice = TextIO.getlnInt();
		}
		while(choice != 1 && choice != 2);
		
		if(choice == 1)
			runLinkedList();
		else
			runBinaryTree();

	}
	
	private static void runLinkedList() {
		OrderedLinkedListOfStrings myList = new OrderedLinkedListOfStrings();

		while(true) {
			System.out.print("New name (x to stop adding nodes): ");
			String input = TextIO.getlnString();
			if(input.equals("x") || input.equals("X"))
				break;
			else
				myList.insert(input);
			System.out.println(myList);
		}
		System.out.println("Exiting!");
	}
	
	private static void runBinaryTree() {
		BinaryTreeOfStrings myTree = new BinaryTreeOfStrings();

		while(true) {
			System.out.print("New name (x to stop adding nodes): ");
			String input = TextIO.getlnString();
			if(input.equals("x") || input.equals("X"))
				break;
			else
				myTree.insert(input);
			myTree.print();
		}
		System.out.println("Exiting setup! Total of " + myTree.getSize() + " nodes added.");
		System.out.print("New name to check if it's in the tree: ");
		String nameToCheck = TextIO.getlnString();
		if(myTree.containsRecursive(nameToCheck))
			System.out.print("contains() says " + nameToCheck + " IS in the tree");
		else
			System.out.print("contains() says " + nameToCheck + " IS NOT in the tree");
		
//		System.out.println("A: " + myTree.containsRecursive("A"));
//		System.out.println("B: " + myTree.containsRecursive("B"));
//		System.out.println("C: " + myTree.containsRecursive("C"));
//		System.out.println("D: " + myTree.containsRecursive("D"));
//		System.out.println("E: " + myTree.containsRecursive("E"));
//		System.out.println("F: " + myTree.containsRecursive("F"));
//		System.out.println("G: " + myTree.containsRecursive("G"));
//		System.out.println("H: " + myTree.containsRecursive("H"));
	}
	
}
