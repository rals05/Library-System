package com.library;
import jakarta.persistence.*;

@Entity
@Table(name = "members")

public class Member extends Person {
	
	private String membershipNumber;
	
	//---------- CONSTRUCTORS ----------
	public Member(){

	}

	public Member(String name, String email, String phoneNumber, String password) {
		super(name, email, phoneNumber, password);
		this.membershipNumber = generateNumber("1");
	}
	
	//---------- GETTERS & SETTERS ----------
	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
    this.membershipNumber = membershipNumber;
    }

}//end class Member
