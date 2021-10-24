package com.trblunt.DrPepper;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DoctorPickPatientController {

	@FXML
	protected void handleSelectPatientAction(ActionEvent event) throws IOException {
		App.setRoot("DoctorAddPatientInfo");
	}

}