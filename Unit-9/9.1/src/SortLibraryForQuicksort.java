
import java.util.Arrays;

public class SortLibraryForQuicksort {

    public static void main(String[] args) {
        int[] random = new int[] {33, 94, 9, 40, 77, 82, 47, 15, 51, 64, 76, 28, 2, 85, 11};
        int[] alreadySorted = new int[] {2, 9, 11, 15, 28, 33, 40, 47, 51, 64, 76, 77, 82, 85, 94};
        int[] reversed = new int[] {94, 85, 82, 77, 76, 64, 51, 47, 40, 33, 28, 15, 11, 9, 2};
        int[] mostlySorted = new int[] {2, 85, 11, 15, 28, 33, 47, 40, 51, 64, 76, 77, 82, 9, 94};
        int[] myCustomTest = new int[] {5, 3, 69, 73, 11, 17, 1, 74, 34, 86};

        // ***Enter your array to sort here
        int[] arrayToSort = random; // arrayToSort will point to the array you choose
        int[] copyOfArrayToSort = Arrays.copyOf(arrayToSort, arrayToSort.length);

        long startTime1 = System.currentTimeMillis();
        quickSort(arrayToSort); // Call your sort method -- Remember array is modified in the
                                // method, not returned!
        long stopTime1 = System.currentTimeMillis();

        long startTime2 = System.currentTimeMillis();
        Arrays.sort(copyOfArrayToSort); // call java.util.Array's sort method for comparison
        long stopTime2 = System.currentTimeMillis();

        if (arrayToSort.length < 50) {
            System.out.println("Result after sort: " + Arrays.toString(arrayToSort));
            System.out.println("Result should be: " + Arrays.toString(copyOfArrayToSort));
        }

        System.out.println("Sorts match? " + Arrays.equals(arrayToSort, copyOfArrayToSort));
        System.out.println("Time 1: " + (stopTime1 - startTime1) + " ms");
        System.out.println("Time 2: " + (stopTime2 - startTime2) + " ms");
    }

    public static void quickSort(int[] nums) {
        quickSortRecursive(nums, 0, nums.length - 1);
    }

    public static void quickSortRecursive(int[] nums, int loIndex, int hiIndex) {
        if (loIndex >= hiIndex)
            return;
        int pivotIndex = partitionStep(nums, loIndex, hiIndex);
        quickSortRecursive(nums, loIndex, pivotIndex - 1);
        quickSortRecursive(nums, pivotIndex + 1, hiIndex);
    }


    private static int partitionStep(int[] nums, int loIndex, int hiIndex) {
        int pivotIndex = (int) (Math.random() * (hiIndex - loIndex) + loIndex);
        int pivotNum = nums[pivotIndex];
        while (loIndex < hiIndex) {
            while (nums[loIndex] < pivotNum) {
                loIndex++;
            }
            while (nums[hiIndex] > pivotNum) {
                hiIndex--;
            }
            if (loIndex >= hiIndex) {
                break;
            }
            swapValues(nums, loIndex, hiIndex);
        }
        pivotIndex = loIndex;
        return pivotIndex;
    }

    private static void swapValues(int[] nums, int loIndex, int hiIndex) {
        int temp = nums[loIndex];
        nums[loIndex] = nums[hiIndex];
        nums[hiIndex] = temp;
    }

}
