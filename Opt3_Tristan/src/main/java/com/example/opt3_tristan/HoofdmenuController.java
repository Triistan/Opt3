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

    public ArrayList<HuurItem> huurItems = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingelogdeMederwerkerLabel.setText(Medewerker.huidigeMedewerker.getUsername());

        for (Medewerker medewerker : Medewerker.IngelogdeMedewerkers) {
            ingelogdeGebruikersListView.getItems().add(medewerker);
        }

       //3 hardcoded personenauto's
        HuurItemFactory autoFabriek1 = new PersonenautoFactory("Toyota", 1200, "Een comfortabele personenauto");
        HuurItem auto1 = autoFabriek1.maakHuurItem();
        huurItems.add(auto1);
        HuurItemFactory autoFabriek2 = new PersonenautoFactory("Volvo", 2500, "Een veilige personenauto");
        HuurItem auto2 = autoFabriek2.maakHuurItem();
        huurItems.add(auto2);
        HuurItemFactory autoFabriek3 = new PersonenautoFactory("Porsche", 1500, "Een vrij snelle personenauto");
        HuurItem auto3 = autoFabriek3.maakHuurItem();
        huurItems.add(auto3);

        //3 hardcoded vrachtwagens
        HuurItemFactory vrachtwagenFabriek1 = new VrachtwagenFactory(20000, 18000,"Een wat kleinere vrachtwagen met 2 assen");
        HuurItem vrachtwagen1 = vrachtwagenFabriek1.maakHuurItem();
        huurItems.add(vrachtwagen1);
        HuurItemFactory vrachtwagenFabriek2 = new VrachtwagenFactory(30000, 25000,"Een middelgrote vrachtwagen met 3 assen");
        HuurItem vrachtwagen2 = vrachtwagenFabriek2.maakHuurItem();
        huurItems.add(vrachtwagen2);
        HuurItemFactory vrachtwagenFabriek3 = new VrachtwagenFactory(32000, 30000,"Een grote vrachtwagen met 4 assen");
        HuurItem vrachtwagen3 = vrachtwagenFabriek3.maakHuurItem();
        huurItems.add(vrachtwagen3);

        //3 hardcoded Boormachines
        HuurItemFactory BoormachineFabriek1 = new BoormachineFactory("Makita","HP457DWE accu schroef en klopboormachine","een veelzijdige schroefboormachine die ook als klopboor kan functioneren");
        HuurItem boormachine1 = BoormachineFabriek1.maakHuurItem();
        huurItems.add(boormachine1);
        HuurItemFactory BoormachineFabriek2 = new BoormachineFactory("Bosch","EasyDrill","Een comfortabele en veelzijdige boormachine");
        HuurItem boormachine2 = BoormachineFabriek2.maakHuurItem();
        huurItems.add(boormachine2);
        HuurItemFactory BoormachineFabriek3 = new BoormachineFactory("Einhell","TE-CD","een krachtige alleskunner");
        HuurItem boormachine3 = BoormachineFabriek3.maakHuurItem();
        huurItems.add(boormachine3);




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


    public void openOverzicht() {
        // Creates a new tab in the tabpane
        Tab overzichtTab = new Tab();
        overzichtTab.setText("Overzicht");
        overzichtTab.setClosable(true);

        // Creates a ListView to display all products
        ListView<HuurItem> producten = new ListView<>();
        ObservableList<HuurItem> items = FXCollections.observableArrayList(huurItems);
        producten.setItems(items);

        // Creates a TextArea for the details of all products
        TextArea textArea = new TextArea();
        textArea.setVisible(false);
        producten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textArea.setText(newValue.getInformatie());
                textArea.setVisible(true);
            }
        });

        // Creates a BorderPane to accommodate the ListView and TextArea
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(producten);
        borderPane.setRight(textArea);

        // Label with the name of the logged-in employee
        Label bottomLabel = new Label("Ingelogde medewerker: " + Medewerker.huidigeMedewerker.getUsername());
        borderPane.setBottom(bottomLabel);
        BorderPane.setAlignment(bottomLabel, javafx.geometry.Pos.BOTTOM_LEFT);

        overzichtTab.setContent(borderPane);
        mainPane.getTabs().add(overzichtTab);
    }


    public void openBeheer() {
        Tab beheerTab = new Tab();
        beheerTab.setText("Beheer");
        beheerTab.setClosable(true);

        // Creates a ListView to display all products of type HuurItem
        ListView<HuurItem> producten = new ListView<>();
        ObservableList<HuurItem> items = FXCollections.observableArrayList(huurItems);
        producten.setItems(items);

        // Creates a TextArea for the details of all products
        TextArea textArea = new TextArea();
        textArea.setVisible(false);
        producten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Assuming HuurItem has a toString() method that returns a readable representation
                textArea.setText("Details van: " + newValue.toString());
                textArea.setVisible(true);
            }
        });

        // Creates a BorderPane to accommodate the ListView and TextArea
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(producten);
        borderPane.setRight(textArea);

        // Label with the name of the logged-in employee
        Label bottomLabel = new Label("Ingelogde medewerker: " + Medewerker.huidigeMedewerker.getUsername());
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
