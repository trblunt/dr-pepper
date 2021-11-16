package com.trblunt.DrPepper;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import com.trblunt.DrPepper.types.Nurse;
import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.Vitals;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class NurseAddPatientInfoController {

    private Patient patient;
    private Nurse nurse;

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
    private Text patientName;
    @FXML
    private Text patientName2;
    /*@FXML
    protected void handleBackAction(ActionEvent event) throws IOException {
        App.setRoot("NursePickPatient");
    }*/

    @FXML
    void handleSubmitAction(ActionEvent event) throws IOException {
        updatePatient();
        nurse.passPatientToDoctor(patient);
        NursePickPatientController controller = App.setRoot("NursePickPatient");
        controller.setNurse(nurse);
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
        this.visitReasonInput.setText(patient.record.currentVisit.getReasonForVisit());
        this.previousHealthIssuesInput.setText(patient.record.patientHistory.previousHealthIssues);
        this.previousPrescriptionsText.setText(String.join("\n", patient.record.patientHistory.perscriptions));
        this.previousImmunizationsText.setText(String.join("\n", patient.record.patientHistory.immunizations));
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    private void updatePatient() {
        
        double weight = Double.parseDouble(this.weightInput.getText());
        int height = Integer.parseInt(this.heightInput.getText());
        double temp = Double.parseDouble(this.temperatureInput.getText());
        String bloodPressure = this.bloodPressureInput.getText();
        String allergies = this.allergyInput.getText();
        String reasonForVisit = this.visitReasonInput.getText();

        nurse.enterVitals(patient, height, weight, temp, bloodPressure, allergies, reasonForVisit);

        patient.record.patientHistory.previousHealthIssues = this.previousHealthIssuesInput.getText();
        patient.record.patientHistory.perscriptions = new ArrayList<>(
                Arrays.asList(this.previousPrescriptionsText.getText().split("\n")));
        patient.record.patientHistory.immunizations = new ArrayList<>(
                Arrays.asList(this.previousImmunizationsText.getText().split("\n")));
        // TODO: Update database w/ new patient info
    }

}
