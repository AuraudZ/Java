public class LastChars {
    public String lastChars(String a, String b) {
        if (a.length() == 0 || b.length() == 0) {
            return a + b;
        }
        return a.substring(0, a.length() - 1) + b.substring(b.length() - 1);
    }
}
