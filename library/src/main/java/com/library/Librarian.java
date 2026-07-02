package com.library;
import jakarta.persistence.*;

@Entity
@Table(name = "librarians")

public class Librarian extends Person{
	
	private int employeeNumber;
	
	//---------- CONSTRUCTORS ----------
	public Librarian(){

	}

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