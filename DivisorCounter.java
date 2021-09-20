public class DivisorCounter {
    public static void main(String[] args) {
        System.out.println("Please Enter a Postive Intger ");
        // 2) Figures out which integer(s) between 1 and the inputted number has the
        // greatest amount of divisors

        int divisor = TextIO.getlnInt();
        int count = 0;

        for (int i = 1; i <= divisor; i++) {
            if (divisor % i == 0) {
                count++;
            }
        }
        System.out.println("The Amount of divisors is " + count);
    }

    public int[] DivisorCounter(int number) {
        return new int[number];
    }
}