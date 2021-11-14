package com.trblunt.DrPepper;

import java.io.IOException;

import com.trblunt.DrPepper.types.Patient;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class DoctorPickPatientController {

	@FXML
	private Accordion accordion;

	@FXML
	public void initialize() {
		ObservableList<TitledPane> panes = accordion.getPanes();
		Patient testPatient = new Patient("");
		for (int i=0; i<4; i++) {
			PatientPane pane = new PatientPane(testPatient);
			pane.isActive.addListener((isActive, oldVal, newVal) -> {
				if (!newVal) {
					panes.remove(pane);
				}
			});
			panes.add(pane);
		}
	}

	/* @FXML
	protected void handleSelectPatientAction(ActionEvent event) throws IOException {
		App.setRoot("DoctorAddPatientInfo");
	} */

}