package application;

public class Employee {
	private String password;
	private String empInitial;
	private boolean clockedIn;
	
	public Employee(String password, String empInitial) {
		this.password = password;
		this.empInitial = empInitial;
		this.clockedIn = false;
	}
	
	String getName() {
		return this.empInitial;
	}
	
	String getPassword() {
		return this.password;
	}
	
	void clockIn() {
		this.clockedIn = true;
	}
	
	boolean clockedIn() {
		return this.clockedIn;
	}
}
