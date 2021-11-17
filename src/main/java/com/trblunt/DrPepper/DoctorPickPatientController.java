package com.trblunt.DrPepper;

import java.io.IOException;
import java.util.ArrayList;

import com.trblunt.DrPepper.types.Doctor;
import com.trblunt.DrPepper.types.History;
import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.PatientRecord;
import com.trblunt.DrPepper.types.Visit;
import com.trblunt.DrPepper.types.Vitals;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class DoctorPickPatientController {

	@FXML
	private Accordion accordion;

	private Doctor doctor;

	@FXML
	public void initialize() {
		ObservableList<TitledPane> panes = accordion.getPanes();
		Patient[] patients = getPatients();
		for (int i=0; i<patients.length; i++) {
			PatientPane pane = new PatientPane(patients[i], doctor);
			pane.isActive.addListener((isActive, oldVal, newVal) -> {
				if (!newVal) {
					panes.remove(pane);
				}
			});
			panes.add(pane);
		}
	}

	@FXML protected void handleLogoutAction(ActionEvent event) throws IOException {
		App.setRoot("FrontPage");
	}

	private Patient[] getPatients() {
		//TODO: Get from database
		/*return new Patient[] {
			new Patient("John", "Doe", "01/01/2000"),
			new Patient("Jane", "Smith", "02/10/2014"),
			new Patient("Frank", "Zachary", "11/30/1987")
		};*/
		// Patient testPatient = new Patient("John", "Doe", "2000-01-01");
		// testPatient.fillWithFakeInfo();
		// return new Patient[] {testPatient};
		return new Patient[] {};
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/* @FXML
	protected void handleSelectPatientAction(ActionEvent event) throws IOException {
		App.setRoot("DoctorAddPatientInfo");
	} */

}