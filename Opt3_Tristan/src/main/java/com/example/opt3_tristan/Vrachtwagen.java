package com.example.opt3_tristan;

public class Vrachtwagen implements HuurItem {
    private double laadvermogen;
    private double gewicht;
    private String beschrijving;
    private boolean huurStatus;

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
    public String getInformatie() {
        StringBuilder sb = new StringBuilder();
        sb.append("Laadvermogen: ").append(laadvermogen).append("\n");
        sb.append("Gewicht: ").append(gewicht).append(" kg\n");
        sb.append("Beschrijving: ").append(getBeschrijving()).append("\n");
        sb.append("Huurprijs per dag: €").append(getHuurPrijsPerDag()).append("\n");
        sb.append("Verzekeringskosten per dag: €").append(getVerzekeringsKostenPerDag()).append("\n");
        return sb.toString();
    }
    @Override
    public String toString() {
        return "Vrachtwagen " + laadvermogen +"kg";
    }
}
