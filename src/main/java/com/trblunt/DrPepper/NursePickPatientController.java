package com.trblunt.DrPepper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class NursePickPatientController {

    @FXML
    void handleNewPatientAction(ActionEvent event) throws IOException {
        App.setRoot("Register");
    }

    @FXML
    void handleCheckInAction(ActionEvent event) throws IOException {
        App.setRoot("NurseAddPatientInfo");
    }

    @FXML
    protected void handleLogoutAction(ActionEvent event) throws IOException {
        App.setRoot("FrontPage");
    }

}
