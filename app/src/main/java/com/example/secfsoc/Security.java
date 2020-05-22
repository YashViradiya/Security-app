package com.example.secfsoc;


public class Security {
    String first,last,email,password,phone,society;
    public Security() {

    }
    public Security(String first, String last, String email, String phone,String password, String society) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.society = society;

    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }
}
