package com.trblunt.DrPepper;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PatientSignInController {


	@FXML
	protected void handleSignInAction(ActionEvent event) throws IOException, ParseException {
		Server server = Server.getServer();
		
		if (server.validPatientLogin("Henry OM", "2000-10-26")) {
			App.setRoot("Account");
		} else {
			App.setRoot("FrontPage");
		}
		
	}

	@FXML protected void handleBackAction(ActionEvent event) throws IOException {
		App.setRoot("FrontPage");
	}
}