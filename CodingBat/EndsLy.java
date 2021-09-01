import jdk.internal.util.xml.impl.ReaderUTF16;

public class EndsLy {
    public boolean endsLy(String str) {
        if (str.length() < 2) {
            return false;
        }
        if (str.substring(str.length() - 2).equals("ly")) {
            return true;
        }
        return false;
    }

    public String middleThree(String str) {
        if (str.length() < 3) {
        }
        return str.substring((str.length() / 2) - 1, (str.length() / 2) + 2);
    }

    public boolean hasBad(String str) {
        /*
         * Given a string, return true if "bad" appears starting at index 0 or 1 in the string, such
         * as with "badxxx" or "xbadxx" but not "xxbadxx". The string may be any length, including
         * 0. Note: use .equals() to compare 2 strings.
         * 
         */

        if (str.length() < 3) {
            return false;
        }
        if (str.length() == 3) {
            if (str.substring(0, 3).equals("bad")) {
                return true;
            }
        }
        if (str.substring(1, 4).equals("bad")) {
            return true;
        }

        return false;
    }

}
