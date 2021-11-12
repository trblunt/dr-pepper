package com.trblunt.DrPepper.types;

import java.time.LocalDate;

public class Visit {

    private String date;
    private String tests;
    private String notes;
    private String reasonForVisit;
    public Vitals vitals;

    public Visit() {
        LocalDate now = LocalDate.now();
        this.date = now.toString();
    }
	
    // Nurse class uses this method after pressing submit on the Current Visit tab 
    public void nurseSubmitVitals(int height, int weight, int temp, String bloodPressure, String allergies, String reasonForVisit) {
        vitals = new Vitals(height, weight, temp, bloodPressure, allergies);
        this.reasonForVisit = reasonForVisit;
    }

    // use this method after pressing submit on the Test/Perscribe tab of Doctor panel
    public void setDoctorNotes(String tests, String notes) {
        this.tests = tests;
        this.notes = notes;
    }

    // this is no be displayed on past visits tab in Patient and Doctor portal
    public String getDate() {
        return date;
    }

    // this is no be displayed on past visits tab in Patient and Doctor portal
    public String getReasonForVisit() {
        return reasonForVisit;
    }
}
