package com.application.dorbesh.hospitalofdhaka;


public class Ambulence {

    String ambulenceName;
    String ambulenceNumber;

    public Ambulence(String ambulenceName, String ambulenceNumber) {
        this.ambulenceName = ambulenceName;
        this.ambulenceNumber = ambulenceNumber;
    }

    public String getAmbulenceName() {
        return ambulenceName;
    }

    public void setAmbulenceName(String ambulenceName) {
        this.ambulenceName = ambulenceName;
    }

    public String getAmbulenceNumber() {
        return ambulenceNumber;
    }

    public void setAmbulenceNumber(String ambulenceNumber) {
        this.ambulenceNumber = ambulenceNumber;
    }
}
