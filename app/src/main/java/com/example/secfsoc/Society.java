package com.example.secfsoc;


public class Society {
    String scode,sname,sarea,sadd,sowner;
    public Society() {

    }

    public Society(String code,String name, String area, String add, String owner) {
        scode = code;
        sname = name;
        sarea = area;
        sadd = add;
        sowner = owner;
        }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getSname() {
        return sname;
    }

    public String getSarea() {
        return sarea;
    }

    public String getSadd() {
        return sadd;
    }

    public String getSowner() {
        return sowner;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSarea(String sarea) {
        this.sarea = sarea;
    }

    public void setSadd(String sadd) {
        this.sadd = sadd;
    }

    public void setSowner(String sowner) {
        this.sowner = sowner;
    }
}
