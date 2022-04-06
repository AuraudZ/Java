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

        if (rootOfNameTree == null && rootOfBirthDateTree == null) {
            rootOfNameTree = new Node(p);
            rootOfBirthDateTree = new Node(p);
            size++;
            return true;
        }
        Integer birthDay = p.birthDay;
        Integer birthMonth = p.birthMonth;
        Integer birthYear = p.birthYear;
        Node runner = rootOfBirthDateTree;
        Node root = rootOfBirthDateTree;
        while (true) {
            // Puts in the name tree
            if (runner.item.firstName.equals(p.firstName) && runner.item.lastName.equals(p.lastName)) {
                if (birthDaysEqual(runner.item, p)) {
                    return false;
                }
            }


            // Puts in the birthdate tree
            if (p.equals(runner.item)) {
                return false;
            }
            if (birthDaysEqual(p, rootOfBirthDateTree.item) && namesEqual(p, root.item)) {
                return false;
            }
            if (birthYear < runner.item.birthYear) {
                if (runner.left == null) {
                    runner.left = new Node(p);
                    size++;
                    return true;
                }
                runner = runner.left;
            } else if (birthYear > runner.item.birthYear) {
                if (runner.right == null) {
                    runner.right = new Node(p);
                    size++;
                    return true;
                }
                runner = runner.right;
            } else {
                if (birthMonth < runner.item.birthMonth) {
                    if (runner.left == null) {
                        runner.left = new Node(p);
                        size++;
                        return true;
                    }
                    runner = runner.left;
                } else if (birthMonth > runner.item.birthMonth) {
                    if (runner.right == null) {
                        runner.right = new Node(p);
                        size++;
                        return true;
                    }
                    runner = runner.right;
                } else {
                    if (birthDay < runner.item.birthDay) {
                        if (runner.left == null) {
                            runner.left = new Node(p);
                            size++;
                            return true;
                        }
                        runner = runner.left;
                    } else if (birthDay > runner.item.birthDay) {
                        if (runner.right == null) {
                            runner.right = new Node(p);
                            size++;
                            return true;
                        }
                        runner = runner.right;
                    }
                    return false;
                }
            }
        }
    }




    /**
     * Helper method for put
     *
     * @param p         person
     * @param lastName  last name
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
        while (true) {
            if (current.item.equals(p)) {
                return false;
            }
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
            if (current.item.lastName.compareTo(lastName) == 0) {
                if (current.item.firstName.compareTo(firstName) > 0) {
                    if (current.left == null) {
                        current.left = new Node(p);
                        size++;
                        return true;
                    }
                    current = current.left;
                }
                if (current.item.firstName.compareTo(firstName) < 0) {
                    if (current.right == null) {
                        current.right = new Node(p);
                        size++;
                        return true;
                    }
                    current = current.right;
                }
                if (current.item.firstName.compareTo(firstName) == 0) {
                    if (!birthDaysEqual(current.item, p)) {
                        if (current.left == null) {
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


    private boolean birthDaysEqual(Person p1, Person p2) {
        return p1.birthDay == p2.birthDay && p1.birthMonth == p2.birthMonth && p1.birthYear == p2.birthYear;
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
        List<Person> list = new ArrayList<>();
        Node runner = rootOfNameTree;
        while (runner != null) {
            if(runner.item.firstName.equals(firstName) && runner.item.lastName.equals(lastName)) {
                list.add(runner.item);
            }
            if(runner.left != null && runner.left.item.firstName.equals(firstName) && runner.left.item.lastName.equals(lastName)) {
                list.add(runner.left.item);
            }
            if(runner.right != null && runner.right.item.firstName.equals(firstName) && runner.right.item.lastName.equals(lastName)) {
                list.add(runner.right.item);
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
        Integer day = birthDay;
        Integer month = birthMonth;
        Integer year = birthYear;
        List<Person> list = new ArrayList<Person>();
        Node current = rootOfBirthDateTree;
        while (current != null) {
            if (year.compareTo(current.item.birthYear) == 0) {
                if (month.compareTo(current.item.birthMonth) == 0) {
                    if (day.compareTo(current.item.birthDay) == 0) {
                        list.add(current.item);
                    }
                    if (day.compareTo(current.item.birthDay) < 0) {
                        current = current.left;
                    }
                    if (day.compareTo(current.item.birthDay) > 0) {

                        current = current.right;
                    }
                }
                if (month.compareTo(current.item.birthMonth) > 0) {

                    current = current.right;
                }
                if (month.compareTo(current.item.birthMonth) < 0) {

                    current = current.left;
                }
            }
            if (year.compareTo(current.item.birthYear) > 0) {
                current = current.right;
            }
            if(current == null){
                return list;
            }

            if (year.compareTo(current.item.birthYear) < 0) {
                if (current.left == null) {
                    return list;
                }
                current = current.left;
            }
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
