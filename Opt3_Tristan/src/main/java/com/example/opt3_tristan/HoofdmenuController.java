package com.example.opt3_tristan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HoofdmenuController extends SwitchableScene implements Initializable {
    @FXML
    private Label ingelogdeMederwerkerLabel;
    @FXML
    private ListView<Medewerker> ingelogdeGebruikersListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingelogdeMederwerkerLabel.setText(Medewerker.huidigeMedewerker.getUsername());

        for (Medewerker medewerker : Medewerker.IngelogdeMedewerkers) {
            ingelogdeGebruikersListView.getItems().add(medewerker);
        }

    }
    public void wisselVanGebruiker(){
        Medewerker selectedItem = ingelogdeGebruikersListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            System.out.println("Gewisseld naar: "+selectedItem);
            Medewerker.huidigeMedewerker = ingelogdeGebruikersListView.getSelectionModel().getSelectedItem();
            ingelogdeMederwerkerLabel.setText(Medewerker.huidigeMedewerker.getUsername());

        }
    }


    public void loguit(ActionEvent event){
        Medewerker.IngelogdeMedewerkers.remove(Medewerker.huidigeMedewerker);
        System.out.println(Medewerker.huidigeMedewerker.getUsername() + " has been logged out.");
        Medewerker.huidigeMedewerker = null;
        super.switchScene(event,"login.fxml");
    }

    public void login(ActionEvent event){
        super.switchScene(event,"login.fxml");

    }

}
