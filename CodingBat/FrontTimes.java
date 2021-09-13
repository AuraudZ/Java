public class FrontTimes {
    public String frontTimes(String str, int n) {
        // using while loop
        String result = "";
        int i = 0;
        while (i < n) {
            result += str.substring(0, 3);
            i++;
        }
        return result;
    }
}
