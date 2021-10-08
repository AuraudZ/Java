
public class MidpointCalculator {

	public static void main(String[] args) {
		int x1 = 3; int y1 = 5;
		int x2 = -7; int y2 = 0;
		
		String x3 = "8"; String y3 = "10";
		String x4 = "-10"; String y4 = "0";
		
		System.out.print("The midpoint between (" + x1  + ", " + y1 + ")");
		System.out.print(" and (" + x2  + ", " + y2 + ") is: ");
		// Calculate and print midpoint, given int inputs 
		System.out.println("(" + Statistics.calculateAverage(x1, x2) + ", "
				+ Statistics.calculateAverage(y1, y2) + ")");
		
		System.out.print("The midpoint between (" + x3  + ", " + y3 + ")");
		System.out.print(" and (" + x4  + ", " + y4 + ") is: ");
		// Calculate and print midpoint, given String inputs
		System.out.println("(" + Statistics.calculateAverage(x3, x4) + ", "
				+ Statistics.calculateAverage(y3, y4) + ")");

	}

}
