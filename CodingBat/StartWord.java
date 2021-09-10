public class StartWord {

    public String startWord(String str, String word) {
        if (str.length() < word.length()) {
            return "";
        }
        if (str.substring(0, word.length()).equals(word)) {
            return str;
        }
        if (str.substring(1, word.length() + 1).equals(word)) {
            return str.substring(1);
        }
        if (str.substring(2, word.length() + 2).equals(word)) {
            return str.substring(2);
        }
        return "";

    }
}


