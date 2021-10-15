package CodingBat;

public class PlusTwo {
    public static void main(String[] args) {

    }

    public int[] plusTwo(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            c[i + a.length] = b[i];
        }
        return c;
    }

    public int[] swapEnds(int[] nums) {
        int temp = nums[0];
        nums[0] = nums[nums.length - 1];
        nums[nums.length - 1] = temp;
        return nums;
    }

    public int[] midThree(int[] nums) {
        int[] c = new int[3];
        c[0] = nums[nums.length / 2 - 1];
        c[1] = nums[nums.length / 2];
        c[2] = nums[nums.length / 2 + 1];
        return c;
    }

    public boolean lucky13(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1 || nums[i] == 3) {
                return false;
            }
        }
        return true;
    }

    public boolean sum28(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 2) {
                sum += nums[i];
            }
        }
        return sum == 8;
    }

    public boolean more14(int[] nums) {
        // Given an array of ints, return true if the number of 1's is greater than the
        // number of 4's
        int one = 0;
        int four = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                one++;
            } else if (nums[i] == 4) {
                four++;
            }
        }
        return one > four;
    }

    public int[] fizzArray(int n) {
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = i;
        }
        return c;
    }

    public int[] no14(int[] nums) {
        int[] c = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        while (i < nums.length && j >= 0) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[j] == 4) {
                j--;
            } else {
                c[i] = nums[i];
                i++;
            }
        }
        return c;
    }

    public String[] fizzArray2(int n) {
        String[] c = new String[n];
        for (int i = 0; i < n; i++) {
            c[i] = Integer.toString(i);
        }
        return c;
    }

    public boolean more14(int[] nums) {
        int one = 0;
        int four = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                one++;
            } else if (nums[i] == 4) {
                four++;
            }

        }
        return one > four;
    }

    /*
     * Return the sum of the numbers in the array, except ignore sections of numbers
     * starting with a 6 and extending to the next 7 (every 6 will be followed by at
     * least one 7). Return 0 for no numbers.
     */
    public int sum67(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 6) {
                while (nums[i] != 7) {
                    i++;
                }
            } else {
                sum += nums[i];
            }
        }
        return sum;
    }

    /*
     * Given an array of ints, return true if the array contains a 2 next to a 2 or
     * a 4 next to a 4, but not both.
     */
    public boolean either24(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 2 && nums[i + 1] == 2) {
                return false;
            } else if (nums[i] == 4 && nums[i + 1] == 4) {
                return false;
            }
        }
        return true;
    }

    /*
     * Given an array of ints, return true if the array contains either 3 even or 3
     * odd values all next to each other.
     */
    public boolean modThree(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++) {
            if ((nums[i] % 2 == 0 && nums[i + 1] % 2 == 0 && nums[i + 2] % 2 == 0)
                    || (nums[i] % 2 == 1 && nums[i + 1] % 2 == 1 && nums[i + 2] % 2 == 1)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Given an array of ints, return true if the value 3 appears in the array
     * exactly 3 times, and no 3's are next to each other.
     */
    public boolean haveThree(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 3
        int sum = 0;

    }

    public boolean has22(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 2 && nums[i + 1] == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean double23(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 2 && nums[i + 1] == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean canBalance(int[] nums) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < nums.length; i++) {
            sum1 += nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            sum2 += nums[i];
            if (sum1 == sum2) {
                return true;
            }
            sum2 -= nums[i];
        }
        return false;
    }

    public boolean linearIn(int[] outer, int[] inner) {
        int i = 0;
        for (int j = 0; j < inner.length; j++) {
            if (inner[j] == outer[i]) {
                i++;
            }
        }
        return i == inner.length;
    }

    public boolean linearIn2(int[] outer, int[] inner) {
        int i = 0;
        for (int j = 0; j <
    }

}