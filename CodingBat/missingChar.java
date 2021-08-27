public class missingChar {
    public static String missingChar(String str, int n) {
        String front = str.substring(0, n);
        String back = str.substring(n + 1);
        System.out.println(front);
        System.out.println(back);
        return front + back;
    }

    public static void main(String[] args) {
        System.out.println(missingChar("kitten", 1));
    }
}
