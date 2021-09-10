public class WithoutX {
    public String withoutX(String str) {
        int n = str.length();
        if (str.length() == 0) {
            return str;
        }
        if (n < 2) {
            return str;
        }
        if (str.charAt(0) == 'x' && str.charAt(n - 1) == 'x') {
            return str.substring(1, n - 1);
        }
        if (str.charAt(0) == 'x') {
            return str.substring(1);
        }
        if (str.charAt(n - 1) == 'x') {
            return str.substring(0, n - 1);
        }

        return str;
    }

}
