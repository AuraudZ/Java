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

    // Return an array that contains the exact same numbers as the given array, but rearranged so
    // that all the even numbers come before all the odd numbers. Other than that, the numbers can
    // be in any order. You may modify and return the given array, or make a new array.
    public int[] evenOdd(int[] nums) {
        int[] result = new int[nums.length];
        int even = 0;
        int odd = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                result[even] = nums[i];
                even++;
            } else {
                result[odd] = nums[i];
                odd--;
            }
        }
        return result;
    }

    // Return a version of the given array where each zero value in the array is replaced by the
    // largest odd value to the right of the zero in the array. If there is no odd value to the
    // right of the zero, leave the zero as a zero.
    public int[] zeroMax(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int max = 0;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] % 2 == 1 && nums[j] > max) {
                        max = nums[j];
                    }
                }
                result[i] = max;
            } else {
                result[i] = nums[i];
            }
        }
        return result;
    }



    /*
     * Given an array of ints, is it possible to divide the ints into two groups, so that the sum of
     * the two groups is the same, with these constraints: all the values that are multiple of 5
     * must be in one group, and all the values that are a multiple of 3 (and not a multiple of 5)
     * must be in the other. (No loops needed.)
     * 
     * 
     */
    public boolean split53(int[] nums) {
        if (nums.length == 0)
            return true;
        int sum1 = 0;
        int sum2 = 0;

        split53Helper(nums, index, sum1, sum2)
    }

    public int[] withoutTen(int[] nums) {
        int[] result = new int[nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 10) {
                result[index] = nums[i];
                index++;
            }
        }
        return result;
    }

    /*
     * Given start and end numbers, return a new array containing the sequence of integers from
     * start up to but not including end, so start=5 and end=10 yields {5, 6, 7, 8, 9}. The end
     * number will be greater or equal to the start number. Note that a length-0 array is valid.
     * 
     */
    public int[] fizzArray3(int start, int end) {
        int[] result = new int[end - start];
        for (int i = start; i < end; i++) {
            result[i - start] = i;
        }
        return result;
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
