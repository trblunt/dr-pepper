package com.trblunt.DrPepper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NurseAddPatientInfoController {

    @FXML
    protected void handleBackAction(ActionEvent event) throws IOException {
        App.setRoot("NursePickPatient");
    }

    @FXML
    void handleSubmitAction(ActionEvent event) throws IOException {

    }

}
