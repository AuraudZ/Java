public class FormatTesterClasswork {

	public static void main(String[] args) {

		int testInt = 54321;
		double testDouble = 987.654321;

		// "+" operator will convert int testInt to String and then concatenate it
		System.out.println("testInt: " + testInt);

		// "+" operator will convert double testDouble to String and then concatenate it
		System.out.println("testDouble: " + testDouble);


		// *********** Format tests *************

		// ******int Tests******
		// Try to have it output testInt with 5 blank spaces in the front
		// right-justified: " 54321"
		System.out.printf("", testInt);
		System.out.println();

		// Try to have it output testInt with 5 blank spaces in the back
		// left-justified: "54321 " (HINT: USE A FLAG)
		System.out.printf(" %d", testInt);
		System.out.println();

		// Try to have it output testInt with 3 blank spaces in the front, and commas separating
		// them
		// right-justified: " 54,321"
		// System.out.printf(); // 54,321
		System.out.println();



		// ******double Tests******
		// Try to have it output testDouble rounded to 2 decimal places,
		// with 2 dollar signs in front, "$$987.65"
		System.out.printf("", testDouble);
		System.out.println();

		// Try to have it output testDouble rounded to 3 decimal places, and with a
		// minimum of 15 characters printed. Instead of blank spaces, pad the
		// left with 0's "00000000987.654"

		System.out.println();



		// ******String Tests******
		// Try to have it output the 2 numbers in the middle of a String.
		// Put 2 blank spaces in front of the decimal number, round the double to 2 digits
		// "I make $ 54,321 every month selling laptops!(new line)
		// "Call today and you can make $$$987.65!!!!"

		System.out.println();


	}


}
