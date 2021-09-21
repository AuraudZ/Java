public class DivisorCounter {

    public static void main(String[] args) {
        System.out.println("Please Enter A Postive Integer");
        int input = TextIO.getlnInt();
        int[] nums = new int[input];
        int[] divisors = new int[input];
        int maxDivisors = 0;
        for (int i = 0; i < input; i++) {
            nums[i] = i + 1;
            divisors[i] = 0;
            for (int j = 1; j <= nums[i]; j++) {
                if (nums[i] % j == 0) {
                    divisors[i]++;
                }
            }
            if (divisors[i] > maxDivisors) {
                maxDivisors = divisors[i];
                int max = nums[i];
                int maxIndex = i;
            }
        }
        System.out.println("The number(s) between 1 and " + input + " with the most divisors are: ");
        for (int i = 0; i < input; i++) {
            if (divisors[i] == maxDivisors) {
                System.out.println(nums[i] + " ");
            }
        }
        System.out.println("They each have " + maxDivisors + " divisors.");
    }
}