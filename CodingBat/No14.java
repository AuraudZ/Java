public class No14 {
    public boolean no14(int[] nums) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count1++;
            }
            if (nums[i] == 4) {
                count2++;
            }
        }
        return count1 == 0 || count2 == 0;
    }

    }

    public boolean isEverywhere(int[] nums, int val) {
        // We'll say that a value is "everywhere" in an array if for every pair of
        // adjacent elements in the array, at least one of the pair is that value.
        // Return true if the given value is everywhere in the array.
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != val && nums[i + 1] != val) {
                return false;
            }
        }
        return true;

    }

    public boolean splitArray(int[] nums) {

        return splitArrayHelper(nums, 0, 0, 0);
    }

    private boolean splitArrayHelper(int[] nums, int i, int j, int k) {
        if (i == nums.length) {
            return k == j;
        }
        return splitArrayHelper(nums, i + 1, j + nums[i], k) || splitArrayHelper(nums, i + 1, j, k + nums[i]);
    }

    }

    public int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

}

}
