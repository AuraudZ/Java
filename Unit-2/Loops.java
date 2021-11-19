
public class Loops {

	public static void main(String[] args) {
		// Codingbat example:
		System.out.println("Testing stringBits: " + stringBits("Hello"));


		// Debugging examples:
		definiteAssignmentExample();
		first10Try1();
		first10Try2();

		// Tests:
		System.out.println("*** Testing evenNumbers() ***");
		evenNumbers();
		System.out.println("*** Testing multiplesFive() ***");
		multiplesFive();
		System.out.println("*** Testing powersOf2() ***");
		powersOf2();
		System.out.println("*** Testing blastOff() ***");
		blastOff(7);
		System.out.println("*** Testing chopName() ***");
		chopName("Stephen");


		// Other Tests:
		int number = 732;
		System.out.println("The number " + number + " has " + countDigits(number) + " digits.");
	}


	/*
	 * In general, you want to avoid having your subroutines print out with System.out (unless
	 * debugging). This is because a subroutine should have a specific task (e.g. calculating
	 * slope). It should do that task, then return to the caller (often main), who would decide what
	 * to do with that returned value.
	 * 
	 * Here, however, to practice loops, we will resort to using System.out's inside these
	 * subroutines.
	 * 
	 * Credit for exercises 1, 2, 3, 4 to http://teachapcs.com/exercise/loop-exercises
	 */

	// 0a: Example of code from stringBits
	public static String stringBits(String str) {
		String myString = "";
		int index = 0;

		while (index < str.length()) {
			System.out.println("Debug: " + myString);
			myString = myString + str.charAt(index);
			index = index + 2;
		}
		return myString;
	}


	// 0b: Debug why this if-else branch doesn't compile
	private static String definiteAssignmentExample() {
		// String name; // Dosen't Work
		String name = "";
		int x = (int) (Math.random() * 10); // Should be rand int between 0 and 9
		if (x > 4) {
			name = "Jack";
		} else if (x <= 4) {
			name = "Jill";
		}
		// name could not be inalizied some times
		return name;
	}

	// 0c: Debug the problem in this loop that is supposed to print out the integers
	// between 1 and 10
	private static void first10Try1() {
		int count = 1;
		while (count <= 10) {
			System.out.println(count);
			count = count + 1;
		}
		System.out.println("Finished printing up to " + count);
	}

	// 0d: Debug the problems in this loop that is supposed to print out the integers
	// between 1 and 10
	private static void first10Try2() {
		int count = 1;
		while (count <= 10) {
			System.out.println(count);
			count += 1;
		}
		System.out.println("Finished printing up to " + count);
	}



	// 1: Write a while loop that prints out all even numbers between 22 and 48.
	private static void evenNumbers() {
		int count = 22;
		while (count <= 48) {
			System.out.println(count);
			count += 2;
		}
	}


	// 2: Write a while loop that prints out all multiples of 5 less than or equal to 85.
	private static void multiplesFive() {
		int count = 5;
		while (count <= 85) {
			System.out.println(count);
			count += 5;
		}
	}

	// 3: Write a loop that prints out the powers of 2 from 0 to 10.
	// (Don't forget that one way to think about powers is as repeated multiplication.)
	private static void powersOf2() {
		int count = 0;
		while (count <= 10) {
			System.out.println(Math.pow(2, count));
			count += 1;
		}
	}



	// 4: Write a subroutine using a loop that prints out the decreasing numbers starting
	// from count, all the way down to 1. Then, it prints "blast off!" when it reaches 1.
	// blastOff(3) --> blastOff(2) -->
	// 3 2
	// 2 1
	// 1 blast off!
	// blast off!
	private static void blastOff(int count) {
		while (count > 0) {
			System.out.println(count);
			count -= 1;
		}
		System.out.println("blast off!");
	}


	// 5: Write a subroutine using a loop that prints out the String name, chopping off the
	// last letter of the name each time, until name has no more characters left.
	// Then it prints out "<original name> has been chopped."
	// chopName("Clyde") --> chopName("Barb") -->
	// Clyde Barb
	// Clyd Bar
	// Cly Ba
	// Cl B
	// C
	// Barb has been chopped.
	// Clyde has been chopped.
	private static void chopName(String name) {
		while (name.length() > 0) {
			System.out.println(name);
			name = name.substring(0, name.length() - 1);
		}
		System.out.println(name + " has been chopped.");
	}



	// 6: Write a subroutine that, given an integer,
	// uses a loop to count the number of digits in the integer
	// countDigits(52) --> 2 countDigits(23118) --> 5
	//
	// Hint % 10 grabs the last digit in an int, / 10 chops the last digit
	// Example: 982 % 10 --> 2, 982 / 10 --> 98
	public static int countDigits(int num) {
		int n = 0;
		while (num > 0) {
			num = num / 10;
			n = n + 1;
		}
		return n;
	}


}
