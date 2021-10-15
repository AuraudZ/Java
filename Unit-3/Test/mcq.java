
public class mcq {
    public static void main(String[] args) {
        int a = 0;
        for (int f = 3; f < 15; f++) {
            if (f % 2 == 1) {
                a = a + f;
            }
        }
        System.out.println(a);
    }
}
