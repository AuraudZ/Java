public class CodingBatTester {

	// *** Remember, when you are writing Codingbat problems you are just writing the subroutine
	// part.
	// However, keep in mind the context. In real life, there will always be a class the subroutine
	// is a part of.
	// There will be a subroutine (often main()) that calls it.
	// The calling subroutine will be expecting the subroutine to perform exactly as specified.
	// However, this is hard to do (ensuring that the subroutine works under all cases). That is why
	// we are practicing
	// with Codingbat.
	// If it helps you to picture the context, or to debug (by adding System.out.println
	// statements), feel free to use this file
	// and then paste in your answers to codingbat.com

	/*
	 * Given 2 integer values, a and b, returns true if one of them is 10 or if their sum is 10
	 * makes10(9, 10) → true makes10(9, 9) → false makes10(1, 9) → true
	 */
	public static boolean makes10(int a, int b) {
		if (a == 10 || b == 10 || a + b == 10) {
			return true;
		}
		return false;
	}

	/*
	 * Given three int values, a b c, return the largest. intMax(1, 2, 3) → 3 intMax(1, 3, 2) → 3
	 * intMax(3, 2, 1) → 3
	 */
	public static int intMax(int a, int b, int c) {
		int max = a;
		if (b > max) {
			max = b;
		}
		if (c > max) {
			max = c;
		}
		return max;
	}

	/*
	 * Given a string, return a new string where "not " has been added to the front. However, if the
	 * string already begins with "not", return the string unchanged. Note: use .equals() to compare
	 * 2 strings. notString("candy") → "not candy" notString("x") → "not x" notString("not bad") →
	 * "not bad"
	 */
	public static String notString(String str) {
		if (str.substring(0, 3).contains("not")) {
			return str;
		}

		return "";
	}

	/*
	 * Given a non-empty string and an int n, return a new string where the char at index n has been
	 * removed. The value of n will be a valid index of a char in the original string (i.e. n will
	 * be in the range 0..str.length()-1 inclusive). missingChar("kitten", 1) → "ktten"
	 * missingChar("kitten", 0) → "itten" missingChar("kitten", 4) → "kittn"
	 */
	public static String missingChar(String str, int n) {
		return "";
	}

	/*
	 * Given a string, return a new string where the first and last chars have been exchanged.
	 * frontBack("code") → "eodc" frontBack("a") → "a" frontBack("ab") → "ba"
	 */
	public static String frontBack(String str) {
		return "";
	}

	public static void main(String[] args) {
		System.out.println("***Testing makes10***");
		System.out.println("Calling makes10(9,10) -->  " + makes10(9, 10) + ". (Should be true)");
		System.out.println("Calling makes10(9,9) -->  " + makes10(9, 9) + ". (Should be false)");
		System.out.println("Calling makes10(1,9) -->  " + makes10(1, 9) + ". (Should be true)");
		System.out.println("Calling makes10(3,7) -->  " + makes10(3, 7) + ". (Should be true)");
		System.out.println();

		System.out.println("***Testing intMax ***");
		System.out.println("Calling intMax(1, 2, 3) -->  " + intMax(1, 2, 3) + ". (Should be 3)");
		System.out.println("Calling intMax(1, 3, 2) -->  " + intMax(1, 3, 2) + ". (Should be 3)");
		System.out.println("Calling intMax(3, 2, 1) -->  " + intMax(3, 2, 1) + ". (Should be 3)");

		System.out.println("\n");

		System.out.println("***Testing notString ***");
		System.out.println("Calling notString(\"candy\") -->  " + notString("candy")
				+ ". (Should be \"not candy\")");
		System.out.println(
				"Calling notString(\"x\") -->  " + notString("x") + ". (Should be \"not x\")");
		System.out.println("Calling notString(\"not bad\") -->  " + notString("not bad")
				+ ". (Should be \"not bad\" )");
		System.out.println();

		System.out.println("***Testing missingChar ***");
		System.out.println("Calling missingChar(\"kitten\", 1) -->  " + missingChar("kitten", 1)
				+ ". (Should be \"ktten\")");
		System.out.println("Calling missingChar(\"kitten\", 0) -->  " + missingChar("kitten", 0)
				+ ". (Should be \"itten\")");
		System.out.println("Calling missingChar(\"kitten\", 4) -->  " + missingChar("kitten", 4)
				+ ". (Should be \"kittn\")");
		System.out.println();

		System.out.println("***Testing frontBack ***");
		System.out.println(
				"Calling frontBack(\"code\") -->  " + frontBack("code") + ". (Should be \"eodc\")");
		System.out
				.println("Calling frontBack(\"a\") -->  " + frontBack("a") + ". (Should be \"a\")");
		System.out.println(
				"Calling frontBack(\"ab\") -->  " + frontBack("ab") + ". (Should be \"ba\")");
	}
}
