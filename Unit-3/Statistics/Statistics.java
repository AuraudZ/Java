import java.util.Arrays;

public class Statistics {

    /**
     * @param array Array of doubles
     * @return double The average of the array
     */
    public static double calculateAverage(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    /**
     * @param array
     * @return double
     */
    public static double calculateAverage(int[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    /**
     * @param array
     * @return double
     */
    public static double calcuateAverage(double[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    /**
     * @param arr
     * @return double
     */
    public static double calcuateAverage(int[] arr) {
        return calcuateAverage(arr);
    }

    /**
     * @param x
     * @param y
     * @return double
     */
    public static double calculateAverage(double x, double y) {
        return (double) (x + y) / 2;
    }

    /**
     * @param x
     * @param y
     * @return double
     */
    public static double calculateAverage(String x, String y) {
        return (Double.parseDouble(x) + Double.parseDouble(y)) / 2;
    }

    /**
     * @param x
     * @param y
     * @return double
     */
    public static double calculateAverage(int x, int y) {
        return (x + y) / 2;
    }

    /**
     * @param array
     * @return double
     */
    public static double calculateAverage(String[] array) {
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i].length();
        }
        return sum / array.length;
    }

    /**
     * @param array
     * @return double
     */
    public static double calculateMedian(double[] array) {
        Arrays.sort(array);
        if (array.length % 2 == 0) {
            return (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        } else {
            return array[array.length / 2];
        }
    }

    /**
     * @param array
     * @return double
     */
    public static double calculateMedian(int[] array) {
        Arrays.sort(array);
        double[] doubleArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = array[i];
        }
        return calculateMedian(doubleArray);
    }

    /**
     * @param array
     * @return double
     */
    public static double calculateMedian(String[] array) {
        Arrays.sort(array);
        if (array.length % 2 == 0) {
            return (Double.parseDouble(array[array.length / 2]) + Double.parseDouble(array[array.length / 2 - 1])) / 2;
        } else {
            return Double.parseDouble(array[array.length / 2]);
        }
    }

    /**
     * @param array
     * @return double
     */
    public static double calculateStdDev(double[] array) {
        double sum = 0;
        double average = calculateAverage(array);
        for (int i = 0; i < array.length; i++) {
            sum += Math.pow(array[i] - average, 2);
        }
        return Math.sqrt(sum / array.length);
    }

    /**
     * @param array
     * @return double
     */
    public static double calculateStdDev(String[] array) {
        double[] doubleArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = Double.parseDouble(array[i]);
        }
        return calculateStdDev(doubleArray);
    }

    /**
     * @param array
     * @return double
     */
    public static double calculateStdDev(int[] array) {
        double[] doubleArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            doubleArray[i] = array[i];
        }
        return calculateStdDev(doubleArray);
    }
}
