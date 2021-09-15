public class FrontTimes {
    public String frontTimes(String str, int n) {
        // using while loop
        String result = "";
        int i = 0;
        while (i < n) {
            result += str.substring(0, str.length() - 1);
            i++;
        }
        return result;
    }

    public String frontTimes(String str, int n) {
        // using for loop
        String result = "";
        for (int i = 0; i < n; i++) {
            result += str.substring(0, str.length() - 1);
        }
        return result;
    }

    public String frontTimes(String str, int n) {
        // using recursion
        if (str.length() == 0) {
            return "";
        } else if (n == 0) {
            return "";
        } else {
            return str.substring(0, str.length() - 1) + str.substring(0, str.length() - 1);
        }
    }
}
