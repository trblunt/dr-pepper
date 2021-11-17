package com.trblunt.DrPepper;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.github.javafaker.Faker;
import com.trblunt.DrPepper.types.Doctor;
import com.trblunt.DrPepper.types.Nurse;
import com.trblunt.DrPepper.types.Patient;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


public class NursePickPatientController {

    @FXML private ComboBox<Doctor> doctorDropdown;

    @FXML private TextField firstNameInput;
    @FXML private TextField lastNameInput;
    @FXML private DatePicker birthdateInput;

    private Nurse nurse;

    @FXML
    void handleNewPatientAction(ActionEvent event) throws IOException {
        App.setRoot("Register");
    }

    @FXML
	public void initialize() {
        ArrayList<Doctor> doctors = Server.getServer().getAllDoctorsNameAndIDOnly();
        doctorDropdown.getItems().addAll(doctors);
    }

    @FXML
    void handleCheckInAction(ActionEvent event) throws IOException {
        if (doctorDropdown.getValue() != null) {
            Doctor assignedDoc = doctorDropdown.getValue();
            NurseAddPatientInfoController controller = App.setRoot("NurseAddPatientInfo");
            Patient patient = Server.getServer().patientForLogin(firstNameInput.getText() + " " + lastNameInput.getText(), birthdateInput.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
            //TODO: Remove after database implementation
            // patient.fillWithFakeInfo();
            nurse.startPatientVisit(patient, assignedDoc);
            controller.setPatient(patient);
            controller.setNurse(nurse);
            controller.setDoctor(assignedDoc);
        }
    }

    @FXML
    protected void handleLogoutAction(ActionEvent event) throws IOException {
        App.setRoot("FrontPage");
    }

    // private Doctor[] getDoctors() {
    //     //TODO: Fetch doctors from the database
    //     return new Doctor[] { new Doctor(new Faker().name().fullName()), new Doctor(new Faker().name().fullName()), 
    //             new Doctor(new Faker().name().fullName()), new Doctor(new Faker().name().fullName()), };
    // }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

}