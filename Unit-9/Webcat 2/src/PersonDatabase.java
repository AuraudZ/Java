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

    if (rootOfNameTree == null) {
      rootOfNameTree = new Node(p);
      size++;
      return true;
    }
    if(rootOfBirthDateTree == null){
      rootOfBirthDateTree = new Node(p);
      size++;
      return true;
    }

    int birthDay = p.birthDay;
    int birthMonth = p.birthMonth;
    int birthYear = p.birthYear;
    String lastName = p.lastName;
    String firstName = p.firstName;
    Node runnerNameTree = rootOfNameTree;
    Node runnerBirthDateTree = rootOfBirthDateTree;
    while (true) {
      if (runnerNameTree.item.equals(p)) {
        return false;
      }
      if(birthDaysEqual(runnerNameTree.item, p) && namesEqual(runnerNameTree.item, p)){
        return false;
      }
      if (runnerNameTree.item.lastName.compareTo(lastName) > 0) {
        if (runnerNameTree.left == null) {
          runnerNameTree.left = new Node(p);
          size++;
          return true;
        }
        runnerNameTree = runnerNameTree.left;
      }
      if (runnerNameTree.item.lastName.compareTo(lastName) < 0) {
        if (runnerNameTree.right == null) {
          runnerNameTree.right = new Node(p);
          size++;
          return true;
        }
        runnerNameTree = runnerNameTree.right;
      }
      if (runnerNameTree.item.lastName.compareTo(lastName) == 0) {
        if (runnerNameTree.item.firstName.compareTo(firstName) > 0) {
          if (runnerNameTree.left == null) {
            runnerNameTree.left = new Node(p);
            size++;
            return true;
          }
          runnerNameTree = runnerNameTree.left;
        }
        if (runnerNameTree.item.firstName.compareTo(firstName) < 0) {
          if (runnerNameTree.right == null) {
            runnerNameTree.right = new Node(p);
            size++;
            return true;
          }
          runnerNameTree = runnerNameTree.right;
        }
        if (runnerNameTree.item.firstName.compareTo(firstName) == 0) {
          if (!birthDaysEqual(runnerNameTree.item, p)) {
            if (runnerNameTree.left == null) {
              runnerNameTree.left = new Node(p);
              size++;
              return true;
            }
            runnerNameTree = runnerNameTree.left;
          }
          if (birthDaysEqual(runnerNameTree.item, p)) {
            if (runnerNameTree.right == null) {
              runnerNameTree.right = new Node(p);
              size++;
              return true;
            }
            runnerNameTree = runnerNameTree.right;
          }
        }
      }

      // Puts in the birthdate tree
      if (p.equals(runnerBirthDateTree.item)) {
        return false;
      }
      if (birthDaysEqual(p, rootOfBirthDateTree.item) && namesEqual(p, rootOfBirthDateTree.item)) {
        return false;
      }
      if (birthYear < runnerBirthDateTree.item.birthYear) {
        if (runnerBirthDateTree.left == null) {
          runnerBirthDateTree.left = new Node(p);
          size++;
          return true;
        }
        runnerBirthDateTree = runnerBirthDateTree.left;
      } else if (birthYear > runnerBirthDateTree.item.birthYear) {
        if (runnerBirthDateTree.right == null) {
          runnerBirthDateTree.right = new Node(p);
          size++;
          return true;
        }
        runnerBirthDateTree = runnerBirthDateTree.right;
      } else {
        if (birthMonth < runnerBirthDateTree.item.birthMonth) {
          if (runnerBirthDateTree.left == null) {
            runnerBirthDateTree.left = new Node(p);
            size++;
            return true;
          }
          runnerBirthDateTree = runnerBirthDateTree.left;
        } else if (birthMonth > runnerBirthDateTree.item.birthMonth) {
          if (runnerBirthDateTree.right == null) {
            runnerBirthDateTree.right = new Node(p);
            size++;
            return true;
          }
          runnerBirthDateTree = runnerBirthDateTree.right;
        } else {
          if (birthDay < runnerBirthDateTree.item.birthDay) {
            if (runnerBirthDateTree.left == null) {
              runnerBirthDateTree.left = new Node(p);
              size++;
              return true;
            }
          } else if (birthDay > runnerBirthDateTree.item.birthDay) {
            if (runnerBirthDateTree.right == null) {
              runnerBirthDateTree.right = new Node(p);
              size++;
              return true;
            }
          }
          return false;
        }
      }
    }
  }

  private boolean birthDaysEqual(Person p1, Person p2) {
    return p1.birthDay == p2.birthDay
        && p1.birthMonth == p2.birthMonth
        && p1.birthYear == p2.birthYear;
  }

  private boolean namesEqual(Person p1, Person p2) {
    return p1.lastName.equals(p2.lastName) && p1.firstName.equals(p2.firstName);
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
    return find(rootOfNameTree, firstName, lastName);
  }

  private List<Person> find(Node root, String firstName, String lastName) {
    List<Person> list = new ArrayList<>();
    if (root == null) {
      return list;
    }
    if (root.item.lastName.equals(lastName) && root.item.firstName.equals(firstName)) {
      list.add(root.item);
    }
    if (nameSmaller(root.item, firstName, lastName)) {
      if (root.left != null) {
        list.addAll(find(root.left, firstName, lastName));
      }
    }
    if (nameBigger(root.item, firstName, lastName)) {
      if (root.right != null) {
        list.addAll(find(root.right, firstName, lastName));
      }
    }
    return list;
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
    return find(rootOfBirthDateTree, birthDay, birthMonth, birthYear);
  }

  private List<Person> find(Node current, int birthDay, int birthMonth, int birthYear) {
    List<Person> list = new ArrayList<>();
    if (current == null) {
      return list;
    }
    if (current.item.birthDay == birthDay
        && current.item.birthMonth == birthMonth
        && current.item.birthYear == birthYear) {
      list.add(current.item);
    }
    // This needs to log(n) time, where n is the number of nodes in the tree.
    // To do this, we need to know that it is sorted by birth date. // Left being smaller than right
    if (birthDateSmaller(current.item, birthYear, birthMonth, birthDay)) {
      if (current.left != null) {
        list.addAll(find(current.left, birthDay, birthMonth, birthYear));
      }
    }
    if (birthDayBigger(current.item, birthYear, birthMonth, birthDay)) {
      if (current.right != null) {
        list.addAll(find(current.right, birthDay, birthMonth, birthYear));
      }
    }
    return list;
  }

  private boolean nameBigger(Person p1, String lastName, String firstName) {
    return p1.lastName.compareTo(lastName) > 0
        || (p1.lastName.equals(lastName) && p1.firstName.compareTo(firstName) > 0);
  }

  private boolean nameSmaller(Person p1, String lastName, String firstName) {
    return p1.lastName.compareTo(lastName) < 0
        || (p1.lastName.equals(lastName) && p1.firstName.compareTo(firstName) < 0);
  }

  private boolean birthDateSmaller(Person p1, int birthYear, int birthMonth, int birthDay) {
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
