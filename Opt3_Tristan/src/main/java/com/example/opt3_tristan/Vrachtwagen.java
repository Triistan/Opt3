package com.example.opt3_tristan;

public class Vrachtwagen implements HuurItem {
    private double laadvermogen;
    private double gewicht;
    private String beschrijving;

    public Vrachtwagen(double laadvermogen, double gewicht, String beschrijving) {
        this.laadvermogen = laadvermogen;
        this.gewicht = gewicht;
        this.beschrijving = beschrijving;
    }

    public double getHuurPrijsPerDag() {
        return 0.10 * laadvermogen;
    }

    public double getVerzekeringsKostenPerDag() {
        return 0.01 * gewicht;
    }

    public String getBeschrijving() {
        return beschrijving;
    }
}
