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

	private Doctor doctor;

	@FXML
	private Accordion accordion;

	

	// @FXML
	// public void initialize() {
	// 	System.out.println("DPPC INIT");
	// 	System.out.println(doctor.userID);
	// 	System.out.println("----");
	// 	ObservableList<TitledPane> panes = accordion.getPanes();
	// 	ArrayList<Patient> patients = getPatients();
	// 	for (Patient p: patients) {
	// 		PatientPane pane = new PatientPane(p, doctor);
	// 		pane.isActive.addListener((isActive, oldVal, newVal) -> {
	// 			if (!newVal) {
	// 				panes.remove(pane);
	// 			}
	// 		});
	// 		panes.add(pane);
	// 	}
	// }
	
	public void loadPatients() {
		System.out.println("DPPC INIT");
		System.out.println(doctor.userID);
		System.out.println("----");
		ObservableList<TitledPane> panes = accordion.getPanes();
		ArrayList<Visit> visits = getVisits();
		for (Visit v: visits) {
			PatientPane pane = new PatientPane(v, doctor);
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

	private ArrayList<Visit> getVisits() {
		return Server.getServer().getActiveVisitsForDoctor(doctor);
		//TODO: Get from database
		// return new Patient[] {
		// 	Server.getServer().patientForLogin("Henry OM", "2000-10-26")
		// };
		// Patient testPatient = new Patient("John", "Doe", "2000-01-01");
		// testPatient.fillWithFakeInfo();
		// return new Patient[] {testPatient};
		// return new Patient[] {};
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	/* @FXML
	protected void handleSelectPatientAction(ActionEvent event) throws IOException {
		App.setRoot("DoctorAddPatientInfo");
	} */

}