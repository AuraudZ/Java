
public class Parent extends User implements Messageable {
	// inherits name, email, password from User

	
	public Parent(String name, String password, String email) {
		super(name, password, email);
	}

	// To fulfill contract of Messageable interface
	public void message() {
		System.out.println("Messaging parent.");
	}

}
