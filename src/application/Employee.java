package application;

public class Employee {
	private String password;
	private String empInitial;
	
	public Employee(String password, String empInitial) {
		this.password = password;
		this.empInitial = empInitial;
	}
	
	String getName() {
		return this.empInitial;
	}
}
