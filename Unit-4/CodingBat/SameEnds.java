public class SameEnds {
    public boolean sameEnds(int[] nums, int len) {
        for (int i = 0; i < len; i++) {
            if (nums[i] != nums[nums.length - len + i]) {
                return false;
            }
        }
        return true;
    }

    public String[] fizzBuzz(int start, int end) {
        String[] result = new String[end - start];
        for (int i = start; i < end; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result[i - start] = "FizzBuzz";
            } else if (i % 3 == 0) {
                result[i - start] = "Fizz";
            } else if (i % 5 == 0) {
                result[i - start] = "Buzz";
            } else {
                result[i - start] = Integer.toString(i);
            }
        }
        return result;
    }

    /*
     * Given an array of ints, is it possible to divide the ints into two groups, so
     * that the sum of the two groups is the same, with these constraints: all the
     * values that are multiple of 5 must be in one group, and all the values that
     * are a multiple of 3 (and not a multiple of 5) must be in the other. (No loops
     * needed.)
     * 
     * 
     */
    public boolean split53(int[] nums) {
        if (nums.length == 0)
            return true;

    }

    public static boolean split53Helper(int[] nums, int index, int sum1, int sum2) {
        if (index == nums.length) {
            return sum1 == sum2;
        }
        if (index >= nums.length) {
            return sum1 == sum2;
        }

        if (nums[index] % 5 == 0) {
            return split53Helper(nums, index + 1, sum1 + nums[index], sum2);
        } else if (nums[index] % 3 == 0) {
            return split53Helper(nums, index + 1, sum1, sum2 + value);
        } else {
            return (split53Helper(nums, index + 1, sum1 + value, sum2)
                    || split53Helper(nums, index + 1, sum1, sum2 + value));
        }
    }
}
