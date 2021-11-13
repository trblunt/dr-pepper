package com.trblunt.DrPepper.types;

import java.util.ArrayList;

public class Patient extends User {

    public PatientRecord record;
    public String firstName;
    public String lastName;
    private String insuranceProvider;
    private int insuranceID;
    private String pharmacyAddress;
    public Doctor assignedDoctor;

    public Patient(String name) {
        // might add a boolean here that only gets the information from the database if the Patient isn't new
        super(name);
        firstName = name.substring(0, name.indexOf(" "));
        lastName = name.substring(name.indexOf("")+1);
        // TODO: get all the information from the database and set each of the variables
        email = "";
        address = "";
        dateOfBirth = "";
        insuranceProvider = "";
        insuranceID = 0;
        pharmacyAddress = "";
        History history = new History();
        //record = new PatientRecord(patient history from database);
    }

    // TODO this will need to add the patient to the database
    public void registerPatient(String email, Address address, String dateOfBirth, String insuranceProvider, int insuranceID) {
        firstName = name.substring(0, name.indexOf(" "));
        lastName = name.substring(name.indexOf("")+1);
        this.email = email;
        this.address = address.toString();
        this.dateOfBirth = dateOfBirth;
        this.insuranceProvider = insuranceProvider;
        this.insuranceID = insuranceID;
    }

    // TODO this will need to update some fields of the database
    public void modifyAccountInfo(String Name, String email, String dateOfBirth, String address, String insuranceProvider, int insuranceID, String pharmacyAddress) {
        
    }

    //doesn't actually do anything
    public void messageDoctor(String message, Doctor doctor) {

    }

    public ArrayList<Visit> viewPastVisits() {
        return record.patientHistory.pastVisits;
    }


	
}
