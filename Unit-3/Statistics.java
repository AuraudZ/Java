
public class Statistics {
    public static int calculateAverage() {
        int sum = 0;
        int count = 0;
        int average = 0;
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            count++;
        }
        average = sum / count;
        return average;
    }
}