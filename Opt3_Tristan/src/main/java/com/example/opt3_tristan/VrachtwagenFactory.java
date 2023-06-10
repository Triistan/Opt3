package com.example.opt3_tristan;

public class VrachtwagenFactory implements HuurItemFactory{
    private double laadvermogen;
    private double gewicht;
    private String beschrijving;

    public VrachtwagenFactory(double laadvermogen, double gewicht, String beschrijving) {
        this.laadvermogen = laadvermogen;
        this.gewicht = gewicht;
        this.beschrijving = beschrijving;
    }

    public HuurItem maakHuurItem() {
        return new Vrachtwagen(laadvermogen, gewicht, beschrijving);
    }
}


