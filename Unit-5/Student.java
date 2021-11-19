import java.sql.Struct;

// The template for every Student object created
// DOES NOT RUN!!
public class Student {

	// static variables: associated with the class, only one copy
	static String schoolName = "Los Altos";

	// non-static, instance variables: each object has their own copy
	// (the structure, or what each Student object 'has')
	// Encapsulation: hiding the details of how the object is created
	// Needs to be private, so that only the class can access it
	// Use Getters and Setters to access the private variables
	String name;
	int studentID;

	// Constructor: a method that is called when an object is created
	public Student() {
		this.name = "Default";
		this.studentID = 0;
	}

	public Student(String name) {
		this.name = name;
		studentID = 0;
	}

	public Student(String name, int studentID) {
		this.name = name;
		this.studentID = studentID;
	}


	// non-static, instance methods: each object has their own copy
	// (the behavior, or what each Student object can 'do')
	public void sayHi() {
		System.out.println("Hi, I'm " + name);
	}

	// Note main() is **static**. It's not associated with
	// any object (b/c that would be weird...can you imagine
	// harry, hermione, ron all having different ways to run the
	// program)? So...it's actually quite conceptually confusing
	// to mix this "Runner" code in with the "template" or "specification"
	// of what each Student has/does above. It's often easier
	// conceptually to separate out main() from a class.

}
