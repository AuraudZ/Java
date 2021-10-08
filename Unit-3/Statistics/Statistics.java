import java.util.Arrays;

public class Statistics {

    public static double calculateAverage(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    public static double calculateAverage(int[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    public static double calculateAverage(double x, double y) {
        return (double) (x + y) / 2;
    }

    public static double calculateAverage(String x, String y) {
        return (Double.parseDouble(x) + Double.parseDouble(y)) / 2;
    }

    public static double calculateAverage(int x, int y) {
        return (x + y) / 2;
    }

    public static double calculateAverage(String[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i].length();
        }
        return sum / array.length;
    }

    public static double calculateMedian(double[] array) {
        Arrays.sort(array);
        if (array.length % 2 == 0) {
            return (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        } else {
            return array[array.length / 2];
        }
    }

    public static double calculateMedian(int[] array) {
        Arrays.sort(array);
        double[] doubleArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = array[i];
        }
        return calculateMedian(doubleArray);
    }

    public static double calculateMedian(String[] array) {
        Arrays.sort(array);
        if (array.length % 2 == 0) {
            return (Double.parseDouble(array[array.length / 2]) + Double.parseDouble(array[array.length / 2 - 1])) / 2;
        } else {
            return Double.parseDouble(array[array.length / 2]);
        }
    }

    public static double calculateStdDev(double[] array) {
        double sum = 0;
        double average = calculateAverage(array);
        for (int i = 0; i < array.length; i++) {
            sum += Math.pow(array[i] - average, 2);
        }
        return Math.sqrt(sum / array.length);
    }

    public static double calculateStdDev(String[] array) {
        double[] doubleArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = Double.parseDouble(array[i]);
        }
        return calculateStdDev(doubleArray);
    }

    public static double calculateStdDev(int[] array) {
        double[] doubleArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = array[i];
        }
        return calculateStdDev(doubleArray);
    }
}
