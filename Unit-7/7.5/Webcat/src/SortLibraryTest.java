import static org.junit.Assert.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class SortLibraryTest {

	int[] longEvenRandomArray;
	int[] copyOflongEvenRandomArray;

	int[] longOddReversedArray;
	int[] copyOflongOddReversedArray;

	int[] longOddInOrderArray;
	int[] copyOflongOddInOrderArray;

	int[] shortAlmostInOrderArray;
	int[] copyOfshortAlmostInOrderArray;

	int[] shortRandomArrayForMergeSort;
	int[] copyOfshortRandomArrayForMergeSort;

	@Before
	public void setUp() throws Exception {
		// have to reset the arrays before each test
		// int[] temp = readArrayFile("longEvenRandomArray.txt");
		longEvenRandomArray = readArrayFile("longEvenRandomArray.txt");

		copyOflongEvenRandomArray = Arrays.copyOf(longEvenRandomArray, longEvenRandomArray.length);

		longOddReversedArray = readArrayFile("longOddReversedArray.txt");
		copyOflongOddReversedArray =
				Arrays.copyOf(longOddReversedArray, longOddReversedArray.length);

		longOddInOrderArray = readArrayFile("longOddInOrderArray.txt");
		copyOflongOddInOrderArray = Arrays.copyOf(longOddInOrderArray, longOddInOrderArray.length);

		shortAlmostInOrderArray = readArrayFile("shortAlmostInOrderArray.txt");
		copyOfshortAlmostInOrderArray =
				Arrays.copyOf(shortAlmostInOrderArray, shortAlmostInOrderArray.length);

		shortRandomArrayForMergeSort = readArrayFile("shortRandomArrayForMergeSort.txt");
		copyOfshortRandomArrayForMergeSort =
				Arrays.copyOf(shortRandomArrayForMergeSort, shortRandomArrayForMergeSort.length);
	}


	@Test
	public void testBubbleSortRandomData() {
		SortLibrary.bubbleSort(longEvenRandomArray);
		Arrays.sort(copyOflongEvenRandomArray);
		assertTrue(Arrays.equals(longEvenRandomArray, copyOflongEvenRandomArray));
	}

	@Test
	public void testBubbleSortRandomDataNumOfSwaps() {
		int swaps = SortLibrary.bubbleSort(longEvenRandomArray);
		assertEquals(67110900, swaps);
	}

	@Test
	public void testBubbleSortReversedDataNumOfSwaps() {
		int swaps = SortLibrary.bubbleSort(longOddReversedArray);
		assertEquals(1562320881, swaps);
	}

	@Test
	public void testBubbleSortInOrderDataNumOfSwaps() {
		int swaps = SortLibrary.bubbleSort(longOddInOrderArray);
		assertEquals(0, swaps);
	}

	@Test
	public void testInsertionSortRandomData() {
		SortLibrary.insertionSort(longEvenRandomArray);
		Arrays.sort(copyOflongEvenRandomArray);
		assertTrue(Arrays.equals(longEvenRandomArray, copyOflongEvenRandomArray));
	}

	@Test
	public void testInsertionSortInorderFasterThanReverseData() {
		long startTime1 = System.currentTimeMillis();
		SortLibrary.insertionSort(longOddReversedArray);
		long stopTime1 = System.currentTimeMillis();

		long startTime2 = System.currentTimeMillis();
		SortLibrary.insertionSort(longOddInOrderArray);
		long stopTime2 = System.currentTimeMillis();

		long timeForReversedSort = (stopTime1 - startTime1);
		long timeForInOrderSort = (stopTime2 - startTime2);

		// System.out.println("inorder time " + timeForInOrderSort + ", reversed time: " +
		// timeForReversedSort);
		assertTrue(timeForInOrderSort * 1.5 < timeForReversedSort);
	}

	@Test
	public void testInsertionSortdReverseData() {
		SortLibrary.insertionSort(longOddReversedArray);
		Arrays.sort(copyOflongOddReversedArray);
		assertTrue(Arrays.equals(longOddReversedArray, copyOflongOddReversedArray));
	}

	@Test
	public void testInsertionSortInorderData() {
		SortLibrary.insertionSort(longOddInOrderArray);
		Arrays.sort(copyOflongOddInOrderArray);
		assertTrue(Arrays.equals(longOddInOrderArray, copyOflongOddInOrderArray));
	}

	@Test
	public void testSelectionSortRandomData() {
		SortLibrary.selectionSort(longEvenRandomArray);
		Arrays.sort(copyOflongEvenRandomArray);
		assertTrue(Arrays.equals(longEvenRandomArray, copyOflongEvenRandomArray));
	}

	@Test
	public void testSelectionSortAlmostInOrderData() {
		SortLibrary.selectionSort(shortAlmostInOrderArray);
		Arrays.sort(copyOfshortAlmostInOrderArray);
		assertTrue(Arrays.equals(shortAlmostInOrderArray, copyOfshortAlmostInOrderArray));
	}

	@Test
	public void testSelectionSortReverseData() {
		SortLibrary.selectionSort(longOddReversedArray);
		Arrays.sort(copyOflongOddReversedArray);
		assertTrue(Arrays.equals(longOddReversedArray, copyOflongOddReversedArray));
	}

	@Test
	public void testSelectionSortInOrderData() {
		SortLibrary.selectionSort(longOddInOrderArray);
		Arrays.sort(copyOflongOddInOrderArray);
		assertTrue(Arrays.equals(longOddInOrderArray, copyOflongOddInOrderArray));
	}

	@Test
	public void testMergeSortLongRandomPowerOfTwoData() {
		SortLibrary.mergeSort(longEvenRandomArray);
		Arrays.sort(copyOflongEvenRandomArray);
		assertTrue(Arrays.equals(longEvenRandomArray, copyOflongEvenRandomArray));
	}


	@Test
	public void testMergeSortCorrectAnd20XFasterThanBubbleSortForLargeArray() {
		long startTime1 = System.currentTimeMillis();
		SortLibrary.bubbleSort(longEvenRandomArray);
		long stopTime1 = System.currentTimeMillis();
		long bubbleSortTime = stopTime1 - startTime1;

		int[] bubbleResult = Arrays.copyOf(longEvenRandomArray, longEvenRandomArray.length);
		longEvenRandomArray = readArrayFile("longEvenRandomArray.txt");

		long startTime2 = System.currentTimeMillis();
		SortLibrary.mergeSort(longEvenRandomArray);
		long stopTime2 = System.currentTimeMillis();
		long mergeSortTime = stopTime2 - startTime2;

		System.out.println("merge time: " + mergeSortTime);
		System.out.println("bubble time: " + bubbleSortTime);
		assertTrue(mergeSortTime * 20 < bubbleSortTime);
		assertTrue(Arrays.equals(longEvenRandomArray, bubbleResult));
	}

	@Test
	public void testMergeSortCorrectAnd10XFasterThanSelectionSortForLargeArray() {
		long startTime1 = System.currentTimeMillis();
		SortLibrary.selectionSort(longEvenRandomArray);
		long stopTime1 = System.currentTimeMillis();
		long selectionSortTime = stopTime1 - startTime1;

		int[] selectionResult = Arrays.copyOf(longEvenRandomArray, longEvenRandomArray.length);
		longEvenRandomArray = readArrayFile("longEvenRandomArray.txt");

		long startTime2 = System.currentTimeMillis();
		SortLibrary.mergeSort(longEvenRandomArray);
		long stopTime2 = System.currentTimeMillis();
		long mergeSortTime = stopTime2 - startTime2;

		// System.out.println("selection time: " + selectionSortTime);
		// System.out.println("merge time: " + mergeSortTime);
		assertTrue(mergeSortTime * 10 < selectionSortTime);
		assertTrue(Arrays.equals(longEvenRandomArray, selectionResult));
	}

	@Test
	public void testMergeSortNonPowerOfTwoData() {
		SortLibrary.mergeSort(shortRandomArrayForMergeSort);
		Arrays.sort(copyOfshortRandomArrayForMergeSort);
		assertTrue(Arrays.equals(shortRandomArrayForMergeSort, copyOfshortRandomArrayForMergeSort));
	}


	public static int[] readArrayFile(String fileName) {
		try {
			Scanner scanner = new Scanner(new File(fileName));
			ArrayList<Integer> nums = new ArrayList<Integer>();
			while (scanner.hasNextInt()) {
				int n = scanner.nextInt();
				nums.add(n);
			}
			int[] numsArray = new int[nums.size()];
			for (int i = 0; i < numsArray.length; i++) {
				numsArray[i] = nums.get(i);
			}
			return numsArray;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}



}
