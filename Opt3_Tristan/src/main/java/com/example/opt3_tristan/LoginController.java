package com.example.opt3_tristan;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginController {
    @FXML
    private TextField gebruikersnaamTextField;
    @FXML
    private TextField wachtwoordTextField;
    @FXML
    private Label loginStatus;

    private HashMap<String, String> accounts = new HashMap<>();

    public boolean login(HashMap<String, String> accounts, String ingevoerdGebruikersnaam, String ingevoerdWachtwoord) {

        if (accounts.containsKey(ingevoerdGebruikersnaam)) {

            String opgeslagenWachtwoord= accounts.get(ingevoerdGebruikersnaam);


            if (ingevoerdWachtwoord.equals(opgeslagenWachtwoord)) {
                return true;
            }
        }
        return false;
    }

    public void maaktestgebruikers() {

        accounts.put("Jan", "Welkom01");
        accounts.put("Bob", "12345");
    }

    public void handleLogin() {
        maaktestgebruikers();
        String ingevoerdGebruikersnaam = gebruikersnaamTextField.getText();
        String ingevoerdWachtwoord = wachtwoordTextField.getText();

        boolean isLoggedIn = login(accounts, ingevoerdGebruikersnaam, ingevoerdWachtwoord);

        if (isLoggedIn) {
            loginStatus.setText("Ingelogd");
        } else {
            loginStatus.setText("Verkeerde wachtwoord of gebruikersnaam");
        }
    }
}