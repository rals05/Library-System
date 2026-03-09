package Library;

public class Librarian extends Person{
	
	private int employeeNumber;
	
	//---------- CONSTRUCTOR ----------
	public Librarian(String name, String email, String phoneNumber, int employeeNumber) {
		super(name, email, phoneNumber);
		this.employeeNumber = employeeNumber;
	}
	
	//---------- GETTERS & SETTERS ----------
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

}//end class Librarian
