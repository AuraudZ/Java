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
    boolean name = putName(p);
    boolean bday = putBirthDay(p);
    // Will run if either name or bday is true then it will increment size and return true.

    if (name || bday) {
      size++;
      return true;
    }
    return false;
  }

  private boolean putBirthDay(Person p) {
    Node runner = rootOfBirthDateTree;
    if (rootOfBirthDateTree == null) {
      rootOfBirthDateTree = new Node(p);
      return true;
    }
    while (true) {
      if (p.equals(runner.item)) {
        return false;
      }
      if (birthGreater(p, runner.item)) {
        if (runner.right == null) {
          runner.right = new Node(p);
          return true;
        }
        runner = runner.right;
      } else {
        if (runner.left == null) {
          runner.left = new Node(p);
          return true;
        }
        runner = runner.left;
      }
    }
  }

  private boolean putName(Person p) {
    Node runner = rootOfNameTree;
    if (rootOfNameTree == null) {
      rootOfNameTree = new Node(p);
      return true;
    }
    while (true) {
      if (p.equals(runner.item)) {
        return false;
      }
      if (nameGreater(p, runner.item)) {
        if (runner.right == null) {
          runner.right = new Node(p);
        }
        runner = runner.right;
      } else {
        if (runner.left == null) {
          runner.left = new Node(p);
        }
        runner = runner.left;
      }
    }
  }

  private boolean birthGreater(
      Person p1, Person p2) { // Checks if p1's birthdate is greater than p2's
    if (p1.birthYear > p2.birthYear) {
      return true;
    }
    if (p1.birthYear < p2.birthYear) {
      return false;
    }
    if (p1.birthMonth > p2.birthMonth) {
      return true;
    }
    if (p1.birthMonth < p2.birthMonth) {
      return false;
    }
    if (p1.birthDay > p2.birthDay) {
      return true;
    }
    if (p1.birthDay < p2.birthDay) {
      return false;
    }
    return false;
  }

  private boolean nameGreater(
      Person p1, Person p2) { // Checks if p1's name is lexicographically greater than p2's
    // Check if the last names are the same
    int compLast = p1.lastName.compareTo(p2.lastName);
    if (compLast > 0) {
      return true;
    } else if (compLast < 0) {
      return false;
    }
    // If the last names are the same, check the first names
    int compFirst = p1.firstName.compareTo(p2.firstName);
    if (compFirst > 0) {
      return true;
    } else if (compFirst < 0) {
      return false;
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
    List<Person> result = new ArrayList<>();
    return find(firstName, lastName, rootOfNameTree, result);
  }
  // Recersive method to find all people with the given name in the name tree
  private List<Person> find(String firstName, String lastName, Node root, List<Person> result) {
    if (root == null) {
      return result;
    }
    String fullName =
        lastName + ", " + firstName; // Concat the name and makes it simple to compare less checks
    String curName = root.item.lastName + ", " + root.item.firstName;
    if (fullName.compareTo(curName) == 0) { // Found the name
      result.add(root.item);
      return find(
          firstName, lastName, root.left,
          result); // We need to search left because that is where the duplicates are
    }
    if (fullName.compareTo(curName) > 0) {
      return find(firstName, lastName, root.right, result); // If bigger we need to go right.
    }
    return find(firstName, lastName, root.left, result);
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
    List<Person> result = new ArrayList<>();
    Person t =
        new Person(
            "",
            "",
            birthDay,
            birthMonth,
            birthYear); // Creates a temporary person object to compare birth dates
    return find(t, rootOfBirthDateTree, result);
  }
  // Recursive method to find all people with the given birth date in the birth date tree
  private List<Person> find(Person p, Node root, List<Person> result) {
    if (root == null) {
      return result;
    }
    if (birthGreater(p, root.item)) {
      return find(p, root.right, result);
    }
    if (birthLess(p, root.item)) {
      return find(p, root.left, result);
    }
    result.add(
        root.item); // I did this method a bit differently than the other two because I wanted to
    // see how order would affect the results
    return find(p, root.left, result);
  }
  // Helper to compare birthdates
  private boolean birthLess(Person p, Person item) {
    if (p.birthYear < item.birthYear) {
      return true;
    }
    if (p.birthYear > item.birthYear) {
      return false;
    }
    if (p.birthMonth < item.birthMonth) {
      return true;
    }
    if (p.birthMonth > item.birthMonth) {
      return false;
    }
    return p.birthDay < item.birthDay;
  }

  private boolean birthDaySmaller(Person p1, int birthYear, int birthMonth, int birthDay) {
    return p1.birthYear < birthYear
        || (p1.birthYear == birthYear && p1.birthMonth < birthMonth)
        || (p1.birthYear == birthYear && p1.birthMonth == birthMonth && p1.birthDay < birthDay);
  }

  private boolean birthDayBigger(Person item, int birthYear, int birthMonth, int birthDay) {
    return item.birthYear > birthYear
        || (item.birthYear == birthYear && item.birthMonth > birthMonth)
        || (item.birthYear == birthYear
            && item.birthMonth == birthMonth
            && item.birthDay > birthDay);
  }

  // ***** For testing purposes
  public Node getNameRoot() {
    return rootOfNameTree;
  }

  public Node getBDayRoot() {
    return rootOfBirthDateTree;
  }
}
