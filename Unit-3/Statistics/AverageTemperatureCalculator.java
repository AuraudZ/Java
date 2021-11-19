import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;

public class AverageTemperatureCalculator {

	public static void main(String[] args) {
		double[] highTemps = getArrayOfTempsFromFile();
		System.out.print("The average high temperature for the last 1000 days is ");
		// calculate average
		double average = Statistics.calcuateAverage(highTemps);
	}

	public static double[] getArrayOfTempsFromFile() {
		int numberOfLinesInFile = 1000;
		double[] temps = new double[numberOfLinesInFile];

		try {
			BufferedReader reader = new BufferedReader(new FileReader("temps.txt"));
			for (int i = 0; i < numberOfLinesInFile; i++) {
				temps[i] = Double.parseDouble(reader.readLine());
			}
			reader.close();
			return temps;
		} catch (IOException e) {
			return new double[] { 78.76, 79.11, 96.55, 88.01, 84.29 };
		}

	}

}
