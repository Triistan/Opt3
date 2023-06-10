package com.example.opt3_tristan;



public class Personenauto implements HuurItem {
    private String merk;
    private double gewicht;
    private String beschrijving;

    public Personenauto(String merk, double gewicht, String beschrijving) {
        this.merk = merk;
        this.gewicht = gewicht;
        this.beschrijving = beschrijving;
    }

    public double getHuurPrijsPerDag() {
        return 50;
    }

    public double getVerzekeringsKostenPerDag() {
        return 0.01 * gewicht;
    }

    public String getBeschrijving() {
        return beschrijving;
    }
}
