
public class BinaryTreeOfStrings {
	// Every Binary Tree object needs a reference to its root
	TreeNode root;

	// Insert a TreeNode into the tree in the proper location to 
	// maintain a sorted order.  If there is a tie, you can insert it to the right.
	// Consider different cases the tree might be in.
	// str.compareTo(anotherStr) > 0 if str is "later" anotherStr lexigraphically, based on the Unicode.
	// str.compareTo(anotherStr) < 0 if str is "before" anotherStr
	// str.compareTo(anotherStr) == 0 if they are equal -- or you can use .equals()
	public void insert(String str) {
		TreeNode n = new TreeNode(str);
		TreeNode runner = root; // Traverse iteratively

	}
	

	// Return true if the nameToFind is in the tree somewhere.
	// Return false otherwise.
	// Traverse the tree iteratively.
	public boolean contains(String nameToFind) {
		return false;
	}
	
	// Return true if the nameToFind is in the tree somewhere.
	// Return false otherwise.
	// Implement recursively.
	// Note: this is a 'helper' for the actual recursive method -- outside class can't access root
	public boolean containsRecursive(String nameToFind) {
		return containsRecursive(root, nameToFind);
	}
	
	private boolean containsRecursive(TreeNode root, String nameToFind) {
		// base case
		return false;
		
		// recursive call
		
	}
	
	// HUGE thanks to Michal Kreuzman for writing this print method!
	// http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	public void print() {
		BTreePrinter.printNode(root);
	}
	
	
	// helper for recursive method -- outside class can't access root
	public int getSize() {
		return getSize(root);
	}
	
	// recursively get the number of nodes in the tree
	private int getSize(TreeNode root) {
		if(root == null) // Base Case
			return 0; 
		// Otherwise add this node to the sum of all the nodes
		// in the left subtree + all nodes in right subtree
		return 1 + getSize(root.left) + getSize(root.right);
	}

}
