package com.library;
import jakarta.persistence.*;

@Entity
@Table(name = "librarians")

public class Librarian extends Person{
	
	private int employeeNumber;
	private String username;
	private String password;
	
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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password; 
	}

}//end class Librarian