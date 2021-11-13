package com.trblunt.DrPepper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DoctorAddPatientInfoController {

    @FXML
    protected void handleBackAction(ActionEvent event) throws IOException {
        App.setRoot("DoctorPickPatient");
    }

    @FXML
    void handleSendToPharmacyAction(ActionEvent event) {

    }

    @FXML
    void handleSubmitTestAction(ActionEvent event) {

    }

}
