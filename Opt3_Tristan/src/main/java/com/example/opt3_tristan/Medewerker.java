package com.example.opt3_tristan;

import java.util.ArrayList;

public class Medewerker {
    private String username;
    private String wachtwoord;

    public static ArrayList<Medewerker> IngelogdeMedewerkers = new ArrayList<>();
    public static Medewerker huidigeMedewerker;

    public Medewerker(String username, String wachtwoord) {
        this.username = username;
        this.wachtwoord = wachtwoord;
    }

    public String getUsername() {
        return username;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    @Override
    public String toString() {
        return username;
    }


}
