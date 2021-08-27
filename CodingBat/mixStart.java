public class mixStart {
    public boolean mixStart(String str) {
        if (str.length() < 2) {
            return false;
        }
        if (str.substring(1, 3).equals("ix")) {

            return true;

        }
        return false;
    }

}
