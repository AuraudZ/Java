public class NTwice {
    public String nTwice(String str, int n) {
        /*
         * 
         * Given a string and an int n, return a string made of the first and last n chars from the
         * string. The string length will be at least n.
         * 
         * 
         * nTwice("Hello", 2) → "Helo" nTwice("Chocolate", 3) → "Choate" nTwice("Chocolate", 1) →
         * "Ce"
         */
        String first = str.substring(0, n);
        String last = str.substring(str.length() - n);
        return first + last;

    }
}

