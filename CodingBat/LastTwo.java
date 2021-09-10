public class LastTwo {
    /*
     * 
     * Given a string of any length, return a new string where the last 2 chars, if present, are
     * swapped, so "coding" yields "codign".
     * 
     * 
     * lastTwo("coding") → "codign" lastTwo("cat") → "cta" lastTwo("ab") → "ba"
     */
    public String lastTwo(String str) {
        int n = str.length();
        if (n < 2) {
            return str;
        }
        String lastTwo = str.substring(n - 2);

        return str.substring(0, n - 2) + lastTwo.charAt(1) + lastTwo.charAt(0);
    }

}
