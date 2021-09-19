
public class ThreeN1 {
    public static void main(String[] args) {
        // This took way to long to figure out.
        // Timestamp 1632018003
        // Inatlize Vars
        // Mr Smith I spent a long time trying to figure out how to do this. But I wrote
        // a another function but it is annoying how java doesn't have multi returns so
        // I had to do some array stuff but anyway I got it working. And I hope you like
        // what I did :)
        int input = TextIO.getInt();
        int count = 0;
        int max = 0;
        System.out.println("Enter A Postive Integer");
        if (input < 0) {
            System.out.println("Please enter a positive integer");
        }
        int maxNum = 0;
        int[] ans = ThreeNPlus1(input, max, maxNum);
        maxNum = ans[0];
        max = ans[1];
        count = ans[2];
        System.out.println("The number with the most steps is " + maxNum + " with " + max + " steps");
    }

    public static int[] ThreeNPlus1(int input, int max, int maxNum) {
        int gCount = 0;
        for (int i = 1; i <= input; i++) {
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
            gCount = count;
        }
        return new int[] { maxNum, max, gCount };
    }
}
