public class threen1 {

    public static void main(String[] args) {
        // Plan:
        // get a positive integer
        int input;
        do {
            System.out.print("Enter a positive integer: ");
            input = TextIO.getlnInt();
        } while (input <= 0);
        int max = 0;
        int maxNum = 0;
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
            System.out.println("count " + count);
            if (count > max) {
                max = count;
                maxNum = i;
            }
            gCount = count;
        }
        System.out.println(" The number of steps is " + gCount);
        System.out.println("The number with the most steps is " + maxNum + " with " + max + " steps");

    }
}
