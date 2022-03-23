public class Recursion2 {
    public static void main(String[] args) {
        Recursion2 cb = new Recursion2();
        int[] nums = {2, 3};
        int start = 0;
        int target = 17;
//        System.out.println(cb.groupSum(start, nums, target));
        System.out.println(cb.splitArray(nums));
    }


    public boolean groupSum(int start, int[] nums, int target) {
        // Base Case
        if (start >= nums.length) {
            return target == 0;
        }
        // Recursive Case
        return groupSum(start + 1, nums, target - nums[start]) || groupSum(start + 1, nums, target);
    }

    /*
    Given an array of ints, is it possible to divide the ints into two
    groups, so that the sums of the two groups are the same.
    Every int must be in one group or the other.
    Write a recursive helper method that takes whatever arguments you like,
    and make the initial call to your recursive helper from splitArray().
    (No loops needed.)
     */
    public boolean splitArray(int[] nums) {
        // Base Case
        if (nums.length == 0) {
            return false;
        }
        // Recursive Case
        return splitArrayHelper(nums, 0, 0, nums[0]);
    }

    private boolean splitArrayHelper(int[] nums, int start, int sum1, int sum2) {
        if (start >= nums.length) {
            return sum1 == sum2;
        }
        return splitArrayHelper(nums, start + 1, sum1, sum2) || splitArrayHelper(nums, start + 1, sum1 + nums[start], sum2);
    }

}
