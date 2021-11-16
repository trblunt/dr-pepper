 package com.trblunt.DrPepper;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.Visit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AccountController implements Initializable {

    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private DatePicker dob;
    @FXML private TextField residence;
    @FXML private TextField insurance;
    @FXML private TextField insuranceID;
    @FXML private TextField pharmacy;

    @FXML private Text pastVisitsText;

    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label dobLabel;
    @FXML private Label residenceLabel;
    @FXML private Label insuranceLabel;
    @FXML private Label insuranceIDLabel;
    @FXML private Label pharmacyLabel;

    @FXML private Button update;

    @FXML protected void handleLogoutAction(ActionEvent event) throws IOException {
        App.setRoot("FrontPage");
    }

    private Patient patient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        update.setOnAction(this::updatePatient);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        updateLabels();
    }

    private void updateLabels() {
        nameLabel.setText(patient.name);
        emailLabel.setText(patient.email);
        dobLabel.setText(patient.dateOfBirth);
        residenceLabel.setText(patient.address);
        insuranceLabel.setText(patient.insuranceProvider);
        insuranceIDLabel.setText(String.valueOf(patient.insuranceID));
        pharmacyLabel.setText(patient.pharmacyAddress);

        name.setText(patient.name);
        email.setText(patient.email);
        dob.setValue(LocalDate.parse(patient.dateOfBirth, DateTimeFormatter.ISO_LOCAL_DATE));
        residence.setText(patient.address);
        insurance.setText(patient.insuranceProvider);
        insuranceID.setText(String.valueOf(patient.insuranceID));
        pharmacy.setText(patient.pharmacyAddress);

        String pastVisits = "Past Visits: \n";
        for (Visit v : patient.record.patientHistory.pastVisits) {
            pastVisits += v.asBlurb();
        }
        pastVisitsText.setText(pastVisits);
    }

    @FXML
    public void updatePatient(ActionEvent event) {
        patient.modifyAccountInfo(
                name.getText(), email.getText(), dob.getValue().format(
                        DateTimeFormatter.ISO_LOCAL_DATE), 
                residence.getText(), insurance.getText(), Integer.parseInt(insuranceID.getText()), pharmacy.getText());
        Server.getServer().updatePatient(patient);
        updateLabels();
        
    }
}