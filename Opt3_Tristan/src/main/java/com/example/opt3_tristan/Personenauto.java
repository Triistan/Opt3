package com.example.opt3_tristan;



public class Personenauto implements HuurItem {
    private String merk;
    private double gewicht;
    private String beschrijving;
    private boolean huurStatus = false;

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
    public String getInformatie() {
        StringBuilder sb = new StringBuilder();
        sb.append("Merk: ").append(merk).append("\n");
        sb.append("Gewicht: ").append(gewicht).append(" kg\n");
        sb.append("Beschrijving: ").append(getBeschrijving()).append("\n");
        sb.append("Huurprijs per dag: €").append(getHuurPrijsPerDag()).append("\n");
        sb.append("Verzekeringskosten per dag: €").append(getVerzekeringsKostenPerDag()).append("\n");
        return sb.toString();
    }
    @Override
    public String toString() {
        return "Personenauto " + merk;
    }
}
