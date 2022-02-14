public class TwoDimensionalArrayTester {
	public static void main(String[] args) {
		int[][] twoByThree = new int[2][3];
		int[][] threeByThree = new int[3][3];
		int[][] fourByFive = new int[4][5];
		System.out.println(java.util.Arrays.toString(fourByFive));

		// Test countZeros method
		System.out.println("Zeros: " + countZeros(fourByFive));

		// Call fill1And3() method
		fill1And3(fourByFive);

		// Print out array, see if fill1And3() actually changed anything


		// Check if countZeros() properly detects the lack of 0's
		System.out.println("Zeros: " + countZeros(fourByFive));

	}

	// Fills a 2-dimensional array of integers with 1's,
	// except where the row number is the same as the column number
	// -- fill those with 3's
	public static void fill1And3(int[][] vals) {

	}


	public static int countZeros(int[][] vals) {


		return -1;
	}


}
