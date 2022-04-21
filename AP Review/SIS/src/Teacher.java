public class Teacher extends User implements Messageable {
	// inherits name, email, password from User

	
	public Teacher(String name, String password, String email) {
		super(name, password, email);
	}


	public void message() {
		System.out.println("Messaging teacher.");
	}
	
}

	
