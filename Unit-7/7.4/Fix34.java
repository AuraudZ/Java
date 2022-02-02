public class Fix34 {
    /*
     * Return an array that contains exactly the same numbers as the given array, but rearranged so
     * that every 3 is immediately followed by a 4. Do not move the 3's, but every other number may
     * move. The array contains the same number of 3's and 4's, every 3 has a number after it that
     * is not a 3, and a 3 appears in the array before any 4.
     */
    public int[] fix34(int[] nums) {
        int[] result = new int[nums.length];
        int i = 0;
        int j = 0;
        while (i < nums.length) {
            if (nums[i] == 3) {
                result[j] = nums[i];
                j++;
                i++;
            } else if (nums[i] == 4) {
                result[j] = nums[i];
                j++;
                i++;
            } else {
                result[j] = nums[i];
                j++;
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
