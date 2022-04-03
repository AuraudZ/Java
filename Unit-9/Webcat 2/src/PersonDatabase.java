import java.util.ArrayList;
import java.util.List;

public class PersonDatabase {

	/**
	 * This Node is the root of a tree of Person
	 * objects that is sorted by last
	 * name and then first name (ignoring case).
	 * This tree allows duplicate names (as long as
	 * the birth dates are different).
	 */
	private Node rootOfNameTree;
	
	/**
	 * This Node is the root of a tree 
	 * of Person objects that is sorted by birthdate. This tree allows duplicate
	 * birthdate (as long as the names are
	 * different).
	 */
	private Node rootOfBirthDateTree;

	/**
	 * The number of nodes in the tree.
	 * Both trees should have the same
	 * number of nodes.
	 */
	private int size;
	
	
	/**
	 * Returns number of Persons in
	 * the database
	 * @return number of Persons
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Add person to the database unless 
	 * a Person object that is equal already
	 * exists. If a node is created, it 
	 * should be added to both the name tree
	 * and the birthdate tree.
	 * @param p  a person
	 * @return true if person is added, false otherwise
	 */
	public boolean put(Person p) {
		//hint: create two private put methods
		// for each of the trees and call
		// them here
		// Put the person in both trees using the 2 helper methods
		if(rootOfNameTree == null) {
			rootOfNameTree = new Node(p);
		}
		if(rootOfBirthDateTree == null) {
			rootOfBirthDateTree = new Node(p);
		}
		if(rootOfNameTree != null) {
			putName(p, p.lastName, p.firstName, rootOfNameTree);
		}
		return false;
	}

	/**
	 * Helper method for put
	 * @param p person
	 * @param lastName last name
	 * @param firstName first name
	 */
	public boolean putName(Person p, String lastName, String firstName, Node root) {
		// Check if the root is null
		// Sort by name if name is the same and if the birthday is different from it is okay
		// if the name is different from we need to go to the left or right and add it
		// to the tree
		if(root == null) {
			root = new Node(p);
			size++;
			return true;
		}
		else if(lastName.compareTo(root.item.lastName) == 0 && firstName.compareTo(root.item.firstName) == 0) {
			// If the name is the same, we need to check the birthdate
			if(p.birthDay == root.item.birthDay && p.birthMonth == root.item.birthMonth && p.birthYear == root.item.birthYear) {
				// Add the person to the tree
				root.item = p;
				size++;
				return true;
			}
			else if (p.birthDay < root.item.birthDay || p.birthMonth < root.item.birthMonth || p.birthYear < root.item.birthYear) {
				// If the name is the same but the birthdate is different, we need to go to the left
				putName(p, lastName, firstName, root.left);
			}
			else {
				// If the name is the same but the birthdate is different, we need to go to the right
				putName(p, lastName, firstName, root.right);
			}

		}
		else if(lastName.compareTo(root.item.lastName) < 0) {
			// If the name is less than the root, we need to go to the left
			// Make sure the birthdate is different
			if(p.birthDay != root.item.birthDay || p.birthMonth != root.item.birthMonth || p.birthYear != root.item.birthYear) {
				// Add the person to the tree
				root.left = new Node(p);
				size++;
				return true;
			}
			else {
				putName(p, lastName, firstName, root.left);
			}
		}
		else {
			// If the name is greater than the root, we need to go to the right
			// Make sure the birthdate is different
			if(p.birthDay != root.item.birthDay || p.birthMonth != root.item.birthMonth || p.birthYear != root.item.birthYear) {
				// Add the person to the tree
				root.right = new Node(p);
				size++;
				return true;
			}
			else {
				putName(p, lastName, firstName, root.right);
			}
		}
		return false;
	}
	/**
	 * Returns a list of all Person objects in the database with the given name.
	 * This method should search in name tree.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return a list of Person objects (possibly empty)
	 */
	public List<Person> find(String firstName, String lastName) {
		// Run through the tree and find the name
		// It is a binary search tree so it should be easy
		if(rootOfNameTree == null) {
			return null;
		}
		// Create a list to store the results
		List<Person> results = new ArrayList<Person>();
		Node current = rootOfNameTree;
		while (current != null) {
			// If the name is the same, we need to check the birthdate
			if(lastName.compareTo(current.item.lastName) == 0 && firstName.compareTo(current.item.firstName) == 0) {
				// Add the person to the list
//				results.add(current.item);
			}
			else if(lastName.compareTo(current.item.lastName) < 0) {
				// If the name is less than the root, we need to go to the left
				current = current.left;
			}
			else {
				// If the name is greater than the root, we need to go to the right
				current = current.right;
			}
		}
		return results;
	}

	/**
	 * Returns a list of all Person objects in the database with the given birth
	 * date. This method should search in the birth date tree.
	 * 
	 * @param birthDay
	 * @param birthMonth
	 * @param birthYear
	 * @return a list of Person objects (possibly empty)
	 */
	public List<Person> find(int birthDay, int birthMonth, int birthYear) {
		// Run through the tree and find the birth date
		// It is a binary search tree so it should be easy
		if(rootOfBirthDateTree == null) {
			return null;
		}
		Node current = rootOfBirthDateTree;
		List<Person> results = new ArrayList<Person>();
		while (current != null) {
			if(current.item.birthDay == birthDay && current.item.birthMonth == birthMonth && current.item.birthYear == birthYear) {
			//	results.add(current.item);
			}
			if(current.item.birthDay < birthDay && current.item.birthMonth < birthMonth && current.item.birthYear < birthYear) {
				current = current.right;
			}
			else {
				current = current.left;
			}
		}
		return results;
	}
	
	
	//***** For testing purposes
	public Node getNameRoot() {
		return rootOfNameTree;
	}
	
	public Node getBDayRoot() {
		return rootOfBirthDateTree;
	}
	

}
