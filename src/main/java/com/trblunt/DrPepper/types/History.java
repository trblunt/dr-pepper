package com.trblunt.DrPepper.types;

import java.util.ArrayList;

public class History {
    private ArrayList<Visit> pastVisits; // all of these need to be fetched from the database
    private ArrayList<String> perscriptions;
    private ArrayList<String> immunizations;
    private String previousHealthIssues;    // I don't know where this information is going to be set
}
