public class FormatTesterDemo {
	public static void main(String[] args) {
		double test1 = (double) 87 / 93; // since 87 and 93 are ints, test1 will be 0.0 unless you
											// "typecast"
		// the numbers to doubles
		double test2 = (double) 46 / 74;
		double test3 = (double) 77 / 93;
		double test4 = (double) 70 / 74;

		// How to format these so the columns line up??
		System.out.printf("%-25s %-15s %-15s %-25s", "STUDENT", "TEST1 ", "TEST2", " COMMENT");
		System.out.println("");
		System.out.printf("%-25s %-15f %-15f %-25s", "Bobby Smith", test1, test2,
				"Doing poorly recently");
		System.out.println("");
		System.out.printf("%-25s %-15f %-15f %-25s", "Bobby Smith", test3, test4, "Doing well");
		System.out.println("");
	}
}
