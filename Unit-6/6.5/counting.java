public class counting {
    public static void main(String[] args) {
        int n = 3;
        int count = 0;
        while (count < 4) {
            System.out.println("n is " + n);
            if (n == 6) {
                System.out.println("Hello");
            } else {
                System.out.println("Goodbye");
            }
            n += 2;
            count++;
        }
    }
}
