
public class ThreeN1Old {

    public static void main(String[] args) {
        // This took way to long to figure out.
        // Timestamp 1632018003
        System.out.println("Enter A Postive Integer");
        int input = TextIO.getInt();
        int max = 0;
        if (input < 0) {
            System.out.println("Please enter a positive integer");
        }
        int maxNum = 0;
        for (int i = 1; i <= input; i++) {
            System.out.println("attempt #" + i);
            int count = 0;
            int num = i;
            while (num != 1) {
                if (num % 2 == 0) {
                    num = num / 2;
                } else {
                    num = 3 * num + 1;
                }
                count++;
            }
            if (count > max) {
                max = count;
                maxNum = i;
            }
        }
        System.out.println("The number with the most steps is " + maxNum + " with " + max + " steps");
    }
}
