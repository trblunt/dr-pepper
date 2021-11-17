package com.trblunt.DrPepper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.trblunt.DrPepper.types.History;
import com.trblunt.DrPepper.types.Patient;
import com.trblunt.DrPepper.types.PatientRecord;
import com.trblunt.DrPepper.types.Visit;
import com.trblunt.DrPepper.types.Vitals;


public class Server {

    private static Server server = new Server();
    private Connection c = null;

    private Server (){
        // init the server
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://198.58.101.42:5432/pepper", "postgres", "drpepper");
            System.out.println("Connected!");
        } catch (Exception e) {
            System.out.println("Failed to connect!");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static Server getServer(){
        return server;
    }

    Patient patientForLogin(String name, String dob){
        try {
            // the cast is nessesary because the Java Date format was not working and Java is enforcing type casting
            String sql = "SELECT * FROM SUser U, Patient P WHERE U.user_id = P.user_id AND name = ? AND dob = CAST(? AS DATE);";
            PreparedStatement sm = c.prepareStatement(sql); // YYYY-MM-DD
            sm.setString(1, name);
            sm.setString(2, dob);
            System.out.println(sm.toString());
            ResultSet rs = sm.executeQuery();
            System.out.println(rs);
    
    
            if (rs.next() == true) {
                Patient patient = new Patient(rs.getString("name"), rs.getString("email"), rs.getString("address"), dob, rs.getString("insuranceProvider"), rs.getInt("insuranceID"), rs.getString("pharmacyAddress"));
                patient.userID = rs.getInt("user_id");
                // get visits
                String visitsSQL = "SELECT * FROM Visit WHERE patient_id = ?";
                PreparedStatement getVisits = c.prepareStatement(visitsSQL);
                getVisits.setInt(1, patient.userID);
                ResultSet visits = getVisits.executeQuery();

                ArrayList<Visit> pastVisits = new ArrayList<Visit>();
                while (visits.next()){
                    Visit visit = new Visit();
                    visit.date = visits.getString("date");
       
                    Vitals vitals = new Vitals(visits.getInt("height"), visits.getInt("weight"), visits.getFloat("temp"), "blood presure", "allergies");
                    visit.vitals =  vitals;
                    pastVisits.add(visit);
                }
                ArrayList<String> persc = new ArrayList<String>();
                ArrayList<String> immun = new ArrayList<String>();

                String prevH = "previous health issues";
                History ph = new History(pastVisits, persc, immun, prevH);
                patient.record = new PatientRecord(ph);

                return patient;
            }
            sm.close();

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }
        return null;
    }

    void updatePatient(Patient patient){
        try {
            System.out.println(patient.name);
            // the cast is nessesary because the Java Date format was not working and Java is enforcing type casting
            // String sql = "UPDATE SUser U, Patient P SET U.name = ?, U.email = ?, U.dob = CAST(? AS DATE), P.insuranceProvider = ?, P.insuranceID = ?, P.pharmacyAddress = ? WHERE user_id = ?";
            // String updateSql = "UPDATE SUser SET name = ?, email = ?, dob = CAST(? AS DATE) WHERE user_id = ?; UPDATE Patient SET insuranceProvider = ?, insuranceID = ?, pharmacyAddress = ? WHERE user_id = ?";
            String updateUserSQL = "UPDATE SUser SET name = ?, email = ?, dob = CAST(? AS DATE) WHERE user_id = ?";
            PreparedStatement updateUser = c.prepareStatement(updateUserSQL);
            updateUser.setString(1, patient.name);
            updateUser.setString(2, patient.email);
            updateUser.setString(3, patient.dateOfBirth);
            updateUser.setInt(4, patient.userID);
            System.out.println(updateUser.toString());
            int success = updateUser.executeUpdate();
            System.out.println(success);
            updateUser.close();
            System.out.println(patient.insuranceProvider);
            System.out.println(patient.insuranceID);
            System.out.println(patient.pharmacyAddress);
            System.out.println(patient.userID);
            String updatePatientSQL = "UPDATE Patient SET insuranceProvider = ?, insuranceID = ?, pharmacyAddress = ? WHERE user_id = ?";
            PreparedStatement updatePatient = c.prepareStatement(updatePatientSQL);
            updatePatient.setString(1, patient.insuranceProvider);
            updatePatient.setInt(2, patient.insuranceID);
            updatePatient.setString(3, patient.pharmacyAddress);
            updatePatient.setInt(4, patient.userID);
            System.out.println(updatePatient.toString());
            success = updatePatient.executeUpdate();
            System.out.println(success);
            updateUser.close();


            


        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }
    }

    void registerPatient(Patient newPatient){
        try {
            String newUserSQL = "INSERT INTO SUser (name, email, address, dob) Values (?, ?, ?, CAST(? AS DATE)) RETURNING user_id;";
            PreparedStatement newUser = c.prepareStatement(newUserSQL);
            newUser.setString(1, newPatient.name);
            newUser.setString(2, newPatient.email);
            newUser.setString(3, newPatient.address);
            newUser.setString(4, newPatient.dateOfBirth);
            System.out.println(newUser.toString());
            ResultSet rs = newUser.executeQuery();
            rs.next();
            int newID = rs.getInt("user_id");
            newUser.close();

            String newPatientSQL = "INSERT INTO Patient VALUES (?, ?, ?, ?);";
            PreparedStatement newPatientSM = c.prepareStatement(newPatientSQL);
            newPatientSM.setInt(1, newID);
            newPatientSM.setString(2, newPatient.insuranceProvider);
            newPatientSM.setInt(3, newPatient.insuranceID);
            newPatientSM.setString(4, newPatient.pharmacyAddress);
            System.out.println(newPatientSM.toString());
            ResultSet s = newPatientSM.executeQuery();
            newPatientSM.close();


            


        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }
    }

}
