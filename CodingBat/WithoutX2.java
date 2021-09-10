public class WithoutX2 {
    public String withoutX2(String str) {
        if (str.length() == 0) {
            return str;
        }
        if (str.length() == 1) {
            if (str.charAt(0) == 'x') {
                return "";
            } else {
                return str;
            }
        }
        if (str.charAt(0) == 'x') {
            if (str.charAt(1) == 'x') {
                return str.substring(2);
            } else {
                return str.substring(1);
            }
        } else {
            if (str.charAt(1) == 'x') {
                return str.substring(0, 1) + str.substring(2);
            } else {
                return str;
            }
        }
    }

}
