package com.trblunt.DrPepper;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.trblunt.DrPepper.types.Address;
import com.trblunt.DrPepper.types.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField legalNameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField insuranceProviderInput;
    @FXML
    private TextField insuranceIdInput;
    @FXML
    private TextField streetAddressInput;
    @FXML
    private TextField stateInput;
    @FXML
    private TextField pharmacyInput;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField postalCodeInput;
    @FXML
    private DatePicker datePicker;


    @FXML protected void handleRegisterAction(ActionEvent event) throws IOException {
        Address address = new Address(streetAddressInput.getText(), cityInput.getText(), stateInput.getText(), Integer.parseInt(postalCodeInput.getText()));
        Patient newPatient = new Patient(legalNameInput.getText(), emailInput.getText(), address.toString(), datePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE), insuranceProviderInput.getText(), Integer.parseInt(insuranceIdInput.getText()));
        newPatient.pharmacyAddress = pharmacyInput.getText();
        AccountController controller = App.setRoot("Account");
        controller.setPatient(newPatient);
    }

    @FXML protected void handleBackAction(ActionEvent event) throws IOException {
        App.setRoot("FrontPage");
    }
}