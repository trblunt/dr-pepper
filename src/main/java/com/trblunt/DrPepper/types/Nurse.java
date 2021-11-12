package com.trblunt.DrPepper.types;

import java.util.ArrayList;

public class Nurse extends Staff {
    private ArrayList<Doctor> doctors;  /*this will be prepopulated in the database
    and will be shown in the dropdown where the nurse picks what doctor the patient will
    be assigned to when they click check in*/
	
    public Nurse(String name) {
        super(name);
    }

    // this happens when you click check in
    public void startPatientVisit(Patient patient, Doctor doctor) { 
        patient.assignedDoctor = doctor;
        patient.record.currentVisit = new Visit();
    }

    // when you click submit update the vitals for the current visit and add the patient to the doctors list of patients
    public void enterVitals(Patient patient, int height, int weight, int temp, String bloodPressure, String allergies,  String reasonForVisit) {
        patient.record.currentVisit.nurseSubmitVitals(height, weight, temp, bloodPressure, allergies, reasonForVisit);
        passPatientToDoctor(patient);
    }

    public History viewPatientHistory(Patient patient) {
        return patient.record.patientHistory;
    }

    // do we need this method?
    public void updatePatientHistory(Patient patient, String prevHealthIssues) { 
        patient.record.patientHistory.updatePrevHealthIssues(prevHealthIssues);
    }

    public void passPatientToDoctor(Patient patient) {
        patient.assignedDoctor.addPatient(patient);
    }
}
