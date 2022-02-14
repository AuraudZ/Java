public class TwoDimensionalArrayTester {
	public static void main(String[] args) {
		int[][] twoByThree = new int[2][3];
		int[][] threeByThree = new int[3][3];
		int[][] fourByFive = new int[4][5];
		print2DArray(fourByFive);

		// Test countZeros method
		System.out.println("Zeros: " + countZeros(fourByFive));

		// Call fill1And3() method
		fill1And3(fourByFive);

		// Print out array, see if fill1And3() actually changed anything
		print2DArray(fourByFive);

		// Check if countZeros() properly detects the lack of 0's
		System.out.println("Zeros: " + countZeros(fourByFive));

	}

	// Fills a 2-dimensional array of integers with 1's,
	// except where the row number is the same as the column number
	// -- fill those with 3's
	public static void fill1And3(int[][] vals) {
		for (int i = 0; i < vals.length; i++) {
			for (int j = 0; j < vals[i].length; j++) {
				if (i == j) {
					vals[i][j] = 3;
				} else {
					vals[i][j] = 1;
				}
			}
		}
	}

	public static int countZeros(int[][] vals) {
		int count = 0;
		// Plan: loop through each row (each row is an array)
		// and go through each col.
		for (int i = 0; i < vals.length; i++) {
			for (int j = 0; j < vals[i].length; j++) {
				if (vals[i][j] == 0) {
					count++;
				}
			}
		}
		return count;
	}

	public static void print2DArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(java.util.Arrays.toString(arr[i]));
		}
	}
}
