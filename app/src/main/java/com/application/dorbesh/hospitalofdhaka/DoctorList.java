package com.application.dorbesh.hospitalofdhaka;

/**
 * Created by DORBESH on 1/27/2017.
 */

public class DoctorList {

    String name,expertise,number;

    public DoctorList(String name, String expertise, String number) {
        this.name = name;
        this.expertise = expertise;
        this.number = number;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
