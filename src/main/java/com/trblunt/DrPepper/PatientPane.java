package com.trblunt.DrPepper;

import java.io.IOException;

import com.trblunt.DrPepper.types.Doctor;
import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.Visit;

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

	private Visit visit;

	public PatientPane(Visit v, Doctor doctor) {
		this.visit = v;
        FXMLLoader fxmlLoader = App.loadComponent("PatientPane");
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

		this.patient = v.patient;
		this.doctor = doctor;

		this.textProperty().set(this.getPatientName());

		this.isActive = new SimpleBooleanProperty(true);

    }

	@FXML
	protected void handleSelectPatientAction(ActionEvent event) throws IOException {
		DoctorAddPatientInfoController controller = App.setRoot("DoctorAddPatientInfo");
		patient.record.currentVisit = visit;
		controller.setPatient(patient);
		controller.setDoctor(doctor);
		controller.setVisit(visit);
	}

	@FXML
	protected void handleRemovePatientAction(ActionEvent event) throws IOException {
		this.isActive.set(false);
	}

	private String getPatientName() {
		// TODO: Use actual patient data
		return patient.name;
	}

}