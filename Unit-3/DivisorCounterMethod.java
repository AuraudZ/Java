
public class DivisorCounterMethod {
    public static void main(String[] args) {
        System.out.println("Please Enter A Postive Integer");
        int input = TextIO.getlnInt();
        System.out.println("The number(s) between 1 and " + input + " with the most divisors are: "); // Print the max
        // number

        int[] divisors = divisorCounter(input);
        for (int i = 0; i < input; i++) { // Print the array of divisors for each number

        }
    }

    public static int[] divisorCounter(int input) {

        int[] nums = new int[input];
        int[] divisors = new int[input];
        int maxDivisors = 0;
        int maxIndex = 0;
        for (int i = 0; i < input; i++) {
            nums[i] = i + 1;
            divisors[i] = 0;
            for (int j = 1; j <= nums[i]; j++) { // Runs though every value in the array
                if (nums[i] % j == 0) {
                    divisors[i]++; // Add one to divisors array if divisble
                }
            }
            if (divisors[i] > maxDivisors) {
                maxDivisors = divisors[i]; // Checks if the current number has more divisors than the max
                int max = nums[i];
                maxIndex = i;
            }
        }
        // return divsors and nums
        return nums;
    }
}
