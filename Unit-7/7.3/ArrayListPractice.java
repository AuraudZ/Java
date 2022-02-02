import java.util.ArrayList;

/*
 * All Exercises property of UW Practice It! (http://practiceit.cs.washington.edu ), Written by
 * Marty Stepp and Jeff Prouty
 */
public class ArrayListPractice {

	/*
	 * Write a method switchPairs that switches the order of values in an ArrayList of Strings in a
	 * pairwise fashion. Your method should switch the order of the first two values, then switch
	 * the order of the next two, switch the order of the next two, and so on. For example {"four",
	 * "score", "and", "seven", "years", "ago"} ---> {"score", "four", "seven", "and", "ago",
	 * "years"} If there are an odd number of values in the list, the final element is not moved.
	 * For example {"to", "be", "or", "not", "to", "be", "hamlet"} ---> {"be", "to", "not", "or",
	 * "be", "to", "hamlet"}
	 */
	public static void switchPairs(ArrayList<String> strings) {
		for (int i = 0; i < strings.size() / 2; i++) {
			String temp = strings.get(i);
			strings.set(i, strings.get(strings.size() - 1 - i));
			strings.set(strings.size() - 1 - i, temp);
		}
	}

	/*
	 * Write a method markLength4 that takes an ArrayList of Strings as a parameter and that places
	 * a string of four asterisks "****" in front of every string of length 4. For example, suppose
	 * that a variable called list contains the following values: {"this", "is", "lots", "of",
	 * "fun", "for", "every", "Java", "programmer"} markLength4(list); --> list should store the
	 * following values after the call: {"****", "this", "is", "****", "lots", "of", "fun", "for",
	 * "every", "****", "Java", "programmer"}
	 */
	public static void markLength4(ArrayList<String> strings) {
		if (strings.size() == 0) {
			return;
		}
		for (int i = 0; i < strings.size(); i++) {
			if (strings.get(i).length() == 4) {
				strings.add(i, "****");
				i++;
			}
		}
	}

	/*
	 * Write a method removeBadPairs that accepts an ArrayList of integers and removes any adjacent
	 * pair of integers in the list if the left element of the pair is larger than the right element
	 * of the pair. For example, in [3,7,9,2,5,5,8,5,6,3,4,7,3,1], think of this list as a sequence
	 * of pairs: (3, 7), (9, 2), (5, 5), (8, 5), (6, 3), (4, 7), (3, 1). The pairs (9, 2), (8, 5),
	 * (6, 3), and (3, 1) are "bad" because the left element is larger than the right one, so these
	 * pairs should be removed. So the call of removeBadPairs(list); would change the list to store:
	 * [3,7,5,5,4,7] If the list has an odd length, the last element is not part of a pair and is
	 * also considered "bad;" it should therefore be removed by your method. If an empty list is
	 * passed in, the list should still be empty at the end of the call. You may assume that the
	 * list passed is not null. You may not use any other arrays, lists, or other data structures to
	 * help you solve this problem
	 */
	public static void removeBadPairs(ArrayList<Integer> list) {
		if (list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size() - 1; i += 2) {
			if (list.get(i) > list.get(i + 1)) {
				list.remove(i);
				list.remove(i);
				i -= 2;
			}
		}
	}



	// **** ------ main() method for testing + helper methods are below --------
	public static void main(String[] args) {
		System.out.println("--------Miscellaneous ArrayList testing--------");
		// declare ArrayList holding ints called 'list'
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(4); // Integer object is created automatically: "autoboxing"
		list.add(new Integer(8)); // this is also OK but is not recommended
		list.add(15);
		list.add(16);
		list.add(23);
		// *** add 32 to end of the ArrayList
		System.out.println(list.toString());
		System.out.println("Element at index 3: " + list.get(3));

		// *** add Integer 99 between 4 and 8 in list
		list.add(2, 99);
		System.out.println(list.toString());
		System.out.println("Element at index 3: " + list.get(3));

		// *** loop through ArrayList (for:each also works), print out 2x each number
		for (int i : list) {
			System.out.println(i * 2);
		}

		// *** remove element at index 2
		list.remove(2);
		System.out.println(list.toString());
		System.out.println("Element at index 3: " + list.get(3));


		System.out.println("\n\n--------Start of switchPairs() test--------");
		ArrayList<String> test = new ArrayList<String>();
		addStringsToArrayList(test, new String[] {"four", "score", "and", "seven", "years", "ago"});

		System.out.println("Before switchPairs(): " + test.toString());
		switchPairs(test);
		System.out.println("After switchPairs(): " + test.toString());

		System.out.println("--------");
		ArrayList<String> test1 = new ArrayList<String>();
		addStringsToArrayList(test1,
				new String[] {"one", "two", "three", "four", "five", "six", "seven"});
		System.out.println("Before switchPairs(): " + test1.toString());
		switchPairs(test1);
		System.out.println("After switchPairs(): " + test1.toString());

		System.out.println("--------End of switchPairs() test--------");

		System.out.println("\n");

		System.out.println("--------Start of markLength4() test--------");
		ArrayList<String> test2 = new ArrayList<String>();
		addStringsToArrayList(test2, new String[] {"this", "is", "lots", "of", "fun", "for",
				"every", "Java", "programmer"});

		System.out.println("Before markLength4(): " + test2.toString());
		markLength4(test2);
		System.out.println("After markLength4(): " + test2.toString());

		System.out.println("--------");

		ArrayList<String> test3 = new ArrayList<String>();
		addStringsToArrayList(test3, new String[] {"one", "two", "three", "four", "five", "six",
				"seven", "eight", "nine"});

		System.out.println("Before markLength4(): " + test3.toString());
		markLength4(test3);
		System.out.println("After markLength4(): " + test3.toString());
		System.out.println("--------End of markLength4() test--------");

		System.out.println("\n");

		System.out.println("--------Start of removeBadPairs() test--------");
		ArrayList<Integer> test4 = new ArrayList<Integer>();
		addIntsToArrayList(test4, new int[] {3, 7, 9, 2, 5, 5, 8, 5, 6, 3, 4, 7, 3, 1});

		System.out.println("Before removeBadPairs(): " + test4.toString());
		removeBadPairs(test4);
		System.out.println("After removeBadPairs(): " + test4.toString());

		System.out.println("--------");

		ArrayList<Integer> test5 = new ArrayList<Integer>();
		addIntsToArrayList(test5, new int[] {99, 90, 77, 70, 63, 60, 58, 58, 49, 40, 35});
		System.out.println("Before removeBadPairs(): " + test5.toString());
		removeBadPairs(test5);
		System.out.println("After removeBadPairs(): " + test5.toString());

		System.out.println("--------End of removeBadPairs() test--------");


	}

	public static void addStringsToArrayList(ArrayList<String> list, String[] items) {
		for (String item : items)
			list.add(item);
	}

	public static void addIntsToArrayList(ArrayList<Integer> list, int[] items) {
		for (int item : items) {
			list.add(item);
		}
	}

}
