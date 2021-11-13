package com.trblunt.DrPepper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class StaffSignInController implements Initializable{

	@FXML public ChoiceBox<String> staffType;
	public String[] staff= {"Doctor", "Nurse"};



	@FXML
	protected void handleSignInAction(ActionEvent event) throws IOException {
		if (staffType.getValue() == "Doctor") {
			App.setRoot("DoctorPickPatient");
		} else if (staffType.getValue() == "Nurse"){
			App.setRoot("NursePickPatient");
		} else {

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

}
