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

    public int getAnAge() {
        return anAge;
    }

    public Student(String aName, String aSurname, int aPhoneNumber, double aGrade, int anAge) {
        this.aName = aName;

        this.aSurname = aSurname;
        this.aPhoneNumber = aPhoneNumber;
        this.aGrade = aGrade;
        this.anAge = anAge;
    }

    @Override
    public String toString() {
        return "Name:" +aName + " Surname:" + aSurname + " Phone:" + aPhoneNumber + " Grade" + aGrade + " Age" + anAge;
    }
}
