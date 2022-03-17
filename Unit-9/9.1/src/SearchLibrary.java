public class SearchLibrary {

	public static int sequentialSearch(int num, int[] list) {
		for (int j = 0; j < list.length; j++) { // this is sequential search
			if (num == list[j]) {
				return j;
			}
		}
		return -1;
	}


/*	public static int binarySearch(int num, int[] list) {
		int low = 0;
		int high = list.length - 1;
		int medium = (low + high)/2;

		while(low <= high) {
			if(num == list[medium]) { // found!
				return medium; // return index (not value)
			}
			else if(num > list[medium]) { // slice off left half of list
				low = medium + 1;
				medium = (low + high)/2;
			}
			else { // cut off right half of list
				high = medium - 1;
				medium = (low + high)/2;
			}

		}
		return -1; // not found!  return index of -1!
	} */

	public static int binarySearch(int num, int[] list) {
		return binarySearchRecursive(num, list, 0, list.length/2, list.length - 1);
	}

	public static int binarySearchRecursive(int num, int[] list, int low,int mid, int high) {

		if (num == list[mid]) { // found!
			return mid; // return index (not value)
		}
		if (low > high) { // not found!
			return -1;
		} else if (num > list[mid]) { // slice off left half of list
			return binarySearchRecursive(num, list, mid + 1, (low + high) / 2, high);
		} else { // cut off right half of list
			return binarySearchRecursive(num, list, low, (low + high) / 2, mid - 1);
		}
	}
}
