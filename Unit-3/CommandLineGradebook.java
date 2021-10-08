
public class CommandLineGradebook {
	public static void main(String[] args) {
		System.out.println("***Welcome to the Test Score Averager***");
		if(args.length == 0)
			System.out.println("Enter in test scores (as a decimal) on the command line!"
					+ " \nWe are old school here!");
		else {
			System.out.print("The average score is: ");
			// Calculate the average based on command line arguments
			System.out.printf("%.2f degrees", Statistics.calculateAverage(args));
		}
	}
}
