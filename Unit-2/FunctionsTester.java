public class FunctionsTester {

	public static void main(String[] args) {
		final int NUM_TESTS = 10;
		int numCorrect = 0;
		
		System.out.println("\n*** Starting tests ***\n");

		if(slopeCorrect()) {  // run test #1
			numCorrect++;
			System.out.println("Test #1 passed.");
		}
		else {
			System.out.println("Test #1 (Slope) failed!!");
		}
		
		if(geometricMeanCorrect()) { // run test #2
			numCorrect++;
			System.out.println("Test #2 passed.");
		}
		else {
			System.out.println("Test #2 (Geometric Mean) failed!!");
		}
		
		if(quadraticSolutionCorrect()) { // run test #3
			numCorrect++;
			System.out.println("Test #3 passed.");
		}
		else {
			System.out.println("Test #3 (Quadratic Solution) failed!!");
		}
		
		if(quadraticSolutionZeroCorrect()) { // run test #4
			numCorrect++;
			System.out.println("Test #4 passed.");
		}
		else {
			System.out.println("Test #4 (Quadratic Solution Zero) failed!!");
		}
		
		if(firstAndLastCorrect()) { // run test #5
			numCorrect++;
			System.out.println("Test #5 passed.");
		}
		else {
			System.out.println("Test #5 (First and Last) failed!!");
		}
		
		if(firstAndLastSingleLetterCorrect()) { //  run test #6
			numCorrect++;
			System.out.println("Test #6 passed.");
		}
		else {
			System.out.println("Test #6 (First and Last Single Letter) failed!!");
		}
		
		if(middleCapitalizationCorrect()) { // run test #7
			numCorrect++;
			System.out.println("Test #7 passed.");
		}
		else {
			System.out.println("Test #7 (Middle Capitalization) failed!!");
		}
		
		if(middleCapitalizationTwoLettersCorrect()) { // run test #8
			numCorrect++;
			System.out.println("Test #8 passed.");
		}
		else {
			System.out.println("Test #8 (Middle Capitalization Two Letters) failed!!");
		}
		
		if(middleCapitalizationOneLetterCorrect()) { // run test #9
			numCorrect++;
			System.out.println("Test #9 passed.");
		}
		else {
			System.out.println("Test #9 (Middle Capitalization One Letter) failed!!");
		}
		
		if(middleCapitalizationNoLettersCorrect()) { // run test #10
			numCorrect++;
			System.out.println("Test #10 passed.");
		}
		else {
			System.out.println("Test #10 (Middle Capitalization No Letters) failed!!");
		}
		
		System.out.println("\n*** Tests complete ***\n");
		System.out.println("You passed " + numCorrect + " out of " + NUM_TESTS + " tests.");
	}
	

	// test #1
	public static boolean slopeCorrect() {
		if(Math.abs(Functions.slope(-8.7, 6.7, 103.4, -11.4) - (-0.161463)) < 0.001) {
			return true;
		}
		return false;
	}

	// test #2
	public static boolean geometricMeanCorrect() {
		if(Math.abs(Functions.geometricMean(3.12, 11.4) - (5.96389)) < 0.001) {
			return true;
		}
		return false;
	}

	// test #3
	public static boolean quadraticSolutionCorrect() {
		if(Math.abs(Functions.quadraticSolution(1.8,-7.0,-3.7) - (4.36031)) < 0.001) {
			return true;
		}
		return false;
	}
	
	//test #4
	public static boolean quadraticSolutionZeroCorrect() {
		if(Math.abs(Functions.quadraticSolution(1.8,0,-3.7) - (1.43372)) < 0.001) {
			return true;
		}
		return false;
	}

	// test #5
	public static boolean firstAndLastCorrect() {
		if(Functions.firstAndLast("#ijghidilfKK").equals("#K")) {
			return true;
		}
		return false;
		
	}
	
	// test #6
	public static boolean firstAndLastSingleLetterCorrect() {
		if(Functions.firstAndLast("T").equals("TT")) {
			return true;
		}
		return false;
	}

	// test #7
	public static boolean middleCapitalizationCorrect() {
		if(Functions.middleCapitalization("CkIHelKD").equals("cKIHELKd")) {
			return true;
		}
		return false;
	}
	
	// test #8
	public static boolean middleCapitalizationTwoLettersCorrect() {
		if(Functions.middleCapitalization("xY").equals("xy")) {
			return true;
		}
		return false;
	}
	
	
	public static boolean middleCapitalizationOneLetterCorrect() {
		if(Functions.middleCapitalization("Q").equals("q")) {
			return true;
		}
		return false;
	}
	
	
	public static boolean middleCapitalizationNoLettersCorrect() {
		if(Functions.middleCapitalization("").equals("")) {
			return true;
		}
		return false;
	}

}
