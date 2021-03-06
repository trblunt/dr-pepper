package com.trblunt.DrPepper.types;

import java.util.ArrayList;

public class History {
    public ArrayList<Visit> pastVisits; 
    public ArrayList<String> perscriptions;
    public ArrayList<String> immunizations;
    public String previousHealthIssues;    // I don't know where this information is going to be set originally

    public History(ArrayList<Visit> pastVisits, ArrayList<String> perscriptions, ArrayList<String> immunizations, String previousHealthIssues) {
        this.pastVisits = pastVisits;
        this.perscriptions = perscriptions;
        this.immunizations = immunizations;
        this.previousHealthIssues = previousHealthIssues;
    }

    public void addPastVisit(Visit v) {
        pastVisits.add(v);
    }

    public void addPerscription(String p) {
        perscriptions.add(p);
    }

    public void addImmunization(String i) {
        immunizations.add(i);
    }

    public void updatePrevHealthIssues(String h) {
        previousHealthIssues = h;
    }
}
