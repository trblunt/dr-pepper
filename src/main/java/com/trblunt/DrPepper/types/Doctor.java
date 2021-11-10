package com.trblunt.DrPepper.types;

import java.util.ArrayList;

public class Doctor extends Staff{
	
    private ArrayList<Patient> patients;

    public Doctor(String name) {
        super(name);
    }

    public void removePatient(Patient patient) { // removes the Patient from the ArrayList

    }

    public void perscribeMeds(String perscription) {   // Adds perscription to the database
        
    }   

    public void continuePatientVisit(Patient patient) {

    }

    public void enterFindings(String tests, String results) { // adds tests/notes to the database for this visit

    }

    public void contactPatient(Patient patient, String message) { // this doen't actually do anything

    }
 }
