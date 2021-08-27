public class startOz {
    public String startOz(String str) {
        if (str.length() < 2) {
            return "";
        }
        if (str.length() < 2 || str.length() == 2) {
            return str.substring(0, 2);
        }
        if (str.charAt(0) == 'o' && str.charAt(1) == 'z') {
            return "oz";
        }
        if (str.charAt(0) == 'o') {
            return "o";
        }
        if (str.charAt(1) == 'z') {
            return "z";
        }
        return "";
    }

    public String startOz2(String str) {
        if (str.length() < 2) {
            return "";
        }
        if (str.length() == 2) {
            return str.substring(0, 2);
        }
        if (str.charAt(0) == 'o' && str.charAt(1) == 'z') {
            return "oz";
        }
        if (str.charAt(0) == 'o') {
            return "o";
        }
        if (str.charAt(1) == 'z') {
            return "z";
        }
        return "";
    }
}
