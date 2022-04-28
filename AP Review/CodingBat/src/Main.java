import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main cb = new Main();
        String[] a = {"a", "a", "b", "b", "c"};
        String[] b = {"a", "b", "b", "b", "c"};
        String c = "x";
        System.out.println(cb.commonTwo(a, b));
    }

    public int[] copyEvens(int[] nums, int count) {
        int[] result = new int[count];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // Check if the number is even and if it will fit in the result array
            if (nums[i] % 2 == 0 && j < count) {
                result[j] = nums[i];
                j++;
            }
        }
        return result;
    }

    public int[] copyEndy(int[] nums, int count) {
        int[] result = new int[count];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // Check if the number is endy and if it will fit in the result array
            if (isEndy(nums[i]) && j < count) {
                result[j] = nums[i];
                j++;
            }
        }
        return result;
    }

    private boolean isEndy(int num) {
        if (num >= 0 && num <= 10) {
            return true;
        }
        if (num >= 90 && num <= 100) {
            return true;
        }
        return false;
    }

    public int matchUp(String[] a, String[] b) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
      /*
      Count the number of times that the 2 strings are non-empty and start with the same char.
      The strings may be any length, including 0.
       */
            if (a[i].length() > 0 && b[i].length() > 0 && a[i].charAt(0) == b[i].charAt(0)) {
                count++;
            }
            if (a[i].charAt(0) == b[i].charAt(0)) {
                count++;
            }
        }
        return count;
    }

    public int scoreUp(String[] key, String[] answers) {
        int score = 0;
        for (int i = 0; i < key.length; i++) {
            if (key[i].charAt(0) == answers[i].charAt(0)) {
                score += 4;
            }
            if (answers[i].charAt(0) != key[i].charAt(0) && answers[i].charAt(0) != '?') {
                score -= 1;
            }
        }
        return score;
    }

    public int commonTwo(String[] a, String[] b) {
        // Return the count of the number of strings which appear in both arrays
        int count = 0;
        String dupeString = "";
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i].equals(b[j]) && !dupeString.contains(a[i])) {
                    dupeString += a[i];
                    count++;
                }
            }
        }
        return count;
    }


    public String[] wordsWithout(String[] words, String target) {

        int count = 0;
        for (String s : words) {
            if (!s.equals(target)) {
                count++;
            }
        }
        String[] result = new String[count];
        int j = 0;
        for (String word : words) {
            if (!word.equals(target)) {
                result[j] = word;
                j++;
            }
        }
        return result;
    }

    public int scoresSpecial(int[] a, int[] b) {
        return findLargestSpecial(a) + findLargestSpecial(b);
    }

    private int findLargestSpecial(int[] a) {
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 10 == 0 && a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    public int sumHeights(int[] heights, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += Math.abs(heights[i] - heights[i + 1]);
        }
        return sum;
    }


    /*
    Given start/end indexes into the array, return the sum of the changes for a walk beginning at the start index and ending at the end index, however increases in height count double. For example, with the heights {5, 3, 6, 7, 2} and start=2, end=4 yields a sum of 1*2 + 5 = 7. The start end end index will both be valid indexes into the array with start <= end.

     */
    public int sumHeights2(int[] heights, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end - 1; i++) {
            if (heights[i] < heights[i + 1]) {
                sum += 2 * (Math.abs(heights[i] - heights[i + 1]));
            } else {
                sum += Math.abs(heights[i] - heights[i + 1]);
            }
        }
        return sum;
    }

    public int bigHeights(int[] heights, int start, int end) {
        int count = 0;
        for (int i = start; i <= end - 1; i++) {
            if (Math.abs(heights[i] - heights[i + 1]) >= 5) {
                count++;
            }
        }
        return count;
    }


    public int userCompare(String aName, int aId, String bName, int bId) {
        if (aName.equals(bName) && aId == bId) {
            return 0;
        }
        int result = aName.compareTo(bName);
        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        }
        if (aId < bId) {
            return -1;
        } else if (aId > bId) {
            return 1;
        }
        return 0;
    }

    public String[] mergeTwo(String[] a, String[] b, int n) {
        String[] result = new String[n];
        int j = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            int compare = a[j].compareTo(b[k]);
            if (compare <= 0) {
                result[i] = a[j++];
                if (compare == 0) {
                    k++;
                }
            } else {
                result[i] = b[k++];
            }
        }
        return result;
    }


}

