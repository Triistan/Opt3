package com.example.opt3_tristan;

public class Boormachine implements HuurItem {
    private String merk;
    private String type;
    private String beschrijving;

    public Boormachine(String merk, String type, String beschrijving) {
        this.merk = merk;
        this.type = type;
        this.beschrijving = beschrijving;
    }

    public double getHuurPrijsPerDag() {
        return 5;
    }

    public double getVerzekeringsKostenPerDag() {
        return 1;
    }

    public String getBeschrijving() {
        return beschrijving;
    }
}
