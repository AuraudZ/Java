import java.util.List;

public class CodingBat {
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
        System.out.println(cb.count11("11x111x1111"));
        System.out.println(cb.strDist("cccatcowcatxx", "cat"));
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

    // compute recursively the largest substring which starts and ends with sub and return its
    // length.

    public int strDist(String str, String sub) {
        if (str.length() < sub.length()) {
            return 0;
        }
        if (str.substring(0, sub.length()).equals(sub)) {
            return sub.length() + strDist(str.substring(sub.length()), sub);
        }
        return strDist(str.substring(1), sub);
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



    public String noX(String str) {

        if (str.length() == 0) {
            return "";
        }
        if (str.charAt(0) == 'x') {
            return noX(str.substring(1));
        }
        return str.charAt(0) + noX(str.substring(1));
    }



}
