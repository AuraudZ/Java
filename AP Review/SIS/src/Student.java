public class Student extends User  {
	// inherits name, email, password from User

	
	public Student(String name, String password, String email) {
		super(name, password, email);
	}

	// Notice how Student's message() is different than Parent's
	// Interface specifies WHAT must be done, not HOW to do it.
	public void message() {
		System.out.println("Messaging " + this.getName());
	}
	
}
