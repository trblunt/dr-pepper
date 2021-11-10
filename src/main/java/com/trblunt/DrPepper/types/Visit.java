package com.trblunt.DrPepper.types;

import java.time.LocalDate;

public class Visit {

    private String date;
    private String notes;
    private String reasonForVisit;
    private Vitals vitals;

    public Visit() {
        LocalDate now = LocalDate.now();
        this.date = now.toString();
    }
	
    public void nurseSubmitVitals(int height, int weight, int temp, String bloodPressure, String allergies, String reasonForVisit) {
        vitals = new Vitals(height, weight, temp, bloodPressure, allergies);
        this.reasonForVisit = reasonForVisit;
    }
}
