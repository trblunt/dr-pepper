package com.trblunt.DrPepper.types;

public class Vitals {
    private int height;
    private int weight;
    private int temp;
    private String bloodPressure;
    // private boolean overTwelve;  // don't know where we want to implement this 
    private String allergies;

    public Vitals(int height, int weight, int temp, String bloodPressure, String allergies) {
        this.height = height;
        this.weight = weight;
        this.temp = temp;
        this.bloodPressure = bloodPressure;
        this.allergies = allergies;
    }
}
