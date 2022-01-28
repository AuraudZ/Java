/**
 * Reads a list of non-zero numbers from the user, then prints out the input numbers in the reverse
 * of the order in which the were entered. There is no limit on the number of inputs.
 */
public class ReverseInputUsingDynamicArray {

	public static void main(String[] args) {
		// **declare dynamic data structure to hold numbers
		DynamicArrayOfInts list = new DynamicArrayOfInts(); // Let's use our new, custom, dynamic
															// data type


		System.out.println("Enter a non-zero integer and it will be added to the list.\n"
				+ "Enter 0 to end. \nEnter 's' to set an int.  \nEnter 'r' to remove an int.\nEnter 'a' to add an int at a certain index");
		while (true) {
			System.out.print("? ");
			String input = TextIO.getlnString();
			if (input.equals("0"))
				break;
			else if (input.toLowerCase().equals("s")) {
				int index;
				do { // say in this case we forgot to check that index was OK...
					System.out.print("Name the index of the int you want to replace: (0 - "
							+ (list.size() - 1) + "): ");
					index = TextIO.getlnInt();
				} while (index < 0 || index >= list.size());
				System.out.print("Name the replacement int: ");
				int newInt = TextIO.getlnInt();
				list.set(index, newInt); // ** perform replacement
			} else if (input.toLowerCase().equals("r")) {
				int index;
				do {
					System.out.print("Name the index of the int you want to remove: (0 - "
							+ (list.size() - 1) + "): ");
					index = TextIO.getlnInt();
				} while (index < 0 || index >= list.size());
				list.remove(index); // ** perform removal
			} else if (input.toLowerCase().equals("a")) {
				int index;

				do { // say in this case we forgot to check that index was OK...
					System.out.print("Name the index of the int you want to add to: (0 - "
							+ (list.size() - 1) + "): ");
					index = TextIO.getlnInt();
				} while (index < 0 || index >= list.size());

				System.out.print("Number to add at the index: ");
				int num = TextIO.getlnInt();
				list.add(index, num); // ** perform addition
			} else {
				// ** perform addition to list. Notice how we, as the users of this
				// dynamic array do NOT have to worry about the out-of-bounds case.
				// our data structure handles it internally.
				list.add(Integer.parseInt(input));
			}


			// For demonstration
			System.out.println("Size: " + list.size() + ", Array contents: " + list);
		}
		System.out.println();
		System.out.println("Your numbers in reverse are:");
		for (int i = list.size() - 1; i >= 0; i--) {
			System.out.printf("%10d%n", list.get(i));
		}
	}

}
