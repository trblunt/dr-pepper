package com.trblunt.DrPepper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FrontPageController {


	@FXML protected void handlePatientLoginAction(ActionEvent event) throws IOException {
		App.setRoot("PatientSignIn");
	}

	@FXML protected void handleStaffLoginAction(ActionEvent event) throws IOException {
		App.setRoot("StaffSignIn");
	}

	@FXML protected void handleRegisterAction(ActionEvent event) throws IOException {
		App.setRoot("Register");
	}
}