public class Recursion2 {
    public static void main(String[] args) {
        Recursion2 cb = new Recursion2();
        int[] nums = {1, 2, 4, 8, 1};
        int start = 0;
        int target = 14;
        System.out.println(cb.groupSumClump(start, nums, target));
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
    Given an array of ints, is it possible to choose a group of some of the ints,
    beginning at the start index, such that the group sums to the given target?
    However, with the additional constraint that all 6's must be chosen.
    (No loops needed.)
     */
    public boolean groupSum6(int start, int[] nums, int target) {
        // Base Case
        if (start >= nums.length) {
            return target == 0;
        }
        if (nums[start] == 6) {
            return groupSum6(start + 1, nums, target - nums[start]);
        }
        return groupSum6(start + 1, nums, target - nums[start]) || groupSum6(start + 1, nums, target);
    }


    public boolean groupSum5(int start, int[] nums, int target) {
        // Base Case
        if (start >= nums.length) {
            return target == 0;
        }

        if(nums[start] % 5 == 0) {
            if (start + 1 < nums.length && nums[start + 1] == 1) {
                return groupSum5(start + 2, nums, target - nums[start]);
            }
            return groupSum5(start + 1, nums, target - nums[start]);
        }

        // If the value following a multiple of 5 is also a 1 then it is false
        return groupSum5(start + 1, nums, target - nums[start]) || groupSum5(start + 1, nums, target);
    }

    public boolean groupNoAdj(int start, int[] nums, int target) {
        // Base Case
        if (start >= nums.length) {
            return target == 0;
        }
        if (start + 2 < nums.length && nums[start] == nums[start + 2]) {
            return groupNoAdj(start + 2, nums, target);
        }
        // Recursive Case
        return groupNoAdj(start + 2, nums, target - nums[start]) || groupNoAdj(start + 1, nums, target);
    }



    //Given an array of ints, is it possible to choose a group of
    // some of the ints, such that the group sums to the given target,
    // with this additional constraint:
    // if there are numbers in the array that are adjacent and the
    // identical value, they must either all be chosen,
    // or none of them chosen. For example,
    // with the array {1, 2, 2, 2, 5, 2},
    // either all three 2's in the middle must be chosen or not,
    // all as a group.
    // (one loop can be used to find the extent of the identical values).

    public boolean groupSumClump(int start, int[] nums, int target) {
        // Base Case
        if (start >= nums.length) {
            return target == 0;
        }
        int i = start;
        while (i < nums.length && nums[i] == nums[start]) {
            i++;
        }
        return groupSumClump(i, nums, target - nums[start]) || groupSumClump(i, nums, target);
    }

  



    public static  int equationSolver() {
       double a = 3*Math.pow(-2,4);
       double b = -6*Math.pow(-2,3);
       double c = -5*-2;
       double d = a + b + c+10;
       return (int) d;
  }

}
