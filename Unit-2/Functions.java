
public class Functions {

	// Use the main subroutine to check your other subroutines.
	public static void main(String[] args) {
		double testSlope = slope(1, 2, 3, 7);
		System.out.println(testSlope); // testSlope should be 5/2 = 2.5

		// remember we can also System.out the result of the subroutine
		// without storing it in another variable like above.
		System.out.println("Quadratic Solution " + quadraticSolution(1, 6, 9));

		// You should write additional tests to make sure your functions
		// work for different cases (like the empty String).
		System.out.println("Geometric Mean: " + geometricMean(4, 9));
		System.out.println("First And Last " + firstAndLast("Whdither"));
		System.out.println("Middle Capitalization " + middleCapitalization("fidEhfiKldhIK"));
		System.out.println(
				"Middle Capitalization 2 " + middleCapitalization("ergjerngojhiuehfiryujnwef"));
	}

	/**
	 * This functions returns the slope of the line through the points (x1, y1) and (x2, y2).
	 * Precondition: The line must have a defined slope.
	 * 
	 * @param x1 the x coordinate of the first point
	 * @param y1 the y coordinate of the second point
	 * @param x2 the x coordinate of the first point
	 * @param y2 the y coordinate of the second point
	 * @return the slope of the line
	 */
	public static double slope(double x1, double y1, double x2, double y2) {
		double dY = y2 - y1;
		double dX = x2 - x1;
		double slope = dY / dX;
		return slope;
	}

	/**
	 * This function returns the geometric mean of number1 and number2 Precondition: number1 and
	 * number2 must both be positive
	 * 
	 * @param number1 any positive number
	 * @param number2 any positive number
	 * @return the geometric mean of the numbers
	 */
	public static double geometricMean(double number1, double number2) {
		if (number1 <= 0 || number2 <= 0) {
			return 0;
		} else {
			double geometricMean = Math.sqrt(number1 * number2);
			return geometricMean;
		}
	}

	/**
	 * This function returns the larger of the two solutions to the quadratic equation a*x^2 + b*x +
	 * c = 0. Precondition: A solution must exist.
	 * 
	 * @param a the coefficient of the squared term
	 * @param b the coefficient of the linear term
	 * @param c the constant term
	 * @return the larger of the two solutions to the equation
	 */
	public static double quadraticSolution(double a, double b, double c) {
		double discriminant = Math.pow(b, 2) - 4 * a * c;
		if (discriminant < 0) {
			return 0;
		} else {
			double solution1 = (-b + Math.sqrt(discriminant)) / (2 * a);
			double solution2 = (-b - Math.sqrt(discriminant)) / (2 * a);
			if (solution1 > solution2) {
				return solution1;
			} else {
				return solution2;
			}
		}
	}

	/**
	 * This function returns a String consisting of the first and last letters of the parameter str.
	 * Example: firstAndLast("hello") --> "ho" Example: firstAndLast("x") --> "xx" Precondition: the
	 * parameter str must be a String of alpha-characters
	 * 
	 * @param str any non-empty String
	 * @return the first and last letters of the parameter str
	 */
	public static String firstAndLast(String str) {
		String first = str.substring(0, 1);
		String last = str.substring(str.length() - 1);
		return first + last;
	}

	/**
	 * This function returns a modified version of the parameter str with the first and last letters
	 * lowercase and all the remaining letters capitalized. Example: middleCapitalization("Hello")
	 * --> "hELLo" Example: middleCapitalization("X") --> "x" Example: middleCapitalization("oK")
	 * --> "ok" Example: middleCapitalization("") --> ""
	 * 
	 * @param str any String
	 * @return a modified version of the parameter str
	 */
	public static String middleCapitalization(String str) {
		String first = str.substring(0, 1);
		String last = str.substring(str.length() - 1);
		String middle = str.substring(1, str.length() - 1);
		String firstLower = first.toLowerCase();
		String lastLower = last.toLowerCase();
		String middleCap = middle.toUpperCase();
		return firstLower + middleCap + lastLower;

	}

}
