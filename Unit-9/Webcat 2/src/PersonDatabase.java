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

       while (true) {
           // We need to sort both trees using the differnt helper methods
           Node nameNode = rootOfNameTree;
           Node bDayNode = rootOfBirthDateTree;
           return putName(p,p.lastName,p.firstName,nameNode) ? true : putBirthDay(p,p.birthDay,p.birthMonth,p.birthYear,bDayNode);
       }
    }


    private boolean putBirthDay(Person p, int birthDay, int birthMonth, int birthYear, Node root) {
        //leverage
        if (root == null) {
            root = new Node(p);
            size++;
            return true;
        }
        Node runner = root;
        while (true) {
            if (p.equals(runner.item)) {
                return false;
            }
            if (birthDaysEqual(p, root.item) && namesEqual(p, root.item)) {
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
       return find(firstName, lastName, rootOfNameTree);
    }

    private List<Person> find (String firstName, String lastName, Node root) {
        List<Person> result = new ArrayList<>();
        Node runner = root;
        while (runner != null) {
            if(runner.right == null) {
                return result;
            }
            if(runner.left == null) {
                return result;
            }
            if (runner.item.lastName.compareTo(lastName) > 0) {
                runner = runner.left;
            }
            if (runner.item.lastName.compareTo(lastName) < 0) {
                runner = runner.right;
            }
            if (runner.item.lastName.compareTo(lastName) == 0) {
                if (runner.item.firstName.compareTo(firstName) > 0) {
                    runner = runner.left;
                }
                if (runner.item.firstName.compareTo(firstName) < 0) {
                    runner = runner.right;
                }
                if (runner.item.firstName.compareTo(firstName) == 0) {
                    result.add(runner.item);
                }
            }
        }
        return result;
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
        Node current = rootOfBirthDateTree;
        while (current != null) {
            if (current.item.birthDay == birthDay && current.item.birthMonth == birthMonth && current.item.birthYear == birthYear) {
                list.add(current.item);
            }
            if(current.item.birthYear > birthYear) {
                current = current.left;
            }
            if(current.item.birthYear < birthYear) {
                current = current.right;
            }
            if(current.item.birthYear == birthYear) {
                if(current.item.birthMonth > birthMonth) {
                    current = current.left;
                }
                if(current.item.birthMonth < birthMonth) {
                    current = current.right;
                }
                if(current.item.birthMonth == birthMonth) {
                    if(current.item.birthDay > birthDay) {
                        current = current.left;
                    }
                    if(current.item.birthDay < birthDay) {
                        current = current.right;
                    }
                }
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
