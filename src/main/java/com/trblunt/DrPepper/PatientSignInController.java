package com.trblunt.DrPepper;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.github.javafaker.Faker;
import com.trblunt.DrPepper.types.Address;
import com.trblunt.DrPepper.types.History;
import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.PatientRecord;
import com.trblunt.DrPepper.types.Visit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PatientSignInController {


	@FXML
	private DatePicker birthdateInput;
	@FXML
	private TextField firstNameInput;
	// @FXML
	// private TextField lastNameInput;

	@FXML
	protected void handleSignInAction(ActionEvent event) throws IOException {
		Patient patient = getPatient(firstNameInput.getText(), birthdateInput.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
		AccountController controller = App.setRoot("Account");
		controller.setPatient(patient);
	}

	@FXML protected void handleBackAction(ActionEvent event) throws IOException {
		App.setRoot("FrontPage");
	}

	protected Patient getPatient(String name, String birthdate) {
		// TODO: Fetch this data from the database

		// Faker faker = new Faker();
		// String legalName = firstName + " " + lastName;
		Patient patient = Server.getServer().patientForLogin(name, birthdate);
		return patient;
		// String email = faker.bothify("?????????????##@gmail.com");
		// com.github.javafaker.Address fakeAddress = faker.address();
		// Address address = new Address(fakeAddress.streetAddress(), fakeAddress.city(), fakeAddress.state(), Integer.parseInt(fakeAddress.zipCode()));
		// String dateOfBirth = birthdate;
		// String insuranceProvider = faker.company().name() + " Insurance";
		// int insuranceID = faker.number().numberBetween(0, 1000000000);
		// return new Patient(legalName, email, address, dateOfBirth, insuranceProvider, insuranceID);
	}
}