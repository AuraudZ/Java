
import java.util.*;

public class SISSystem {

	private List<Gradebook> allGradebooks;
	
	public static void main(String[] args) {
		SISSystem testSystem = new SISSystem();
		testSystem.allGradebooks = new ArrayList<Gradebook>();
		Gradebook testStudentGradebook = new Gradebook();
		testSystem.allGradebooks.add(testStudentGradebook);
		
		System.out.println("***Testing individual Student's data***");
		Student s1 = new Student("Beth", "password", "beth@mvla.net");
		Student s2 = new Student("Carl", "lol", "carl@mvla.net");
		System.out.println("s1: " + s1.getName() + ", s2: " + s2.getName());
		// (1) 
		s2 = s1;
		//System.out.println("s1: " + s1.getName() + ", s2: " + s2.getName());

		s2 = null;
		//System.out.println("s1: " + s1 + ", s2: " + s2); // avoid NullPointerException


		// (2) Refactoring types that go in Gradebook
		System.out.println("\n***Printing all kinds of assignments for student***");
		testSystem.allGradebooks.get(0).printOutAssignments(); 	// In Gradebook.java. Horribly hardcoded!  

				
		System.out.println("\n***Messaging all different users in system***");
		List<Student> sendList = new ArrayList<Student>();
		// (3) How can I modify to be able to send messages to ALL types of people I want, not just Students?
		//sendList.add(new Parent("Dan's parent", "pswd", "dans.dad@aol.com")); 
		sendList.add(new Student("Dan", "progr@mming", "dan@mvla.net"));
		sendList.add(new Student("Danica", "12345", "danica@mvla.net"));
		for(Student entity : sendList) {
			entity.message();
		}
	}
}







