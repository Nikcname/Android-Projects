package com.example.nikcname.recycleinformation;

import java.io.Serializable;

public class Student implements Serializable {

    private String aName;
    private String aSurname;
    private int aPhoneNumber;
    private double aGrade;
    private int anAge;

    public String getaName() {
        return aName;
    }

    public String getaSurname() {
        return aSurname;
    }

    public int getaPhoneNumber() {
        return aPhoneNumber;
    }

    public double getaGrade() {
        return aGrade;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public void setaSurname(String aSurname) {
        this.aSurname = aSurname;
    }

    public int getAnAge() {
        return anAge;
    }

}
