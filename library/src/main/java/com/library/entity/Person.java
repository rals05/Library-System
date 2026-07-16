package com.library;
import jakarta.persistence.*;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	protected String name;
	protected String email;
	protected String phoneNumber;
	protected String password;
	
	//---------- CONSTRUCTORS ----------
	public Person(){

	}

	public Person(String name, String email, String phoneNumber, String password) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

    //---------- METHOD ----------
	protected String generateNumber(String prefix) {
		StringBuilder sb = new StringBuilder(prefix);

		for(int i = 0; i < 9; i++) {
			sb.append((int)(Math.random() * 10));
		}

		return sb.toString();
	}
	
	//---------- GETTERS & SETTERS ----------
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}//end class Person
