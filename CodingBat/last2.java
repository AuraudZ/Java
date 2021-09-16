package CodingBat;

public class last2 {
    public int last2(String str) {
        if (str.length() < 2) {
            return 0;
        }

        int index = 0;
        int count = 0;
        String last2 = str.substring(str.length() - 2, str.length());
        while (index < str.length() - 2) {
            if (str.substring(index, index + 2).equals(last2)) {
                count++;
            }
            index++;
        }
        return count;
    }
}

