package com.library.dto;

public class LoginRequest {

    private String number;
    private String password;

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}//end class LoginRequest