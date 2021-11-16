package com.trblunt.DrPepper;

import java.io.IOException;

import com.trblunt.DrPepper.types.Doctor;
import com.trblunt.DrPepper.types.Patient;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;

import com.github.javafaker.Faker;

public class PatientPane extends TitledPane {

	public SimpleBooleanProperty isActive;

	private Patient patient;

	private Doctor doctor;

	public PatientPane(Patient patient, Doctor doctor) {
        FXMLLoader fxmlLoader = App.loadComponent("PatientPane");
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

		this.patient = patient;
		this.doctor = doctor;

		this.textProperty().set(this.getPatientName());

		this.isActive = new SimpleBooleanProperty(true);

    }

	@FXML
	protected void handleSelectPatientAction(ActionEvent event) throws IOException {
		DoctorAddPatientInfoController controller = App.setRoot("DoctorAddPatientInfo");
		controller.setPatient(patient);
		controller.setDoctor(doctor);
	}

	@FXML
	protected void handleRemovePatientAction(ActionEvent event) throws IOException {
		this.isActive.set(false);
	}

	private String getPatientName() {
		// TODO: Use actual patient data
		return patient.firstName + " " + patient.lastName;
	}

}