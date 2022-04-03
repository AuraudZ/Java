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
		putBirthDate(p, p.birthDay, p.birthMonth, p.birthYear);
		putName(p, p.lastName, p.firstName);
		return false;
	}

  private boolean putBirthDate(Person p, int birthDay, int birthMonth, int birthYear) {
    // The tree allows for duplicate birthdates (as long as the names are different)
    // So we need to check all the birthdate in the tree and then compare them with names
    Node current = rootOfBirthDateTree;
    while (current != null) {
      if (birthDay < current.item.birthDay) {
        if (current.left == null) {
          current.left = new Node(p);
          size++;
          return true;
        }
        current = current.left;
      } else if (birthDay > current.item.birthDay) {
        if (current.right == null) {
          current.right = new Node(p);
          size++;
          return true;
        }
        current = current.right;
      } else {
        if (birthMonth < current.item.birthMonth) {
          if (current.left == null) {
            current.left = new Node(p);
            size++;
            return true;
          }
          current = current.left;

        } else if (birthMonth > current.item.birthMonth) {
          if (current.right == null) {
            current.right = new Node(p);
            size++;
            return true;
          }
          current = current.right;
        } else {
          if (birthYear < current.item.birthYear) {
            if (current.left == null) {
              current.left = new Node(p);
              size++;
              return true;
            }
            current = current.left;

          } else if (birthYear > current.item.birthYear) {
            if (current.right == null) {
              current.right = new Node(p);
              size++;
              return true;
            }
            current = current.right;
          } else {
            // The birthdate is the same as the current node
            // So we need to check the names
            if (p.lastName.compareTo(current.item.lastName) < 0) {
              if (current.left == null) {
                current.left = new Node(p);
                size++;
                return true;
              }
              current = current.left;
            } else if (p.lastName.compareTo(current.item.lastName) > 0) {
              if (current.right == null) {
                current.right = new Node(p);
                size++;
                return true;
              }
              current = current.right;
            } else {
              // The last name is the same as the current node
              // So we need to check the names
              if (p.firstName.compareTo(current.item.firstName) < 0) {
                if (current.left == null) {
                  current.left = new Node(p);
                  size++;
                  return true;
                }
                current = current.left;
              } else if (p.firstName.compareTo(current.item.firstName) > 0) {
                if (current.right == null) {
                  current.right = new Node(p);
                  size++;
                  return true;
                }
                current = current.right;
              }
            }
          }
        }
      }
    }
	return false;
	}

	private void putName(Person p, String lastName, String firstName) {
		// Sort the name
		if(rootOfNameTree == null) {
			rootOfNameTree = new Node(p);
			size++;
			return;
		}
		Node current = rootOfNameTree;
		while(true) {
			if(lastName.compareTo(current.item.lastName) < 0) {
				if(current.left == null) {
					current.left = new Node(p);
					size++;
					return;
				}
				current = current.left;
			}
			else if(lastName.compareTo(current.item.lastName) > 0) {
				if(current.right == null) {
					current.right = new Node(p);
					size++;
					return;
				}
				current = current.right;
			}
			else {
				if(firstName.compareTo(current.item.firstName) < 0) {
					if(current.left == null) {
						current.left = new Node(p);
						size++;
						return;
					}
					current = current.left;
				}
				else if(firstName.compareTo(current.item.firstName) > 0) {
					if(current.right == null) {
						current.right = new Node(p);
						size++;
						return;
					}
					current = current.right;
				}
				else {
					// duplicate name
					return;
				}
			}
		}
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

		// Cases:
		// 1. root is the name
		// 2. We need to go to the left
		// 3. We need to go to the right

		// Create a list to store the results
		List<Person> results = new ArrayList<Person>();

		// Start at the root
		Node current = rootOfNameTree;
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
		return null;
	}
	
	
	//***** For testing purposes
	public Node getNameRoot() {
		return rootOfNameTree;
	}
	
	public Node getBDayRoot() {
		return rootOfBirthDateTree;
	}
	

}
