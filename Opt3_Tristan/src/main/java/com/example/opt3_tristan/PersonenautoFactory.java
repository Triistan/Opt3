package com.example.opt3_tristan;

public class PersonenautoFactory implements HuurItemFactory {
    private String merk;
    private double gewicht;
    private String beschrijving;

    public PersonenautoFactory(String merk, double gewicht, String beschrijving) {
        this.merk = merk;
        this.gewicht = gewicht;
        this.beschrijving = beschrijving;
    }
    public HuurItem maakHuurItem() {
        return new Personenauto(merk, gewicht, beschrijving);
    }
}
