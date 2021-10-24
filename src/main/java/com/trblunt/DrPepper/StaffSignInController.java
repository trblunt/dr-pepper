package com.trblunt.DrPepper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StaffSignInController {

	@FXML
	protected void handleSignInAction(ActionEvent event) throws IOException {
		if (isDoctor()) {
			App.setRoot("DoctorPickPatient");
		} else {
			App.setRoot("NurseAddPatientInfo");
		}
	}

	private boolean isDoctor() {
		//TODO: Implement check
		return true;
	}

}