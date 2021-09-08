public class LastChar {
    public String lastChars(String a, String b) {
        /*
         * Given 2 strings, a and b, return a new string made of the first char of a and the last
         * char of b, so "yo" and "java" yields "ya". If either string is length 0, use '@' for its
         * missing char.
         * 
         * 
         */
        String result = "";
        if (a.length() == 0) {
            result += "@";
        } else {
            result += a.charAt(0);
        }
        if (b.length() == 0) {
            result += "@";
        } else {
            result += b.charAt(b.length() - 1);
        }
        return result;
    }
}


