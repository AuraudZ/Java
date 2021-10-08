import java.util.Arrays;

public class DieRollingExperiment {
	public static void main(String[] args) {
		int numRolls = 1000;
		int[] roll = new int[numRolls];
		for (int i = 0; i < roll.length; i++) {
			roll[i] = (int)(Math.random() * 6 + 1);
		}
		System.out.println("A dice has been rolled " + numRolls + " times.");
		System.out.println("Here are the results: " + Arrays.toString(roll));
		System.out.print("The average roll was a: "); //calculate average
		System.out.printf("%.2f", );
	}
}
