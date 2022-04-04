import java.util.ArrayList;
import java.util.List;

public class PersonDatabase {

  /**
   * This Node is the root of a tree of Person objects that is sorted by last name and then first
   * name (ignoring case). This tree allows duplicate names (as long as the birth dates are
   * different).
   */
  private Node rootOfNameTree;

  /**
   * This Node is the root of a tree of Person objects that is sorted by birthdate. This tree allows
   * duplicate birthdate (as long as the names are different).
   */
  private Node rootOfBirthDateTree;

  /** The number of nodes in the tree. Both trees should have the same number of nodes. */
  private int size;

  /**
   * Returns number of Persons in the database
   *
   * @return number of Persons
   */
  public int size() {
    return size;
  }

  /**
   * Add person to the database unless a Person object that is equal already exists. If a node is
   * created, it should be added to both the name tree and the birthdate tree.
   *
   * @param p a person
   * @return true if person is added, false otherwise
   */
  public boolean put(Person p) {
    // hint: create two private put methods
    // for each of the trees and call
    // them here
    // Put the person in both trees using the 2 helper methods
    if(rootOfNameTree == null && rootOfBirthDateTree == null){
      rootOfNameTree = new Node(p);
      rootOfBirthDateTree = new Node(p);
      size++;
      return true;
    }
    return putName(p, p.lastName, p.firstName, rootOfNameTree);
  }

  private boolean putBirthDay(Person p, int birthDay, int birthMonth, int birthYear, Node root) {
    return false;
  }

  /**
   * Helper method for put
   *
   * @param p person
   * @param lastName last name
   * @param firstName first name
   */
  public boolean putName(Person p, String lastName, String firstName, Node root) {
    Integer birthDay = p.birthDay;
    Integer birthMonth = p.birthMonth;
    Integer birthYear = p.birthYear;
    if (root == null) {
      root = new Node(p);
      size++;
      return true;
    }
    Node current = root;
    while (current != null) {
      // Loop through the tree until we find the right place to insert the node
      if (current.item.lastName.compareTo(lastName) > 0) {
        if (current.left == null) {
          current.left = new Node(p);
          size++;
          return true;
        }
        current = current.left;
      }
      if (current.item.lastName.compareTo(lastName) < 0) {
        if (current.right == null) {
          current.right = new Node(p);
          size++;
          return true;
        }
        current = current.right;
      }
      if(current.item.lastName.compareTo(lastName) == 0){
        if(current.item.firstName.compareTo(firstName) > 0){
          if(current.left == null){
            current.left = new Node(p);
            size++;
            return true;
          }
          current = current.left;
        }
        if(current.item.firstName.compareTo(firstName) < 0){
          if(current.right == null){
            current.right = new Node(p);
            size++;
            return true;
          }
          current = current.right;
        }
        if(current.item.firstName.compareTo(firstName) == 0){
          if(birthDay.compareTo(current.item.birthDay) > 0){
            if(current.left == null){
              current.left = new Node(p);
              size++;
              return true;
            }
            current = current.left;
          }
          if(birthDay.compareTo(current.item.birthDay) < 0){
            if(current.right == null){
              current.right = new Node(p);
              size++;
              return true;
            }
            current = current.right;
          }
          if(birthDay.compareTo(current.item.birthDay) == 0){
            if(birthMonth.compareTo(current.item.birthMonth) > 0){
              if(current.left == null){
                current.left = new Node(p);
                size++;
                return true;
              }
              current = current.left;
            }
            if(birthMonth.compareTo(current.item.birthMonth) < 0){
              if(current.right == null){
                current.right = new Node(p);
                size++;
                return true;
              }
              current = current.right;
            }
            if(birthMonth.compareTo(current.item.birthMonth) == 0){
              if(birthYear.compareTo(current.item.birthYear) > 0){
                if(current.left == null){
                  current.left = new Node(p);
                  size++;
                  return true;
                }
                current = current.left;
              }
              if(birthYear.compareTo(current.item.birthYear) < 0){
                if(current.right == null){
                  current.right = new Node(p);
                  size++;
                  return true;
                }
                current = current.right;
              }
              if(birthYear.compareTo(current.item.birthYear) == 0){
                if(current.left == null){
                  current.left = new Node(p);
                  size++;
                  return true;
                }
                current = current.left;
              }
            }
          }
        }
      }

    }
    return false;
    }

  /**
   * Returns a list of all Person objects in the database with the given name. This method should
   * search in name tree.
   *
   * @param firstName
   * @param lastName
   * @return a list of Person objects (possibly empty)
   */
  public List<Person> find(String firstName, String lastName) {
    return null;
  }

  /**
   * Returns a list of all Person objects in the database with the given birth date. This method
   * should search in the birth date tree.
   *
   * @param birthDay
   * @param birthMonth
   * @param birthYear
   * @return a list of Person objects (possibly empty)
   */
  public List<Person> find(int birthDay, int birthMonth, int birthYear) {
    List<Person> list = new ArrayList<Person>();
    if (rootOfBirthDateTree == null) {
      return list;
    }

    find(birthDay, birthMonth, birthYear, rootOfBirthDateTree, list);
    return list;
  }

  private List<Person> find(
      int birthDay, int birthMonth, int birthYear, Node root, List<Person> list) {
    if (root == null) {
      return new ArrayList<Person>();
    }
    // Base case if left and right are null
    if (root.left == null && root.right == null) {
      return list;
    }

    if (rootOfBirthDateTree.item.birthDay == birthDay
        && rootOfBirthDateTree.item.birthMonth == birthMonth
        && rootOfBirthDateTree.item.birthYear == birthYear) {
      list.add(rootOfBirthDateTree.item);
    }
    if (rootOfBirthDateTree.left != null) {
      find(birthDay, birthMonth, birthYear, rootOfBirthDateTree.left, list);
    }
    if (rootOfBirthDateTree.right != null) {
      find(birthDay, birthMonth, birthYear, rootOfBirthDateTree.right, list);
    }
    return list;
  }

  // ***** For testing purposes
  public Node getNameRoot() {
    return rootOfNameTree;
  }

  public Node getBDayRoot() {
    return rootOfBirthDateTree;
  }
}
