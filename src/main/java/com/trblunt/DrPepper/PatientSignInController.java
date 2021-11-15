package com.trblunt.DrPepper;

import java.io.IOException;

import com.trblunt.DrPepper.types.Address;
import com.trblunt.DrPepper.types.Patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import com.github.javafaker.Faker;

public class PatientSignInController {

	@FXML
	private TextField birthdateInput;
	@FXML
	private TextField firstNameInput;
	@FXML
	private TextField lastNameInput;

	@FXML
	protected void handleSignInAction(ActionEvent event) throws IOException {
		Patient patient = getPatient(firstNameInput.getText(), lastNameInput.getText(), birthdateInput.getText());
		AccountController controller = App.setRoot("Account");
		controller.setPatient(patient);
	}

	@FXML protected void handleBackAction(ActionEvent event) throws IOException {
		App.setRoot("FrontPage");
	}

	protected Patient getPatient(String firstName, String lastName, String birthdate) {
		// TODO: Fetch this data from the database
		Faker faker = new Faker();
		String legalName = firstName + " " + lastName;
		String email = faker.bothify("?????????????##@gmail.com");
		com.github.javafaker.Address fakeAddress = faker.address();
		Address address = new Address(fakeAddress.streetAddress(), fakeAddress.city(), fakeAddress.state(), Integer.parseInt(fakeAddress.zipCode()));
		String dateOfBirth = birthdate;
		String insuranceProvider = faker.company().name() + " Insurance";
		int insuranceID = faker.number().numberBetween(0, 1000000000);
		return new Patient(legalName, email, address, dateOfBirth, insuranceProvider, insuranceID);
	}
}