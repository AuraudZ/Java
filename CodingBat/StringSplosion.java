public class  StringSplosion {
    public String stringSplosion(String str) {
        //using while
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result += str.substring(0, i + 1);
        }
        return result;
    }
}}

}
