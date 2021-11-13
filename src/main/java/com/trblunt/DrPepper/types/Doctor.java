package com.trblunt.DrPepper.types;

import java.util.ArrayList;

public class Doctor extends Staff{
	
    private ArrayList<Patient> patients;

    public Doctor(String name) {
        super(name);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /* when the doctor removes the patient from their list of patients, 
    add this visits to the list of previous visits for this patient*/
    public void removePatient(Patient patient) {
        patients.remove(patient);
        patient.record.patientHistory.addPastVisit(patient.record.currentVisit);
        // TODO: update the past history for this patient in the database
    }

    // Adds perscription to the database
    public void perscribeMeds(Patient patient, String perscription) {   
        patient.record.patientHistory.addPerscription(perscription);
    }   

    // adds tests/notes to the database for this visit
    public void enterFindings(Patient patient, String tests, String results) { 
        patient.record.currentVisit.setDoctorNotes(tests, results);
    }
 }
