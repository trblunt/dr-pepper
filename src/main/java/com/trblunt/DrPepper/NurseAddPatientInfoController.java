package com.trblunt.DrPepper;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import com.trblunt.DrPepper.types.Doctor;
import com.trblunt.DrPepper.types.Nurse;
import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.Visit;
import com.trblunt.DrPepper.types.Vitals;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class NurseAddPatientInfoController {

    private Patient patient;
    private Nurse nurse;
    private Doctor doctor;

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
        // updatePatient();
        // nurse.passPatientToDoctor(patient);
        addVisit();
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
        String pastVisitInfo = "";
        for (Visit visit: patient.record.patientHistory.pastVisits){
            pastVisitInfo = pastVisitInfo + visit.reasonForVisit + "\n";
        }
        System.out.println(pastVisitInfo);
        this.previousHealthIssuesInput.setText(pastVisitInfo);
        this.previousPrescriptionsText.setText(String.join("\n", patient.record.patientHistory.perscriptions));
        this.previousImmunizationsText.setText(String.join("\n", patient.record.patientHistory.immunizations));
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    private void addVisit() {
        Visit currVisit = new Visit();
        Vitals currVitals = new Vitals(Integer.parseInt(this.heightInput.getText()), Double.parseDouble(this.weightInput.getText()), Double.parseDouble(this.temperatureInput.getText()), this.bloodPressureInput.getText(), this.allergyInput.getText());
        currVisit.vitals = currVitals;
        String[] bpArr = currVitals.bloodPressure.split("/");
        currVisit.bpsystolic = Integer.parseInt(bpArr[0]);
        currVisit.bpdiastolic = Integer.parseInt(bpArr[1]);
        currVisit.reasonForVisit = this.visitReasonInput.getText();
        Server.getServer().addNurseVisit(this.patient, currVisit, this.doctor);
        // nurse.enterVitals(patient, height, weight, temp, bloodPressure, allergies, reasonForVisit);

        // patient.record.patientHistory.previousHealthIssues = this.previousHealthIssuesInput.getText();
        // patient.record.patientHistory.perscriptions = new ArrayList<>(
        //         Arrays.asList(this.previousPrescriptionsText.getText().split("\n")));
        // patient.record.patientHistory.immunizations = new ArrayList<>(
        //         Arrays.asList(this.previousImmunizationsText.getText().split("\n")));
        // TODO: Update database w/ new patient info
    }

}
