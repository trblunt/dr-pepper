package com.trblunt.DrPepper;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.trblunt.DrPepper.types.Address;
import com.trblunt.DrPepper.types.History;
import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.PatientRecord;
import com.trblunt.DrPepper.types.Visit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import com.github.javafaker.Faker;

public class PatientSignInController {

	@FXML
	private DatePicker birthdateInput;
	@FXML
	private TextField firstNameInput;
	@FXML
	private TextField lastNameInput;

	@FXML
	protected void handleSignInAction(ActionEvent event) throws IOException {
		Patient patient = getPatient(firstNameInput.getText(), lastNameInput.getText(), birthdateInput.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
		AccountController controller = App.setRoot("Account");
		controller.setPatient(patient);
	}

	@FXML protected void handleBackAction(ActionEvent event) throws IOException {
		App.setRoot("FrontPage");
	}

	protected Patient getPatient(String firstName, String lastName, String birthdate) {
		// TODO: Fetch this data from the database
		Patient p = new Patient(firstName, lastName, birthdate);
		p.fillWithFakeInfo();
		return p;
	}
}