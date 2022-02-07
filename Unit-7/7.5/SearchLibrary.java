
public class SearchLibrary {

	/*
	 * Search for 'num' in the given list using the Sequential Search algorithm. If Verbose Mode is
	 * 'on', prints out the total number of checks This is to allow you to see the difference in the
	 * number of checks when doing a Single Search Test. (Multiple Search Test would generate too
	 * much output). Returns -1 if the number isn't found. If found, return the index the number was
	 * found at
	 */
	public static int sequentialSearch(int num, int[] list, boolean verboseModeOn) {
		int checkCount = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i] == num) {
				return i;
			}
			checkCount++;
		}
		if (verboseModeOn)
			System.out.println("Total number of checks: " + checkCount);
		return -1;
	}

	/*
	 * Search for 'num' in the given list using the Binary Search algorithm. If Verbose Mode is
	 * 'on', prints out the total number of checks This is to allow you to see the difference in the
	 * number of checks when doing a Single Search Test. (Multiple Search Test would generate too
	 * much output). Returns -1 if the number isn't found. If found, return the index the number was
	 * found at
	 */
	public static int binarySearch(int num, int[] list, boolean verboseModeOn) {
		int checkCount = 0;
		int low = 0;
		int high = list.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (list[mid] == num) {
				return mid;
			} else if (list[mid] < num) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
			checkCount++;
		}
		if (verboseModeOn)
			System.out.println("Total number of checks: " + checkCount);

		return -1;
	}


}
