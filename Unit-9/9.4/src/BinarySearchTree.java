
public class BinarySearchTree {

	private Node root;
	private int size; // Size of tree would be the # of unique words 
	private int totalWordCount; // total words in file 
	
	/**
	 * If a WordFrequency object already exists
	 * in the tree for the parameter word,
	 * then increase the count of that WordFrequency
	 * object. Otherwise, add a WordFrequency
	 * object for the word to the binary search tree.
	 * @param word
	 */
	public void put(String word) {
		if(root == null) {
			root = new Node(new WordFrequency(word));
			size++;
			totalWordCount++;
			return;
		}
		Node current = root;
		while(true) {
			if(current.item.getWord().equals(word)) {
				current.item.increaseCount();
				totalWordCount++;
				return;
			}
			if(current.item.getWord().compareTo(word) > 0) {
				if(current.left == null) {
					current.left = new Node(new WordFrequency(word));
					size++;
					totalWordCount++;
					return;
				}
				current = current.left;
			}
			else {
				if(current.right == null) {
					current.right = new Node(new WordFrequency(word));
					size++;
					totalWordCount++;
					return;
				}
				current = current.right;
			}
		}
	}
		


	/**
	 * If a WordFrequency object with the
	 * given word exists in the tree, return
	 * the frequency of the word.
	 * Otherwise, 0 is returned.
	 * @param word
	 * @return the frequency of the word
	 */
	public int getFrequency(String word) {
		return getFrequency(root, word);
	}

	public int getFrequency(Node current, String word) {
		if(current == null) {
			return 0;
		}
		if(current.item.getWord().equals(word)) {
			return current.item.getCount();
		}
		if(current.item.getWord().compareTo(word) > 0) {
			return getFrequency(current.left, word);
		}
		return getFrequency(current.right, word);
	}

	
	/**
	 * Prints all of the WordFrequency objects
	 * in the tree that fall between
	 * startWord (inclusive) and endWord
	 * (not including endWord).
	 * Prints one per line.
	 * @param startWord the first word in the range
	 * @param endWord the last word in the range
	 */
	public void printRange(String startWord, String endWord) {
		printRange(root, startWord, endWord); //*** Recursive call.  this is helper
	}
	
	// Recursive method to print range.
	public void printRange(Node root, String startWord, String endWord) {
		if(root == null) {
			return;
		}
		if(root.item.getWord().compareTo(startWord) >= 0) {
			printRange(root.left, startWord, endWord);
		}
		if(root.item.getWord().compareTo(endWord) < 0) {
			System.out.println(root.item);
			printRange(root.right, startWord, endWord);
		}
	}
	/**
	 * Returns the number of WordFrequency objects in the tree.
	 * @return the number of WordFrequency objects in the tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns the sum of all the word frequencies
	 * @return the sum of all the word frequencies
	 */
	public int getTotalWordCount() {
		return totalWordCount;
	}
	
	private static class Node {
		public WordFrequency item;
		public Node left;
		public Node right;
		
		public Node(WordFrequency item) {
			this.item = item;
		}
		
	}
}
