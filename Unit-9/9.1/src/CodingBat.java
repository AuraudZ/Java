import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CodingBat {
    // Recursion-1 CodingBats Java Solution
    public String parenBit(String str) {
        if (str.charAt(0) != '(') {
            return parenBit(str.substring(1));
        }
        if (str.charAt(str.length() - 1) != ')') {
            return parenBit(str.substring(0, str.length() - 1));
        }

        return str;
    }

    public static void main(String[] args) {
        CodingBat cb = new CodingBat();

        int[] testArray = {1, 2, 11};

        System.out.println(cb.array11(testArray, 0));

    }

    public int strCount(String str, String sub) {
        if (str.length() < sub.length()) {
            return 0;
        }
        if (str.substring(0, sub.length()).equals(sub)) {
            return 1 + strCount(str.substring(sub.length()), sub); // Recursive count of substrings
        }
        return strCount(str.substring(1), sub);
    }

    public int count11(String str) {
        if (str.length() < 2)
            return 0;
        if (str.substring(0, 2).equals("11")) {
            return 1 + count11(str.substring(2));
        }
        return count11(str.substring(1));
    }
    /*
    Given an array of ints, compute recursively the number of times
    that the value 11 appears in the array.
    We'll use the convention of considering only the part of the array that begins at the
    given index. In this way, a recursive call can pass index+1 to move down the array.
    The initial call will pass in index as 0.
     */
    public int array11(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        if (nums[index] == 11) {
            return 1 + array11(nums, index + 1);
        }
        return array11(nums, index + 1);
    }
    /*
    Given an array of ints, compute recursively if the array contains somewhere a value followed
     in the array by that value times 10.
     We'll use the convention of considering only the part of the array that begins at the given index.
     In this way, a recursive call can pass index+1 to move down the array.
     The initial call will pass in index as 0.
     */
    public boolean array220(int[] nums, int index) {
        // Base case
        if(index >= nums.length - 1) {
            return false;
        }
        if(nums[index] * 10 == nums[index + 1]) {
            return true;
        }
        return array220(nums, index + 1);
    }


    public String allStar(String str) {
        if (str.length() == 1) {
            return str;
        }
        if(str.length() == 0) {
            return "";
        }

        return str.substring(0, 1) + "*" + allStar(str.substring(1));
    }





    //    Given a string and a non-empty substring sub,
//    compute recursively the largest substring which starts and ends with sub and
//    return its length.
    /*
    strDist("catcowcat", "cat") → 9
    strDist("catcowcat", "cow") → 3
    strDist("cccatcowcatxx", "cat") → 9
     */
    public int strDist(String str, String sub) {
        if (str.length() < sub.length()) {
            return 0;
        }
        if (str.substring(0, sub.length()).equals(sub)) {
            return sub.length() + strDist(str.substring(sub.length()), sub);
        }
        return strDist(str.substring(1), sub);
    }

    public List<Integer> no9(List<Integer> nums) {
        return nums.stream().filter(n -> n%10 != 9).collect(Collectors.toList());
    }

    public int[] copyEvens(int[] nums, int count) {
        int[] evens = new int[count];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                evens[index] = nums[i];
                index++;
            }
        }
        return evens;
    }

 /*
Return an array that contains exactly the same numbers as the given array,
but rearranged so that every 4 is immediately followed by a 5.
Do not move the 4's, but every other number may move.
The array contains the same number of 4's and 5's,
and every 4 has a number after it that is not a 4.In this version,
5's may appear anywhere in the original array
  */


    public int[] fix45(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 4) {
                index = i;
                break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != index && nums[i] == 5) {
                nums[i] = 4;
                nums[index] = 5;
                break;
            }
        }
        return nums;
    }




    public boolean array6(int[] nums, int index) {
        if (index == nums.length) {
            return false;
        }
        if (nums[index] == 6) {
            return true;
        }
        return array6(nums, index + 1);
    }

    public String changePi(String str) {
        if (str.length() < 3) {
            return str;
        }
        if (str.substring(0, 3).equals("pi")) {
            return "3.14" + changePi(str.substring(3));
        }

        return str.substring(0, 1) + changePi(str.substring(1));
    }

    // Using lambda expression
    public List<String> no34(List<String> strings) {
        strings.removeIf(s -> s.contains("3") || s.contains("4"));
        return strings;
    }

    public static int quadraticEq(int a, int b, int c) {
        if (a == 0) {
            return -c / b;
        }
        int discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return -1;
        }
        if (discriminant == 0) {
            return -b / (2 * a);
        }
        return (int) ((-b + Math.sqrt(discriminant)) / (2 * a));
    }

    public String noX(String str) {

        if (str.length() == 0) {
            return "";
        }
        if (str.charAt(0) == 'x') {
            return noX(str.substring(1));
        }
        return str.charAt(0) + noX(str.substring(1));
    }

    // Given a string, compute recursively the number of times lowercase "hi" appears in the
    // string, however do not count "hi" that have an 'x' immediately before them.
    public int countHi2(String str) {
        if (str.length() < 2) {
            return 0;
        }
        if(str.length() >= 3 && str.substring(0,3).equals("xhi")){
            return countHi2(str.substring(3));
        }
        else if (str.substring(0, 2).equals("hi")) {
            return 1 + countHi2(str.substring(2));
        }
        return countHi2(str.substring(1));
    }




}
