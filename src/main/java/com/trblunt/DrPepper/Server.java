package com.trblunt.DrPepper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.trblunt.DrPepper.types.Patient;


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
                Patient patient = new Patient(rs.getString("name"), rs.getString("email"), rs.getString("address"), dob, rs.getString("insuranceProvider"), rs.getInt("insuranceID"));
                // patient.
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

}
