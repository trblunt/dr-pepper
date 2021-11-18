package com.trblunt.DrPepper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.trblunt.DrPepper.types.Doctor;
import com.trblunt.DrPepper.types.History;
import com.trblunt.DrPepper.types.Nurse;
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
                    visit.reasonForVisit = "Test: " + visits.getString("testname") + " Result: " + visits.getString("testresult");
                    pastVisits.add(visit);
                }
                ArrayList<String> persc = perscriptionsForPatientID(patient.userID);
                ArrayList<String> immun = immunForPatientID(patient.userID);

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

    ArrayList<String> perscriptionsForPatientID(int id){
        try {
            String perscSQL = "SELECT * FROM Perscription WHERE user_id = ?";
            PreparedStatement getVisits = c.prepareStatement(perscSQL);
            getVisits.setInt(1, id);
            ResultSet rs = getVisits.executeQuery();
            
            ArrayList<String> perscs = new ArrayList<String>();
            while (rs.next()){
                String p = rs.getString("medicationname") + " " + rs.getInt("quantityperday");
                perscs.add(p);
                System.out.println(p);
            }
            return perscs;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }

        return null;
    }

    ArrayList<String> immunForPatientID(int id){
        try {
            String perscSQL = "SELECT * FROM Immunization WHERE user_id = ?";
            PreparedStatement getVisits = c.prepareStatement(perscSQL);
            getVisits.setInt(1, id);
            ResultSet rs = getVisits.executeQuery();
            
            ArrayList<String> perscs = new ArrayList<String>();
            while (rs.next()){
                String p = rs.getString("name");
                perscs.add(p);
                System.out.println(p);
            }
            return perscs;
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
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

    Doctor doctororLogin(String name, String password){
        try {
            // the cast is nessesary because the Java Date format was not working and Java is enforcing type casting
            String sql = "SELECT * FROM SUser U, Staff S WHERE U.user_id = S.user_id AND username = ? AND password = ?";
            PreparedStatement sm = c.prepareStatement(sql); // YYYY-MM-DD
            sm.setString(1, name);
            sm.setString(2, password);
            System.out.println(sm.toString());
            ResultSet rs = sm.executeQuery();
            System.out.println(rs);
            rs.next();
            System.out.println(rs.getString("name"));
            Doctor doc = new Doctor(rs.getString("name"));
            doc.userID = rs.getInt("user_id");
            doc.email = rs.getString("email");
            doc.address = rs.getString("address");
            doc.dateOfBirth = rs.getString("dob");
            // Doctor doc = new Patient(rs.getString("name"), rs.getString("email"), rs.getString("address"), dob, rs.getString("insuranceProvider"), rs.getInt("insuranceID"), rs.getString("pharmacyAddress"));
            doc.userID = rs.getInt("user_id");
            // get visits
            // String patSQL = "SELECT * FROM V WHERE doctor_id = ?";
            // PreparedStatement getPatients = c.prepareStatement(patSQL);
            // getPatients.setInt(1, doc.userID);
            // ResultSet pat = getPatients.executeQuery();

            // ArrayList<Patient> patients = new ArrayList<Patient>();
            // while (pat.next()){
            //     Visit visit = new Visit();
            //     visit.date = visits.getString("date");
    
            //     Vitals vitals = new Vitals(visits.getInt("height"), visits.getInt("weight"), visits.getFloat("temp"), "blood presure", "allergies");
            //     visit.vitals =  vitals;
            //     pastVisits.add(visit);
            // }
        
            
     

            return doc;

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }
        return null;
    }


    Nurse nurseLogin(String name, String password){
        try {
            // the cast is nessesary because the Java Date format was not working and Java is enforcing type casting
            String sql = "SELECT * FROM SUser U, Staff S WHERE U.user_id = S.user_id AND username = ? AND password = ?";
            PreparedStatement sm = c.prepareStatement(sql); // YYYY-MM-DD
            sm.setString(1, name);
            sm.setString(2, password);
            System.out.println(sm.toString());
            ResultSet rs = sm.executeQuery();
            System.out.println(rs);
            rs.next();
            System.out.println(rs.getString("name"));
            Nurse nurse = new Nurse(rs.getString("name"));
            nurse.userID = rs.getInt("user_id");
            nurse.email = rs.getString("email");
            nurse.address = rs.getString("address");
            nurse.dateOfBirth = rs.getString("dob");
            // Doctor doc = new Patient(rs.getString("name"), rs.getString("email"), rs.getString("address"), dob, rs.getString("insuranceProvider"), rs.getInt("insuranceID"), rs.getString("pharmacyAddress"));
            nurse.userID = rs.getInt("user_id");
            // get visits
            // String patSQL = "SELECT * FROM V WHERE doctor_id = ?";
            // PreparedStatement getPatients = c.prepareStatement(patSQL);
            // getPatients.setInt(1, doc.userID);
            // ResultSet pat = getPatients.executeQuery();

            // ArrayList<Patient> patients = new ArrayList<Patient>();
            // while (pat.next()){
            //     Visit visit = new Visit();
            //     visit.date = visits.getString("date");
    
            //     Vitals vitals = new Vitals(visits.getInt("height"), visits.getInt("weight"), visits.getFloat("temp"), "blood presure", "allergies");
            //     visit.vitals =  vitals;
            //     pastVisits.add(visit);
            // }
        
            
     

            return nurse;

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }
        return null;
    }

    ArrayList<Doctor> getAllDoctorsNameAndIDOnly(){

        try {
            ArrayList<Doctor> docs = new ArrayList<Doctor>();
            // get docs
            String docsSQL = "SELECT * FROM SUser U, Staff S WHERE U.user_id = S.user_id and S.isDoctor = True";
            PreparedStatement getDocs = c.prepareStatement(docsSQL);
            
            ResultSet docsRS = getDocs.executeQuery();
    
    
            while (docsRS.next()) {
                Doctor newDoc = new Doctor(docsRS.getString("name"));
                newDoc.userID = docsRS.getInt("user_id");
                System.out.println(docsRS.getString("name"));
                docs.add(newDoc);
            }
            getDocs.close();
            return docs;
            // Doctor[] docA = (Doctor[]) docs.toArray();
            // return docA;
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }






        return null;
    }


    void addNurseVisit(Patient currPatient, Visit currVisit, Doctor currDoctor){
        try {
            String newVisitSQL = "INSERT INTO Visit (patient_id, date, height, weight, temp, bpsystolic, bpdiastolic, testname, testresult, complete, doctor_id) VALUES (?, CAST(? AS DATE), ?, ?, ?, ?, ?, ?, ?, 'FALSE', ?);";
            PreparedStatement newVisit = c.prepareStatement(newVisitSQL);
            newVisit.setInt(1, currPatient.userID);
            newVisit.setString(2, currVisit.date);
            newVisit.setInt(3, currVisit.vitals.height);
            newVisit.setDouble(4, currVisit.vitals.weight);
            newVisit.setDouble(5, currVisit.vitals.temp);
            newVisit.setInt(6, currVisit.bpsystolic);
            newVisit.setInt(7, currVisit.bpdiastolic);
            newVisit.setString(8, currVisit.testName);
            newVisit.setString(9, currVisit.testResult);
            newVisit.setInt(10, currDoctor.userID);
            System.out.println(newVisit.toString());
            ResultSet rs = newVisit.executeQuery();
            newVisit.close();

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }
    }

    ArrayList<Visit> getActiveVisitsForDoctor(Doctor doctor){
        System.out.println("Getting all Active Patients");
        System.out.println(doctor.userID);
        try {
            
        
            String pSQL = "SELECT * FROM SUser U, Patient P, Visit V WHERE U.user_id = P.user_id AND U.user_id = V.patient_id AND V.complete = 'FALSE' AND V.doctor_id = ?";
            PreparedStatement getP = c.prepareStatement(pSQL);
            getP.setInt(1, doctor.userID);
            System.out.println(getP.toString());
            ResultSet pRS = getP.executeQuery();

            ArrayList<Visit> ps = new ArrayList<Visit>();
            while (pRS.next()) {
                Visit cuVisit = new Visit();
                cuVisit.date = pRS.getString("date");
                cuVisit.visitID = pRS.getInt("visit_id");
                Vitals v = new Vitals(pRS.getInt("height"), pRS.getDouble("weight"), pRS.getDouble("temp"), pRS.getString("bpsystolic") + "/" + pRS.getString("bpdiastolic"), pRS.getString("height"));
                cuVisit.vitals = v;
                cuVisit.testName = pRS.getString("testname");
                cuVisit.testResult = pRS.getString("testresult");
                Patient newPatient = patientForLogin(pRS.getString("name"), pRS.getString("dob"));
                cuVisit.patient = newPatient;
                ps.add(cuVisit);
                System.out.println(newPatient.name);
            }
            getP.close();

            System.out.println("RETURN PS");
            System.out.println(ps.toString());
            return ps;
            // Doctor[] docA = (Doctor[]) docs.toArray();
            // return docA;
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }
        System.out.println("Done getting all active patients");
        return null;
    }

    void saveDocVisit(Patient currPatient, Visit currVisit, Doctor currDoctor){
        try {
            String updateVisitSQL = "UPDATE Visit SET  height = ?, weight = ?, temp = ?, bpsystolic = ?, bpdiastolic = ?, testname = ?, testresult = ?, complete = 'True' WHERE visit_id = ?";
            PreparedStatement updateVisit = c.prepareStatement(updateVisitSQL);
            
            updateVisit.setInt(1, currVisit.vitals.height);
            updateVisit.setDouble(2, currVisit.vitals.weight);
            updateVisit.setDouble(3, currVisit.vitals.temp);
            updateVisit.setInt(4, currVisit.bpsystolic);
            updateVisit.setInt(5, currVisit.bpdiastolic);
            updateVisit.setString(6, currVisit.testName);
            updateVisit.setString(7, currVisit.testResult);
            updateVisit.setInt(8, currVisit.visitID);
            System.out.println(updateVisit.toString());
            ResultSet rs = updateVisit.executeQuery();
            updateVisit.close();

        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
            // System.exit(0);
        }
    }

}
