import java.util.Arrays;

public class StatisticsTester {

	public static void main(String[] args) {
		String[] stringArray = new String[] { "2", "4", "6", "8" };
		double[] doubleArray = new double[] { 78.4, 98.4, 41.3 };
		int[] intArray = new int[] { 5, 5, 5, 6, 5, 5, 5, 5, 5, 5, 5, 5 };

		// Test median (passing in String[])
		System.out.println("The median of " + Arrays.toString(stringArray) + " is: ");
		System.out.println(Statistics.calculateMedian(stringArray));

		// Test median (passing in double[])
		System.out.println("The median of " + Arrays.toString(doubleArray) + " is: ");
		System.out.println(Statistics.calculateMedian(doubleArray));

		// Test standard deviation (passing in double[])
		System.out.println("The standard deviation of " + Arrays.toString(doubleArray) + " is: ");
		System.out.println(Statistics.calculateStdDev(doubleArray));

		// Test standard deviation (passing in int[])
		System.out.println("The standard deviation of " + Arrays.toString(intArray) + " is: ");
		System.out.println(Statistics.calculateStdDev(intArray));

	}
}
