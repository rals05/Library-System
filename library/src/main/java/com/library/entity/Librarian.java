package com.library;
import jakarta.persistence.*;

@Entity
@Table(name = "librarians")

public class Librarian extends Person {
	
	private String employeeNumber;
	
	//---------- CONSTRUCTORS ----------
	public Librarian(){

	}

	public Librarian(String name, String email, String phoneNumber, String password, String employeeNumber) {
		super(name, email, phoneNumber, password);
		this.employeeNumber = generateNumber("9");
	}
	
	//---------- GETTERS & SETTERS ----------
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

}//end class Librarian