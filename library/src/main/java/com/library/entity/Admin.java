package com.library;
import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends Person {

    private String adminNumber;

    //---------- CONSTRUCTORS ----------
    public Admin() {

    }

    public Admin(String name, String email, String phoneNumber, String password) {
        super(name, email, phoneNumber, password);
        this.adminNumber = generateNumber("8");
    }

    //---------- GETTERS & SETTERS ----------
    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

}//end class Admin