public class startOz {
    public String startOz(String str) {
        String result = "";
        if (str.length() < 2) {
            result = "";
        } else if (str.charAt(0) == 'o' && str.charAt(1) == 'z') {
            result = "oz";
        } else if (str.charAt(0) == 'o') {
            result = "o";
        } else if (str.charAt(1) == 'z') {
            result = "z";
        } else {
            result = "";
        }
        return result;
    }
}

