public class MiscFunStuff {
	public static void main(String[] args) {
		System.out.println("Welcome! Enter your choice.");
		System.out.print("(1) Convert in. to cm. (2) Even or Odd  (3) Advice: ");
		int choice = TextIO.readInt();
		if (choice == 1) {
			System.out.print("Enter inches: ");
			double inches = TextIO.readDouble();
			convert(inches);
		} else if (choice == 2) {
			System.out.print("Enter a number: ");
			int num = TextIO.readInt();
			evenOrOdd(num);
		}

		else if (choice == 3) {
			System.out.print("Advice Is: " + advice());
		}
	}


	public static void convert() {
		System.out.print("Enter your height in inches: ");

		double heightInInches = TextIO.readDouble();
		System.out.println("Your height in cm is " + inchesToCm(heightInInches));
	}

	public static double inchesToCm(double inches) {
		return inches * 2.54;
	}

	public static void evenOdd(double number) {
		System.out.print("Give me an integer: ");
		if (number % 2 == 0) {
			System.out.println("It's even!");
		} else if (number % 2 == 1) {
			System.out.println("It's odd!");
		} else {
			System.out.println("It's neither...enter an integer!");
		}
	}

	public static void randAdvice() { // practice debugging with System.out!
		int rand = (int) Math.random() * 4;
		// generate a rand int from 1 to 3


		String advice = "";

		if (rand == 1)
			advice = "try harder";
		else if (rand == 2)
			advice = "keep going!";
		else if (rand == 3)
			advice = "deep breaths";

		System.out.println(advice);
	}
}

