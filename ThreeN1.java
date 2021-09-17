
public class ThreeN1 {
    public static void main(String[] args) {
        System.out.println("Enter A Postive Integer");
        // check if n is a positive integer

        int n = TextIO.getInt();
        if (n < 0) {
            System.out.println("Invalid Input");
            return;
        } else {
            int x = 0;
            while (n != 1) {
                if (n % 2 == 0) {
                    n = n / 2;
                } else {
                    n = 3 * n + 1;
                }
                // System.out.println(n);
                x++;
            }
            System.out.println(x + " steps");

        }
    }

}
