
public class BinaryTreeOfStrings {
	// Every Binary Tree object needs a reference to its root
	TreeNode<String> root;

	// Insert a TreeNode into the tree in the proper location to 
	// maintain a sorted order.  If there is a tie, you can insert it to the right.
	// Consider different cases the tree might be in.
	// str.compareTo(anotherStr) > 0 if str is "later" anotherStr lexigraphically, based on the Unicode.
	// str.compareTo(anotherStr) < 0 if str is "before" anotherStr
	// str.compareTo(anotherStr) == 0 if they are equal -- or you can use .equals()
	public void insert(String str) {
		TreeNode<String> n = new TreeNode<>(str);
		TreeNode<String> runner = root; // Traverse iteratively
		// Empty tree
		if(root == null) {
			root = n;
			return;
		}

		while (true) {
			if(str.compareTo(runner.name) < 0) {
				if(runner.left == null) {
					runner.left = n;
					return;
				}

			}
			else {
				if(runner.right == null) {
					runner.right = n;
					return;
				}
			}
			runner = runner.left;
		}
	}
	

	// Return true if the nameToFind is in the tree somewhere.
	// Return false otherwise.
	// Traverse the tree iteratively.
	public boolean contains(String nameToFind) {
		TreeNode<String> runner = root;
		while (runner != null) {
			if (nameToFind.compareTo(runner.name) < 0) {
				runner = runner.left;
			}
			else if (nameToFind.compareTo(runner.name) > 0) {
				runner = runner.right;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	// Return true if the nameToFind is in the tree somewhere.
	// Return false otherwise.
	// Implement recursively.
	// Note: this is a 'helper' for the actual recursive method -- outside class can't access root
	public boolean containsRecursive(String nameToFind) {
		return containsRecursive(root, nameToFind);
	}
	
	private boolean containsRecursive(TreeNode<String> root, String nameToFind) {
		// base case
		if(root == null)
			return false;
		// if we find the name, return true
		if(nameToFind.compareTo(root.name) == 0)
			return true;
		// otherwise, recurse on the left and right subtrees
		return containsRecursive(root.left, nameToFind) || containsRecursive(root.right, nameToFind);
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
