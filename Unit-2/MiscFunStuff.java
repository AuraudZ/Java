public class MiscFunStuff {

	public static void main(String[] args) {
		System.out.println("Welcome! Enter your choice.");
		System.out.print("(1) Convert in. to cm. (2) Even or Odd  (3) Advice: ");
		int choice;
		
		if(choice == 1)
			
		else if(choice == 2)
			
		else if(choice == 3) 
			
	
			
			
		
		
	}
	
	public static void convert() {
		System.out.print("Enter your height in inches: ");
		double heightInInches;
		System.out.println("Your height in cm is " + inchesToCm(heightInInches));
	}
	
	inchesToCm() {
		
	}
	
	public static void evenOdd() {
		System.out.print("Give me an integer: ");
		double number;
		if(       ) 
			System.out.println("It's even!");
		else if(      )
			System.out.println("It's odd!");
		else
			System.out.println("It's neither...enter an integer!");
	}
	
	public static void randAdvice() { // practice debugging with System.out!
		int rand = Math.random(); // ** generate a rand int from 1 to 3
		
		String advice = ""; 
  
		if(rand == 1)  
			advice = "try harder";
		else if(rand == 2)
			advice = "keep going!";	
		else if(rand == 3)
			advice = "deep breaths";	

		System.out.println(advice);		
	}
}

	