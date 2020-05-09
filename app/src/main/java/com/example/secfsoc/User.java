package com.example.secfsoc;

public class User {
    String FirstName,LastName,email,mobnumber,password,society,str_wing,house;
    public User() {

    }

    public User(String firstName, String lastName, String email, String mobnumber, String password, String society, String str_wing, String house) {
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
        this.mobnumber = mobnumber;
        this.password = password;
        this.society = society;
        this.str_wing = str_wing;
        this.house = house;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobnumber() {
        return mobnumber;
    }

    public void setMobnumber(String mobnumber) {
        this.mobnumber = mobnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getStr_wing() {
        return str_wing;
    }

    public void setStr_wing(String str_wing) {
        this.str_wing = str_wing;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
