package com.trblunt.DrPepper.types;

public class PatientRecord {

    private Patient patient; // don't think this actually needs to be here
    public Visit currentVisit;
    // private Vitals vitals;
    public History patientHistory;

    public PatientRecord(History history) {
        // populate patientHistory from the database
        patientHistory = history;
    }
}
