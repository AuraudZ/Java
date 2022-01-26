
// Remember, for-each loops are great at PROCESSING each element in a list, in order
// They eliminate the need for indexes. E.g. given int[] nums:
// for(int i = 0; i < nums.length; i++) sysout(nums[i]) <--- for loop
// for(int num : nums) sysout(num) <--- for-each loop (num is each VALUE, one-by-one)

// They are not good if you need to MODIFY the original list, or mess with indices
// (by looking ahead or looking back at nums[i+1] for example
// Because you have no way to modify the original array
public class ForEachPractice {

	public static void main(String[] args) {
		System.out.println("Calling outputPlusOne({10, 3, 5, 6}): ");
		outputPlusOne(new int[] {10, 3, 5, 6});
		System.out.println();

		System.out.println("Calling modifyListPlusOne({10, 3, 5, 6}): ");
		modifyListPlusOne(new int[] {10, 3, 5, 6});
		System.out.println();

		System.out.println("Check average of {10}: " + average(10));
		System.out.println("Check average of {10, 20}: " + average(10, 20));
		System.out.println("Check average of {10, 20, 30, 40, 50, 60, 70}: "
				+ average(10, 20, 30, 40, 50, 60, 70));
		System.out.println("Check average of {10, 20, 30, 40, 50, 60, 70, 999999}: "
				+ average(10, 20, 30, 40, 50, 60, 70, 999999));
		System.out.println(
				"Check average of new double[]{10, 20} : " + average(new double[] {10, 20}));
		System.out.println();

		System.out.println("Calling cleanUpNameList: ");
		cleanUpNameList(new String[] {" Shigeru Miyamoto#$", " Alexey Pajitnov#$",
				" Markus Persson#$", " Kim Swift#$"});
		System.out.println();

		System.out.println("Calling formalizeNameList: ");
		System.out.println(java.util.Arrays.toString(formalizeNameList(new String[] {
				"Shigeru Miyamoto", "Alexey Pajitnov", "Markus Persson", "Kim Swift"})));
	}


	/*
	 * Output each element in 'nums', increased by one outputPlusOne({10, 3, 5, 6} --> 11 4 6 7
	 */
	public static void outputPlusOne(int[] nums) {
		// Use the for-each syntax to iterate through the values in the array
		// Remember this processes the VALUES in the array, not the elements

		for (int num : nums) {
			System.out.println(num + 1);
		}
	}

	/*
	 * Modify each element in 'nums' to beincreased by one modifyListPlusOne({10, 3, 5, 6} --->
	 * void, but nums should point to {11, 4, 6, 7}
	 */

	public static void modifyListPlusOne(int[] nums) {
		// Try using the for-each syntax to iterate through the values in the array
		// Remember this processes the VALUES in the array, not the elements
		// Notice how it doesn't let you modify the elements in the original array
		for (int num : nums) {
			num++;
		}
	}

	// Change the method signature for average to take in any number of parameters.
	// Try to remember the syntax to define a variable arity method.
	// Remember, technically, 'numbers' or whatever you call it will be of type double[].
	public static double average(double... numbers) {

		// try to practice using a for-each loop to traverse the double[] 'numbers'
		// since for-each loops are suited for processing all values in an array, in order, without
		// worrying about indices.
		double sum = 0;
		double average;
		for (double number : numbers) {
			sum += number;
		}
		average = sum / numbers.length;
		return average;
	}


	/*
	 * Takes in an array of Strings representing names that have a mistaken leading space and a
	 * mistaken "#$" after each name. E.g. {" Tom Jones#$", " Jane Smith#$"} System.out.println's
	 * the names with the leading space and trailing "#$" removed. The name part will be at least
	 * one character long, e.g. " A#$"
	 */
	public static void cleanUpNameList(String[] names) {
		// Use the for-each syntax to iterate through each String in the array

		for (String name : names) {
			System.out.println(name.substring(1, name.length() - 2));
		}
		return;
	}


	/*
	 * Takes in an array of Strings representing names that are in "First Last" form E.g.
	 * {"Tom Jones", "Jane Smith"} and returns a String array with names in a more formal form of
	 * "Last, First" (You can assume no middle name or initial, and that there will always be at
	 * least one character in the First and Last part of the name). formalizeNameList(new
	 * String[]{"Tom Jones", "Jane Smith"}) --> {"Jones, Tom", "Smith, Jane"}
	 */
	public static String[] formalizeNameList(String[] names) {
		// for-each syntax is great for processing all values, in order. However, when more
		// complicated
		// processing is required, it might be easier to use the old for(int i = 0; i < ...) syntax
		// which allows easier indexing into the arrays.
		String[] formalizedNames = new String[names.length];
		int count = 0;
		for (String name : names) {
			String formalName = "";
			for (int i = 0; i < name.length(); i++) {
				if (name.charAt(i) == ' ') {
					formalName = name.substring(i + 1, name.length()) + ", " + name.substring(0, i);
					break;
				}
			}
			formalizedNames[count++] = formalName;
		}

		return formalizedNames;
	}



}
