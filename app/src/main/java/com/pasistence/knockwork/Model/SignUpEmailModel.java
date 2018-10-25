package com.pasistence.knockwork.Model;

public class SignUpEmailModel {
    private String username,email,number,password,retypepassword;

    public SignUpEmailModel() {
    }

    public SignUpEmailModel(String username, String email, String number, String password, String retypepassword) {
        this.username = username;
        this.email = email;
        this.number = number;
        this.password = password;
        this.retypepassword = retypepassword;
    }

    @Override
    public String toString() {
        return "SignUpEmailModel{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", retypepassword='" + retypepassword + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypepassword() {
        return retypepassword;
    }

    public void setRetypepassword(String retypepassword) {
        this.retypepassword = retypepassword;
    }
}
