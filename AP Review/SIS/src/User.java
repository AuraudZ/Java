// ***This never runs!! Just a TEMPLATE or SPECIFICATION
// for User objects that will be created.
public class User {
	// Instance vars: What attributes each User object HAS
	private String name;
	private String password;
	private String email;
	
	// Called with 'new' operator.  
	// No default User() constructor is supplied now that we have this one
	public User(String name, String password, String email) {
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	// Instance methods: What behaviors each User object DOES
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
