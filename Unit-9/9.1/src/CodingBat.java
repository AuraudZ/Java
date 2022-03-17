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


}
