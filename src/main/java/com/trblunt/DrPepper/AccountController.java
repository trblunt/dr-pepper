 package com.trblunt.DrPepper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.trblunt.DrPepper.types.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AccountController implements Initializable {

    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private TextField dob;
    @FXML private TextField residence;
    @FXML private TextField insurance;
    @FXML private TextField insuranceID;
    @FXML private TextField pharmacy;

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
        update.setOnAction(this::getValues);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        nameLabel.setText(patient.firstName + " " + patient.lastName);
        emailLabel.setText(patient.email);
        dobLabel.setText(patient.dateOfBirth);
        residenceLabel.setText(patient.address);
        insuranceLabel.setText(patient.insuranceProvider);
        insuranceIDLabel.setText(String.valueOf(patient.insuranceID));
        pharmacyLabel.setText(patient.pharmacyAddress);

    }

    @FXML
    public void getValues(ActionEvent event) {
        if (name.getText().isEmpty() == false) { nameLabel.setText(name.getText()); }
        if (email.getText().isEmpty() == false) { emailLabel.setText(email.getText()); }
        if (dob.getText().isEmpty() == false) { dobLabel.setText(dob.getText()); }
        if (residence.getText().isEmpty() == false) { residenceLabel.setText(residence.getText()); }
        if (insurance.getText().isEmpty() == false) { insuranceLabel.setText(insurance.getText()); }
        if (insuranceID.getText().isEmpty() == false) { insuranceIDLabel.setText(insuranceID.getText()); }
        if (pharmacy.getText().isEmpty() == false) { pharmacyLabel.setText(pharmacy.getText()); }
    }
}