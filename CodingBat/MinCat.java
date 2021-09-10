public class MinCat {
    public String minCat(String a, String b) {

        if (a.length() == 0) {
            return "";
        }
        if (b.length() == 0) {
            return "";
        }
        if (a.length() == 1) {
            return a + b;
        }
        if (b.length() == 1) {
            return a + b;
        }
        if (a.length() > b.length()) {
            return a.substring(a.length() - b.length()) + b;
        } else if (a.length() < b.length()) {
            return a + b.substring(b.length() - a.length());
        } else {
            return a + b;
        }
    }
}
