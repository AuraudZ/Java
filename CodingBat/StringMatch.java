package CodingBat;

public class StringMatch {
    public int stringMatch(String a, String b) {
        // Given 2 strings, a and b, return the number of the positions where they contain the same
        // length 2 substring. So "xxcaazz" and "xxbaaz" yields 3, since the "xx", "aa", and "az"
        // substrings appear in the same place in both strings.
        while (a.length() >= 2 && b.length() >= 2) {
            if (a.substring(0, 2).equals(b.substring(0, 2))) {
                return 1 + stringMatch(a.substring(1), b.substring(1));
            }
            a = a.substring(1);
            b = b.substring(1);
        }
        return 0;
    }
}
