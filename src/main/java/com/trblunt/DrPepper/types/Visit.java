package com.trblunt.DrPepper.types;

import java.time.LocalDate;

public class Visit {

    public String date;
    public String testName;
    public String testResult;
    public int bpsystolic;
    public int bpdiastolic;
    public String notes;
    public String reasonForVisit;
    public Vitals vitals;

    public Visit() {
        LocalDate now = LocalDate.now();
        this.date = now.toString();
    }
	
    // Nurse class uses this method after pressing submit on the Current Visit tab 
    public void nurseSubmitVitals(int height, double weight, double temp, String bloodPressure, String allergies, String reasonForVisit) {
        vitals = new Vitals(height, weight, temp, bloodPressure, allergies);
        this.reasonForVisit = reasonForVisit;
    }

    // use this method after pressing submit on the Test/Perscribe tab of Doctor panel
    public void setDoctorNotes(String test_name, String test_result) {
        this.testName = test_name;
        this.testResult = test_result;
    }

    // this is to be displayed on past visits tab in Patient and Doctor portal
    public String getDate() {
        return date;
    }

    // this is no be displayed on past visits tab in Patient and Doctor portal
    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public String asBlurb() {
        return date + "\n" + this.reasonForVisit + "\n\n";
    }
}
