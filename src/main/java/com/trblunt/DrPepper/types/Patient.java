package com.trblunt.DrPepper.types;

public class Patient extends User {

    private String insuranceProvider;
    private int insuranceID;

    public Patient(String name) {
        super(name);
    }

    public void setPatientInfo(String email, Address address, int dateOfBirth, int insuranceID) {

    }

    public void modifyAccountInfo() {   // this will need to edit/update the database

    }

    public void messageDoctor(String message, Doctor doctor) {

    }

    public void register(Patient newPatient) {  // do we need this?

    }

    public void login(String firstName, String lastName, int dateOfBirth) {

    }

    public void viewPastVisits() {

    }
	
}
