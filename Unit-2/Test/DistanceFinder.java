// **** Enter your name: Auraud Zarafshar
// ** Don't rename class name, leave as DistanceFinder
public class DistanceFinder {

	/**
	 * The main subroutine is written for you for this class. It calls the subroutine distance(x1,
	 * y1, x2, y2) which gives the distance between the two coordinates.
	 *
	 * The output for the main subroutine should be: Distance between (0,0) and (3,4): 5.0 Distance
	 * between (1,1) and (7,9): 10.0 Distance between (2,2) and (3,3): 1.4142135623730951
	 */
	public static void main(String[] args) {
		double distance1 = distance(0, 0, 3, 4);
		System.out.println("Distance between (0,0) and (3,4): " + distance1);

		double distance2 = distance(1, 1, 7, 9);
		System.out.println("Distance between (1,1) and (7,9): " + distance2);

		double distance3 = distance(2, 2, 3, 3);
		System.out.println("Distance between (2,2) and (3,3): " + distance3);
	}

	/**
	 * This function, given a pair of coordinates, returns the distance between them.
	 * 
	 * Remember the distance formula is just based off of the Pythagorean Theorem, c^2 = a^2 + b^2,
	 * where c represents the hypotenuse,or the distance between the two points.
	 *
	 * @param x1, y1 the coordinates of the first point
	 * @param x2, y2 the coordinates of the second point
	 * @return a double representing the distance between the two points
	 **/
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}

