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
    public String getInformatie() {
        StringBuilder sb = new StringBuilder();
        sb.append("Merk: ").append(merk).append("\n");
        sb.append("Type: ").append(type).append(" kg\n");
        sb.append("Beschrijving: ").append(getBeschrijving()).append("\n");
        sb.append("Huurprijs per dag: €").append(getHuurPrijsPerDag()).append("\n");
        sb.append("Verzekeringskosten per dag: €").append(getVerzekeringsKostenPerDag()).append("\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Boormachine " + merk;
    }
}
