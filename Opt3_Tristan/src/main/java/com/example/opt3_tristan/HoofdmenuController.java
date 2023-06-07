package com.example.opt3_tristan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HoofdmenuController extends SwitchableScene implements Initializable {
    @FXML
    private Label ingelogdeMederwerkerLabel;
    @FXML
    private ListView<Medewerker> ingelogdeGebruikersListView;
    @FXML
    private TabPane mainPane;

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

            for (int i = mainPane.getTabs().size() - 1; i >= 0; i--) {
                Tab tab = mainPane.getTabs().get(i);
                if (!tab.getText().equals("Hoofdmenu")) {
                    mainPane.getTabs().remove(i);
                }
            }
        }
    }


    public void openOverzicht(){
        //Maakt een nieuw tablad aan in de tabpane
        Tab overzichtTab = new Tab();
        overzichtTab.setText("Overzicht");
        overzichtTab.setClosable(true);

        //Maakt een ListView waar alle producten in te komen staan
        ListView<String> producten = new ListView<>();
        ObservableList<String> testitems = FXCollections.observableArrayList ("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");
        producten.setItems(testitems);

        //Maak een textArea voor de details van alle producten
        TextArea textArea = new TextArea();
        textArea.setVisible(false);
        producten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            textArea.setText("Details van: "+newValue);
            textArea.setVisible(true);
        });

        //Maakt een borderPane om de ListView en TextArea in kwijt te kunnen
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(producten);
        borderPane.setRight(textArea);

        //Label met naam van ingelogde medewerker
        Label bottomLabel = new Label("Ingelogde medewerker: "+ Medewerker.huidigeMedewerker.getUsername());
        borderPane.setBottom(bottomLabel);
        BorderPane.setAlignment(bottomLabel, javafx.geometry.Pos.BOTTOM_LEFT);

        overzichtTab.setContent(borderPane);
        mainPane.getTabs().add(overzichtTab);
    }

    public void openBeheer(){
        Tab beheerTab = new Tab();
        beheerTab.setText("Beheer");
        beheerTab.setClosable(true);

        //Maakt een ListView waar alle producten in te komen staan
        ListView<String> producten = new ListView<>();
        ObservableList<String> testitems = FXCollections.observableArrayList ("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");
        producten.setItems(testitems);

        //Maak een textArea voor de details van alle producten
        TextArea textArea = new TextArea();
        textArea.setVisible(false);
        producten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            textArea.setText("Details van: "+newValue);
            textArea.setVisible(true);
        });

        //Maakt een borderPane om de ListView en TextArea in kwijt te kunnen
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(producten);
        borderPane.setRight(textArea);

        //Label met naam van ingelogde medewerker
        Label bottomLabel = new Label("Ingelogde medewerker: "+ Medewerker.huidigeMedewerker.getUsername());
        borderPane.setBottom(bottomLabel);
        BorderPane.setAlignment(bottomLabel, javafx.geometry.Pos.BOTTOM_LEFT);

        beheerTab.setContent(borderPane);
        mainPane.getTabs().add(beheerTab);
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
