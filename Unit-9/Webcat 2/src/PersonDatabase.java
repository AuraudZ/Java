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

    /**
     * The number of nodes in the tree. Both trees should have the same number of nodes.
     */
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

        if (name || bday) {
            size++;
            return true;
        }
        return false;
    }

    private boolean putBirthDay(Person p) {
        Node runner = rootOfBirthDateTree;
        Node insert = new Node(p);

        if (rootOfBirthDateTree == null) {
            rootOfBirthDateTree = new Node(p);
            return true;
        }
        if (runner == null) {
            rootOfBirthDateTree = insert;
            return true;
        }
        int comp;
        while (true) {
            comp = compareBday(runner.item, insert);
            if (comp > 0) { // Older so we need to right
                if (runner.right == null) {
                    runner.right = insert;
                    return true;
                }
                runner = runner.right;
            } else if (comp < 0) { // Younger so we need to left
                if (runner.left == null) {
                    runner.left = insert;
                    return true;
                }
                runner = runner.left;
            } else { // Equal so we need to check if birthdays are equal
                String insName = p.lastName + ", " + p.firstName; // Just concatenate the names so it's easier to compare
                String curName = runner.item.lastName + ", " + runner.item.firstName;
                if (insName.equals(curName)) {
                    return false;
                } else {
                    if (runner.left == null) {
                        runner.left = insert;
                        return true;
                    }
                    runner = runner.left;
                }
            }
        }
    }

    private boolean putName(Person p) {
        Node runner = rootOfNameTree;
        if (rootOfNameTree == null) {
            rootOfNameTree = new Node(p);
            size++;
            return true;
        }
        while (true) {
            if (p.equals(runner.item)) {
                return false;
            }
            if (nameLess(p, runner.item)) {
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

    private boolean nameLess(Person p1, Person p2) {
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
        List<Person> result = new ArrayList<>();
        return find(firstName, lastName, rootOfNameTree, result);
    }

    private List<Person> find(String firstName, String lastName, Node root, List<Person> result) {
        if (root == null) {
            return result;
        }
        String fullName = lastName + ", " + firstName;
        String curName = root.item.lastName + ", " + root.item.firstName;
        if (fullName.compareTo(curName) == 0) { // Found the name
            result.add(root.item);
            return find(firstName, lastName, root.left, result); // We need to search left because that is where the duplicates are
        }
        if (fullName.compareTo(curName) > 0) {
            return find(firstName, lastName, root.right, result);
        }
        return find(firstName, lastName, root.left, result);
    }

    private boolean fullNameLess(Person p1, Person p2) {
        String p1FullName = p1.lastName + ", " + p1.firstName;
        String p2FullName = p2.lastName + ", " + p2.firstName;
        return p1FullName.compareTo(p2FullName) < 0;
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
        List<Person> results = new ArrayList<>();
        Person temp = new Person("", "", birthDay, birthMonth, birthYear);
        return find(temp, rootOfBirthDateTree, results);
    }

    private List<Person> find(Person p, Node runner, List<Person> result) {
        if (runner == null) {
            return result;
        }

        int diff = compareBday(p, runner);
        if (diff == 0) { // if the birthdays are equal then we found the person
            result.add(runner.item);
            return find(p, runner.left, result); // We need to go left because we want to find all people with the same birthday
        }
        if (diff > 0) {
            return find(p, runner.right, result);
        }
        return find(p, runner.left, result);
    }

    private int compareBday(Person person, Node node) {
        // true is root is bigger,
        if (person.birthYear > node.item.birthYear) {
            return 1;
        } else if (person.birthYear < node.item.birthYear) {
            return -1;
        } else {
            if (person.birthMonth > node.item.birthMonth) {
                return 1;
            } else if (person.birthMonth < node.item.birthMonth) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    private boolean nameBigger(Person p1, String lastName, String firstName) {
        return p1.lastName.compareTo(lastName) > 0
                || (p1.lastName.equals(lastName) && p1.firstName.compareTo(firstName) > 0);
    }

    private boolean nameSmaller(Person p1, String lastName, String firstName) {
        return p1.lastName.compareTo(lastName) < 0
                || (p1.lastName.equals(lastName) && p1.firstName.compareTo(firstName) < 0);
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

    private boolean birthDayBigger(Person p1, Person p2) {
        return birthDayBigger(p1, p2.birthYear, p2.birthMonth, p2.birthDay);
    }

    private boolean birthDaySmaller(Person p1, Person p2) {
        return birthDaySmaller(p1, p2.birthYear, p2.birthMonth, p2.birthDay);
    }


    // ***** For testing purposes
    public Node getNameRoot() {
        return rootOfNameTree;
    }

    public Node getBDayRoot() {
        return rootOfBirthDateTree;
    }
}
