package com.trblunt.DrPepper.types;

public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;

    public Address(String streetAddress, String city, String state, int zipCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String toString() {
        return streetAddress + " " + city + " " + state + " " + zipCode;
    }
}
