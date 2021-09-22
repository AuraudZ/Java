public class DivisorCounter {

    public static void main(String[] args) {
        System.out.println("Please Enter A Postive Integer");
        int input = TextIO.getlnInt();
        int[] nums = new int[input];
        int[] divisors = new int[input];
        int maxDivisors = 0;
        if (input < 1) {
            System.out.println("Please Enter A Positive Integer");
            // It did not work I don't know why
        }
        for (int i = 0; i < input; i++) { // Running though every number from 1-imput
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
                int maxIndex = i;
            }
        }
        System.out.println("The number(s) between 1 and " + input + " with the most divisors are: "); // Print the max
                                                                                                      // // number
        for (int i = 0; i < input; i++) { // Print the array of divisors for each number
            if (divisors[i] == maxDivisors) {
                System.out.println(nums[i] + " ");
            }
        }
        System.out.println("They each have " + maxDivisors + " divisors."); // Print the number of divisors // for the
                                                                            // max number
    }
}