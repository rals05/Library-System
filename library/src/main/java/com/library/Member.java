package com.library;
import jakarta.persistence.*;

@Entity
@Table(name = "members")

public class Member extends Person {
	
	private String membershipNumber;
	
	//---------- CONSTRUCTORS ----------
	public Member(){

	}

	public Member(String name, String email, String phoneNumber) {
		super(name, email, phoneNumber);
		this.membershipNumber = generateMembershipNumber();
	}
	
	public String generateMembershipNumber() {
		StringBuilder sb = new StringBuilder("1");
		int numberOfDigits = 9;
		for(int i = 0; i < numberOfDigits; i++) {
			sb.append((int)(Math.random() * 10));
		}
		return sb.toString();
	}
	
	//---------- GETTERS & SETTERS ----------
	public String getMembershipNumber() {
		return membershipNumber;
	}

	public void setMembershipNumber(String membershipNumber) {
    this.membershipNumber = membershipNumber;
    }

}//end class Member
