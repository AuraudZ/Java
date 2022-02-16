// **** YOUR NAME HERE:Auraud
import java.util.Arrays;

public class SortLibrary {

	public static void main(String[] args) {
		// Test arrays you can use to check your sorts.
		// They represent common arrangements: random, already sorted, reversed, mostly sorted
		int[] random = new int[] {33, 94, 9, 40, 77, 82, 47, 15, 51, 64, 76, 28, 2, 85, 11};
		int[] alreadySorted = new int[] {2, 9, 11, 15, 28, 33, 40, 47, 51, 64, 76, 77, 82, 85, 94};
		int[] reversed = new int[] {94, 85, 82, 77, 76, 64, 51, 47, 40, 33, 28, 15, 11, 9, 2};
		int[] mostlySorted = new int[] {2, 85, 11, 15, 28, 33, 47, 40, 51, 64, 76, 77, 82, 9, 94};
		// int[] longerArray = ArrayImporter.readArrayFile("smallArray.txt");
		int[] myCustomTest = new int[] {15, 23, 42, 4, 8, 16, 1};

		// ***Enter your array to sort here
		int[] arrayToSort = random; // arrayToSort will point to the array you choose
		int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);

		// ***Enter which sort you want to test
		selectionSort(arrayToSort); // Call your sort method -- Remember array is modified in the
		// method, not returned!
		Arrays.sort(copyOfArrayToSort); // call java.util.Array's sort method for comparison

		if (arrayToSort.length < 50) {
			System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
			System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
		}

		System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));

	}

	// void normally would be OK.
	// However, I want you to keep track of and return the number of swaps.
	public static int bubbleSort(int[] nums) {
		int numSwaps = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length - 1; j++) {
				if (nums[j] > nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
					numSwaps++;
				}
			}
		}
		return numSwaps; // number of swaps
	}

	// void is OK. 'nums' simply receives a copy of reference to the unsorted
	// array 'arrayToSort' when method is called. When your method finishes,
	// 'arrayToSort' will point to the sorted array
	public static void insertionSort(int[] nums) {
		int temp;
		int j;
		for (int i = 1; i < nums.length; i++) {
			temp = nums[i];
			j = i;
			while (j > 0 && nums[j - 1] > temp) {
				nums[j] = nums[j - 1];
				j--;
			}
			nums[j] = temp;
		}
	}

	public static void selectionSort(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int min = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < nums[min]) {
					min = j;
				}
			}
			int temp = nums[min];
			nums[min] = nums[i];
			nums[i] = temp;
		}
	}

	// if you use recursion here, you should be able to explain it (i.e. you didn't copy it).
	// After break, we will go over how to do this without recursion.
	public static void mergeSort(int[] nums) {
		int[] temp = new int[nums.length];
		for (int blockSize = 1; blockSize < nums.length; blockSize *= 2) {
			for (int i = 0; i < nums.length; i += 2 * blockSize) {
				int low = i;
				int mid = Math.min(i + blockSize, nums.length);
				int high = Math.min(i + 2 * blockSize, nums.length);
				merge(nums, temp, low, mid, high);
			}
		}

	}

	private static void merge(int[] nums, int[] temp, int low, int mid, int high) {

	}

}


