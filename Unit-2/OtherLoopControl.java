
public class OtherLoopControl {
	// Section 3.3
	
	public static void main(String[] args) {
		System.out.println("Starting while demo: "); 
		whileDemo();
		System.out.print("Starting do-while demo: "); 
		doWhileDemo();
		System.out.print("Starting break demo: "); 
		breakDemo(); 
		System.out.print("Starting continue demo: "); 
		continueDemo();
	}
	
	// while tests condition first and repeats if true
	public static void whileDemo() {
		int x = 0;
		while(x > 0) {
			System.out.println("Inside while loop: will I print?");
		}  
	}
	
	// do-while runs AT LEAST ONCE, then tests condition and repeats if true
	public static void doWhileDemo() {
		int x = 0;
		do {
			System.out.println("Inside do-while loop: will I print?");
		} while(x > 0); // *** <-- semicolon
	}
	
	// break breaks out of the loop 
	// can be combined with while(true) { }
	public static void breakDemo() {
		int x = 0;
		while(true) { // Seems like guaranteed infinite loop...
			System.out.print(x);
			if(x == 42) {
				break;
			}
			x++;
		}
		System.out.println("Broke out of loop!");
	}
	
	// continue jumps back up to the top of the loop, and "continues on"
	public static void continueDemo() {
		int x = 0;
		while(x < 10) {
			if(x == 3) {
				x++;
				continue;
			}
			System.out.print(x);
			x++;
		}
	}

}
