package com.trblunt.DrPepper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.javafaker.Faker;
import com.trblunt.DrPepper.types.Doctor;
import com.trblunt.DrPepper.types.Nurse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class StaffSignInController implements Initializable{

	@FXML public ChoiceBox<String> staffType;
	@FXML private TextField passwordInput;
	@FXML private TextField usernameInput;
	public String[] staff= {"Doctor", "Nurse"};

	@FXML
	protected void handleSignInAction(ActionEvent event) throws IOException {
		if (isValidLogin(usernameInput.getText(), passwordInput.getText(), staffType.getValue() == "Doctor")) {
			if (staffType.getValue() == "Doctor") {
				DoctorPickPatientController controller = App.setRoot("DoctorPickPatient");
				controller.setDoctor(getDoctor(usernameInput.getText(), passwordInput.getText()));
			} else if (staffType.getValue() == "Nurse") {
				NursePickPatientController controller = App.setRoot("NursePickPatient");
				controller.setNurse(getNurse(usernameInput.getText(), passwordInput.getText()));
			} else {

			}
		}
	}

	@FXML protected void handleBackAction(ActionEvent event) throws IOException {
		App.setRoot("FrontPage");
	}

	private boolean isDoctor() {
		//TODO: Implement check
		return true;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		staffType.getItems().addAll(staff);
	}

	public boolean isValidLogin(String username, String password, boolean isDoctor) {
		//TODO: Implement auth w/ database (maybe)
		return true;
	}

	public Doctor getDoctor(String username, String password) {
		//TODO: Return doctor from database
		// return new Doctor(new Faker().name().fullName());
		return Server.getServer().doctororLogin(username, password);
	}

	public Nurse getNurse(String username, String password) {
		// TODO: Return doctor from database
		return Server.getServer().nurseLogin(username, password);
	}

}