package com.example.opt3_tristan;

public class BoormachineFactory implements HuurItemFactory {
    private String merk;
    private String type;
    private String beschrijving;

    public BoormachineFactory(String merk, String type, String beschrijving) {
        this.merk = merk;
        this.type = type;
        this.beschrijving = beschrijving;
    }

    public HuurItem maakHuurItem() {
        return new Boormachine(merk, type, beschrijving);
    }
}
