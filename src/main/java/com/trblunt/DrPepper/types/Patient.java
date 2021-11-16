package com.trblunt.DrPepper.types;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.github.javafaker.Faker;

public class Patient extends User {

    public PatientRecord record;
    public String firstName;
    public String lastName;
    public String insuranceProvider;
    public int insuranceID;
    public String pharmacyAddress;
    public Doctor assignedDoctor;

    // use this if the patient is new
    public Patient(String legalName, String email, String address, String dateOfBirth, String insuranceProvider, int insuranceID) {
        super(legalName);
        registerPatient(legalName, email, address, dateOfBirth, insuranceProvider, insuranceID);
        // TODO can either add patient to the database here or in registerPatient()
    }

    // use this if the Patient exists already and if signing in/or the Nurse if checking them in

    //Reimplemented for testing until ability to pull patient from database from ID added
    public Patient(String first_name, String last_name, String birthday) {
        super(first_name + " " + last_name);
        firstName = first_name;
        lastName = last_name;
        dateOfBirth = birthday;
        // TODO: get all the information from the database and set each of the variables
        email = "";
        address = "";
        insuranceProvider = "";
        insuranceID = 0;
        pharmacyAddress = "";
        // History history = new History();
        /*
         * TODO For History, need to get and pass an array list of visits, array list of
         * prescriptions, an array list of immunizations, and a string of
         * previousHealthIssues from the databse
         */
        // record = new PatientRecord(patient history from database);
        /* For record once History is populated this line can be uncommented */

        // TODO Remove next line after implement database load
        record = new PatientRecord(
                new History(new ArrayList<Visit>(), new ArrayList<String>(), new ArrayList<String>(), ""));
    }

    // TODO this will need to add the patient to the database
    public void registerPatient(String legalName, String email, String address, String dateOfBirth, String insuranceProvider, int insuranceID) {
        this.name = legalName;
        firstName = name.substring(0, name.indexOf(" "));
        lastName = name.substring(name.indexOf(" ")+1);
        this.email = email;
        this.address = address.toString();
        this.dateOfBirth = dateOfBirth;
        this.insuranceProvider = insuranceProvider;
        this.insuranceID = insuranceID;
        this.record = new PatientRecord(
                new History(new ArrayList<Visit>(), new ArrayList<String>(), new ArrayList<String>(), ""));
    }

    // TODO this will need to update some fields of the database
    /*When using this method put "" for strings or 0 for ints if that field should not be updated*/
    public void modifyAccountInfo(String name, String email, String dateOfBirth, String address, String insuranceProvider, int insuranceID, String pharmacyAddress) {
        if (!name.isEmpty()) {
            this.name = name;
            firstName = name.substring(0, name.indexOf(" "));
            lastName = name.substring(name.indexOf(" ")+1);
            // update in database
        }
        if (!email.isEmpty()) {
            this.email = email;
            // update in database
        }
        if (!dateOfBirth.isEmpty()) {
            this.dateOfBirth = dateOfBirth;
            // update in database
        }
        if (!address.isEmpty()) {
            this.address = address;
            // update in database
        }
        if (!insuranceProvider.isEmpty()) {
            this.insuranceProvider = insuranceProvider;
            // update in database
        }
        if (insuranceID != 0) {
            this.insuranceID = insuranceID;
            // update in database
        }
        if (!pharmacyAddress.isEmpty()) {
            this.pharmacyAddress = pharmacyAddress;
            // update in database
        }
    }

    //doesn't actually do anything
    public void messageDoctor(String message, Doctor doctor) {

    }

    public ArrayList<Visit> viewPastVisits() {
        return record.patientHistory.pastVisits;
    }

    //TODO: Remove this after database implementation, only use for testing.
    public void fillWithFakeInfo() {
        Faker faker = new Faker();
        email = !this.email.isEmpty() ? this.email : faker.bothify("?????????????##@gmail.com");
        com.github.javafaker.Address fakeAddress = faker.address();
        address = !address.isEmpty() ? this.address : new Address(fakeAddress.streetAddress(), fakeAddress.city(), fakeAddress.state(),
                faker.number().numberBetween(1000, 99999)).toString();
        dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").format(faker.date().between(Date.valueOf("1920-01-01"), Date.valueOf("2000-12-31")));
        insuranceProvider = !this.insuranceProvider.isEmpty() ? this.insuranceProvider : (faker.company().name() + " Insurance");
        insuranceID = (insuranceID != 0) ? this.insuranceID : faker.number().numberBetween(1, 1000000000);
        pharmacyAddress = !pharmacyAddress.isEmpty() ? this.pharmacyAddress : faker.address().streetAddress();

        Visit visit1 = new Visit();
        visit1.date = new SimpleDateFormat("yyyy-MM-dd")
                .format(faker.date().between(Date.valueOf("2010-01-01"), Date.valueOf("2020-12-31")));
        visit1.reasonForVisit = name + " came in for a regular checkup.";
        ArrayList<Visit> pastVisits = new ArrayList<Visit>();
        pastVisits.add(visit1);

        ArrayList<String> perscriptions = new ArrayList<String>();
        perscriptions.add("Triactin");
        perscriptions.add("Dextrofinil");

        ArrayList<String> immunizations = new ArrayList<String>();
        immunizations.add("COVID-19");
        immunizations.add("Measles");

        History history = new History(pastVisits, perscriptions, immunizations,
                name + " has had trouble touching his toes in the past.");

        this.record = new PatientRecord(history);
    }
	
}
