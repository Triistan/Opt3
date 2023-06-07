package com.example.opt3_tristan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController extends SwitchableScene implements Initializable {
    @FXML
    private TextField gebruikersnaamTextField;
    @FXML
    private TextField wachtwoordTextField;
    @FXML
    private Label loginStatus;
    @FXML
    private ListView<Medewerker> ingelogdeGebruikersListView;

    private HashMap<String, String> accounts = new HashMap<>();
    private ArrayList<Medewerker> medewerkers = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Medewerker medewerker1 = new Medewerker("Jan","Welkom01");
        medewerkers.add(medewerker1);
        Medewerker medewerker2 = new Medewerker("Bob","12345");
        medewerkers.add(medewerker2);
        Medewerker medewerker3 = new Medewerker("Tristan","ja");
        medewerkers.add(medewerker3);

        if (Medewerker.IngelogdeMedewerkers.size() > 0) {
            for (Medewerker medewerker : Medewerker.IngelogdeMedewerkers) {
                ingelogdeGebruikersListView.getItems().add(medewerker);
            }

        }

    }
    public void handleLogin(ActionEvent event) {
        String ingevoerdGebruikersnaam = gebruikersnaamTextField.getText();
        String ingevoerdWachtwoord = wachtwoordTextField.getText();


        for (Medewerker medewerker : Medewerker.IngelogdeMedewerkers) {
            if (medewerker.getUsername().equals(ingevoerdGebruikersnaam)) {
                loginStatus.setText("Account is al ingelogd");
                return;
            }
        }

        for (Medewerker medewerker : medewerkers) {
            if (medewerker.getUsername().equals(ingevoerdGebruikersnaam) && medewerker.getWachtwoord().equals(ingevoerdWachtwoord)) {
                Medewerker.IngelogdeMedewerkers.add(medewerker);
                Medewerker.huidigeMedewerker = medewerker;

                loginStatus.setText("Ingelogd");
                super.switchScene(event,"hoofdmenu.fxml");
                return;
            }
        }
        loginStatus.setText("Verkeerde wachtwoord of gebruikersnaam");
    }
    public void opnieuwInloggen(ActionEvent event) {
        Medewerker selectedMedewerker = ingelogdeGebruikersListView.getSelectionModel().getSelectedItem();

        if (selectedMedewerker != null) {

            Medewerker.huidigeMedewerker = selectedMedewerker;
            super.switchScene(event,"hoofdmenu.fxml");

        } else {
            loginStatus.setText("geen ingelogde gebruiker geselecteerd");
        }
    }



}