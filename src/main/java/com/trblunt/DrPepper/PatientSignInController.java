package com.trblunt.DrPepper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PatientSignInController {

	@FXML
	protected void handleSignInAction(ActionEvent event) throws IOException {
		App.setRoot("Account");
	}

	@FXML protected void handleBackAction(ActionEvent event) throws IOException {
		App.setRoot("FrontPage");
	}
}