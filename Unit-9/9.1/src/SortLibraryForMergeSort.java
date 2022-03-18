import java.util.Arrays;

public class SortLibraryForMergeSort {
	
	public static void main(String[] args) {
		// Test arrays you can use to check your sorts.
		// They represent common arrangements: random, already sorted, reversed, mostly sorted
		int[] myCustomTest = new int[]{5,3,69,73,11,17,1,74,34,86}; //***use this one
		// ***Enter your array to sort here
		int[] arrayToSort = myCustomTest; // arrayToSort will point to the array you choose
		int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
		

		long startTime1 = System.currentTimeMillis();
		mergeSort(arrayToSort);		// Call your sort method -- Remember array is modified in the method, not returned!
		long stopTime1 = System.currentTimeMillis();
		
		long startTime2 = System.currentTimeMillis();
		Arrays.sort(copyOfArrayToSort);	// call java.util.Array's sort method for comparison
		long stopTime2 = System.currentTimeMillis();
		
		if(arrayToSort.length < 50) {
			System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
			System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
		}
		
		System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));
		System.out.println("Time 1: " + (stopTime1 - startTime1) + " ms");
		System.out.println("Time 2: " + (stopTime2 - startTime2) + " ms");
	}

	// runner
	public static void mergeSort(int[] a) {
		int aux[] = new int[a.length];
		mergeSort(a, aux,0, a.length/2, a.length);
	}	
	
	// *** You must use recursion here, but feel free to alter parameters if you want!
	public static void mergeSort(int[] a, int[] aux,int lo, int mid, int hi) {
		// base case?
		if(lo == mid) {
			return;
		}
		// recursive call
		mergeSort(a, aux, lo, (lo+mid)/2, mid);
		mergeSort(a, aux, mid, (mid+hi)/2, hi);
		merge(a, aux, lo, mid, hi);
	}
	
	// *** We verified this works, feel free to use as-is (or modify params, etc)
	private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		if (mid >= a.length) 
			return;
		if (hi > a.length) 
			hi = a.length;
		
		int pointer1 = lo, pointer2 = mid;
		
		for (int i = pointer1; i < hi; i++) { 
			if (pointer1 == mid)
				aux[i] = a[pointer2++];
			else if (pointer2 == hi)
				aux[i] = a[pointer1++];
			else if (a[pointer2] < a[pointer1])
				aux[i] = a[pointer2++];
			else
				aux[i] = a[pointer1++];
		}
		
		for (int j = lo; j < hi; j++) // lo and hi still point to original indices
			a[j] = aux[j];
	}  


}
