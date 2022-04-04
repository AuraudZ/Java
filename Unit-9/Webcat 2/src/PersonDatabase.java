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
			return putName(p, p.lastName, p.firstName, rootOfNameTree);
		}
		if(rootOfBirthDateTree != null) {
			return putBirthDay(p, p.birthDay, p.birthMonth, p.birthYear, rootOfBirthDateTree);
		}
		return false;
	}

	private boolean putBirthDay(Person p, int birthDay, int birthMonth, int birthYear, Node root) {
		// Sorted by birthdate (ascending order)
		if (root == null) {
			root = new Node(p);
		}
		// Traverse the tree placing the nodes in the correct order
		// The order by birthdate is ascending order
		// If the name and birthdate are the same, then DO NOT add the node

		if(root.item.birthYear == birthYear && root.item.birthMonth == birthMonth && root.item.birthDay == birthDay && !(root.item.firstName.equals(p.firstName) && root.item.lastName.equals(p.lastName))) {
			return false;
		}

		if(root.item.birthYear > birthYear) {
			if(root.left == null) {
				root.left = new Node(p);
				size++;
			}
			else {
				putBirthDay(p, birthDay, birthMonth, birthYear, root.left);
			}
		}

		if(root.item.birthYear < birthYear) {
			if(root.right == null) {
				root.right = new Node(p);
				size++;
			}
			else {
				putBirthDay(p, birthDay, birthMonth, birthYear, root.right);
			}
		}

		if(root.item.birthYear == birthYear) {
			if(root.item.birthMonth > birthMonth) {
				if(root.left == null) {
					root.left = new Node(p);
				}
				else {
					putBirthDay(p, birthDay, birthMonth, birthYear, root.left);
				}
			}
		}

		return root.item.birthDay != birthDay || root.item.birthMonth != birthMonth || root.item.birthYear != birthYear;
	}

	/**
	 * Helper method for put
	 * @param p person
	 * @param lastName last name
	 * @param firstName first name
	 */
	public boolean putName(Person p, String lastName, String firstName, Node root) {
		if(root == null) {
			root = new Node(p);
			size++;
			return true;
		}

		if(root.item.lastName.equals(lastName) && root.item.firstName.equals(firstName) && root.item.birthDay == p.birthDay && root.item.birthMonth == p.birthMonth && root.item.birthYear == p.birthYear) {
			return false;
		}

		// If a node is created, it should be added to both the name tree and the birthdate tree


		if (lastName.compareTo(root.item.lastName) < 0 && firstName.compareTo(root.item.firstName) < 0) {
			if(root.left == null) {
				root.left = new Node(p);
				size++;
			}
			return putName(p, lastName, firstName, root.left);
		}
		if (lastName.compareTo(root.item.lastName) > 0 && firstName.compareTo(root.item.firstName) > 0) {
			if(root.right == null) {
				root.right = new Node(p);
				size++;
			}
			return putName(p, lastName, firstName, root.right);
		}
		if (lastName.compareTo(root.item.lastName) > 0 && firstName.compareTo(root.item.firstName) < 0) {
			if(root.right == null) {
				root.right = new Node(p);
			}
			return putName(p, lastName, firstName, root.right);
		}
		if (lastName.compareTo(root.item.lastName) < 0 && firstName.compareTo(root.item.firstName) > 0) {
			if(root.left == null) {
				root.left = new Node(p);
			}
			return putName(p, lastName, firstName, root.left);
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
		return null;
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
		List<Person> list = new ArrayList<Person>();
		if(rootOfBirthDateTree == null) {
			return list;
		}

		find(birthDay, birthMonth, birthYear, rootOfBirthDateTree,list);
		return list;
	}

	private List<Person> find(int birthDay, int birthMonth, int birthYear, Node root, List<Person> list) {
		if(root == null) {
			return new ArrayList<Person>();
		}
		// Base case if left and right are null
		if(root.left == null && root.right == null) {
			return list;
		}

		if(rootOfBirthDateTree.item.birthDay == birthDay && rootOfBirthDateTree.item.birthMonth == birthMonth && rootOfBirthDateTree.item.birthYear == birthYear) {
			list.add(rootOfBirthDateTree.item);
		}
		if(rootOfBirthDateTree.left != null) {
			find(birthDay, birthMonth, birthYear, rootOfBirthDateTree.left, list);
		}
		if(rootOfBirthDateTree.right != null) {
			find(birthDay, birthMonth, birthYear, rootOfBirthDateTree.right, list);
		}
		return list;
	}


	//***** For testing purposes
	public Node getNameRoot() {
		return rootOfNameTree;
	}
	
	public Node getBDayRoot() {
		return rootOfBirthDateTree;
	}
	

}
