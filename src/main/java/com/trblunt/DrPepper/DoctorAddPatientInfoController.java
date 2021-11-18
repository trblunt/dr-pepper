package com.trblunt.DrPepper;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import com.trblunt.DrPepper.types.Doctor;
import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.Visit;
import com.trblunt.DrPepper.types.Vitals;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DoctorAddPatientInfoController {

    @FXML
    private TextField weightInput;
    @FXML
    private TextField heightInput;
    @FXML
    private TextField temperatureInput;
    @FXML
    private TextField bloodPressureInput;
    @FXML
    private TextArea allergyInput;
    @FXML
    private TextArea visitReasonInput;
    @FXML
    private TextArea previousHealthIssuesInput;
    @FXML
    private Text previousPrescriptionsText;
    @FXML
    private Text previousImmunizationsText;

    @FXML
    private TextField prescriptionSubmitField;
    @FXML
    private TextField testNameInput;
    @FXML
    private TextArea testResultsInput;

    @FXML
    private Text pastVisitsText;

    @FXML
    private Text patientName;
    @FXML
    private Text patientName2;
    @FXML
    private Text patientName3;
    @FXML
    private Text patientName4;
    @FXML
    private Text patientName5;

    @FXML
    private Text patientPhoneNumber;
    @FXML
    private Text patientEmail;
    @FXML
    private Text patientPharmacy;
    @FXML
    private Text patientInsurance;
    

    private Patient patient;
    private Doctor doctor;
    private Visit visit;

    @FXML
    protected void handleBackAction(ActionEvent event) throws IOException {
        this.updatePatient();
        DoctorPickPatientController controller = App.setRoot("DoctorPickPatient");
        controller.setDoctor(doctor);
    }

    @FXML
    void handleSendToPharmacyAction(ActionEvent event) {
        doctor.perscribeMeds(patient, prescriptionSubmitField.getText());
        prescriptionSubmitField.setText("");
    }

    @FXML
    void handleSubmitTestAction(ActionEvent event) {
        doctor.enterFindings(patient, testNameInput.getText(), testResultsInput.getText());
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        if (patient.record.currentVisit.vitals != null) {
            this.weightInput.setText(new DecimalFormat("#.0").format(patient.record.currentVisit.vitals.weight));
            this.heightInput.setText(String.valueOf(patient.record.currentVisit.vitals.height));
            this.temperatureInput.setText(new DecimalFormat("#.0").format(patient.record.currentVisit.vitals.temp));
            this.bloodPressureInput.setText(patient.record.currentVisit.vitals.bloodPressure);
            this.allergyInput.setText(patient.record.currentVisit.vitals.allergies);
        }
        this.patientName.setText(patient.name + ": ");
        this.patientName2.setText(patient.name + ": ");
        this.patientName3.setText(patient.name + ": ");
        this.patientName4.setText(patient.name + ": ");
        this.patientName5.setText(patient.name + ": ");
        this.visitReasonInput.setText(patient.record.currentVisit.getReasonForVisit());
        this.previousHealthIssuesInput.setText(patient.record.patientHistory.previousHealthIssues);
        this.previousPrescriptionsText.setText(String.join("\n", patient.record.patientHistory.perscriptions));
        this.previousImmunizationsText.setText(String.join("\n", patient.record.patientHistory.immunizations));
        String pastVisits = "Past Visits: \n";
        for (Visit v : patient.record.patientHistory.pastVisits) {
            pastVisits += v.asBlurb();
        }
        this.pastVisitsText.setText(pastVisits);
        this.patientEmail.setText(patient.email);
        this.patientInsurance.setText(patient.insuranceProvider);
        this.patientPharmacy.setText(patient.pharmacyAddress);
    }

    private void updatePatient() {
        Vitals vitals = patient.record.currentVisit.vitals;
        if (vitals==null) {
            //This is just for testing, should never be the case in actual use.
            vitals = new Vitals(Integer.parseInt(this.heightInput.getText()), Double.parseDouble(this.weightInput.getText()), Double.parseDouble(this.temperatureInput.getText()), this.bloodPressureInput.getText(), this.allergyInput.getText());
            patient.record.currentVisit.vitals = vitals;
        }
        vitals.weight = Double.parseDouble(this.weightInput.getText());
        vitals.height = Integer.parseInt(this.heightInput.getText());
        vitals.temp = Double.parseDouble(this.temperatureInput.getText());
        vitals.bloodPressure = this.bloodPressureInput.getText();
        String[] bpArr = vitals.bloodPressure.split("/");
        visit.bpsystolic = Integer.parseInt(bpArr[0]);
        visit.bpdiastolic = Integer.parseInt(bpArr[1]);
        vitals.allergies = this.allergyInput.getText();
        patient.record.currentVisit.reasonForVisit = this.visitReasonInput.getText();
        patient.record.patientHistory.previousHealthIssues = this.previousHealthIssuesInput.getText();
        patient.record.patientHistory.perscriptions = new ArrayList<>(Arrays.asList(this.previousPrescriptionsText.getText().split("\n")));
        patient.record.patientHistory.immunizations = new ArrayList<>(
                Arrays.asList(this.previousImmunizationsText.getText().split("\n")));
        //TODO: Update database w/ new patient info
        Server.getServer().saveDocVisit(patient, patient.record.currentVisit, doctor);
    }

    public void setVisit(Visit visit2) {
        this.visit = visit2;
    }

}
