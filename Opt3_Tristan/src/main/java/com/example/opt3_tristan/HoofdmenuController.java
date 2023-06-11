package com.example.opt3_tristan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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


    public ObservableList<HuurItem> huurItems = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingelogdeMederwerkerLabel.setText(Medewerker.huidigeMedewerker.getUsername());

        for (Medewerker medewerker : Medewerker.IngelogdeMedewerkers) {
            ingelogdeGebruikersListView.getItems().add(medewerker);
        }

       //3 hardcoded personenauto's
        HuurItem auto1 = new Personenauto("Toyota", 1200, "Een comfortabele personenauto");
        huurItems.add(auto1);
        HuurItem auto2 = new Personenauto("Volvo", 2500, "Een veilige personenauto");
        huurItems.add(auto2);
        HuurItem auto3 = new Personenauto("Porsche", 1500, "Een vrij snelle personenauto");
        huurItems.add(auto3);

        //3 hardcoded vrachtwagens
        HuurItem vrachtwagen1 = new Vrachtwagen(20000, 18000,"Een wat kleinere vrachtwagen met 2 assen");
        huurItems.add(vrachtwagen1);
        HuurItem vrachtwagen2 = new Vrachtwagen(30000, 25000,"Een middelgrote vrachtwagen met 3 assen");
        huurItems.add(vrachtwagen2);
        HuurItem vrachtwagen3 = new Vrachtwagen(32000, 30000,"Een grote vrachtwagen met 4 assen");
        huurItems.add(vrachtwagen3);

        //3 hardcoded Boormachines
        HuurItem boormachine1 = new Boormachine("Makita","HP457DWE accu schroef en klopboormachine","een veelzijdige schroefboormachine die ook als klopboor kan functioneren");
        huurItems.add(boormachine1);
        HuurItem boormachine2 = new Boormachine("Bosch","EasyDrill","Een comfortabele en veelzijdige boormachine");
        huurItems.add(boormachine2);
        HuurItem boormachine3 = new Boormachine("Einhell","TE-CD","een krachtige alleskunner");
        huurItems.add(boormachine3);

    }
    public void wisselVanGebruiker(){
        Medewerker selectedItem = ingelogdeGebruikersListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            System.out.println("Gewisseld naar: "+selectedItem);
            Medewerker.huidigeMedewerker = ingelogdeGebruikersListView.getSelectionModel().getSelectedItem();
            ingelogdeMederwerkerLabel.setText(Medewerker.huidigeMedewerker.getUsername());
        }
    }

    public void openOverzicht() {

        Tab overzichtTab = new Tab();
        overzichtTab.setText("Overzicht");
        overzichtTab.setClosable(true);

        ListView<HuurItem> producten = new ListView<>();
        producten.setItems(huurItems);

        TextArea textArea = new TextArea();
        textArea.setVisible(false);
        producten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textArea.setText(newValue.getInformatie());
                textArea.setVisible(true);
            }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(producten);
        borderPane.setRight(textArea);

        Label bottomLabel = new Label("Ingelogde medewerker: " + Medewerker.huidigeMedewerker.getUsername());
        borderPane.setBottom(bottomLabel);
        BorderPane.setAlignment(bottomLabel, javafx.geometry.Pos.BOTTOM_LEFT);

        overzichtTab.setContent(borderPane);
        mainPane.getTabs().add(overzichtTab);
    }

    public void openBeheer() {
        Tab beheerTab = createTab("Beheer", huurItems);

        ComboBox<String> itemTypeComboBox = (ComboBox<String>) beheerTab.getContent().lookup(".combo-box");
        ListView<HuurItem> producten = (ListView<HuurItem>) beheerTab.getContent().lookup(".list-view");

        Label messageLabel = new Label();

        VBox creationBox = new VBox();

        setupItemTypeComboBoxAction(itemTypeComboBox, creationBox, messageLabel, producten);

        TextArea textArea = createTextArea(producten);

        BorderPane borderPane = createMainBorderPane(itemTypeComboBox, creationBox, messageLabel, producten, textArea);

        beheerTab.setContent(borderPane);
        mainPane.getTabs().add(beheerTab);
    }
    private Tab createTab(String tabText, ObservableList<HuurItem> items) {
        Tab tab = new Tab();
        tab.setText(tabText);
        tab.setClosable(true);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Personenauto", "Vrachtwagen", "Boormachine");

        ListView<HuurItem> listView = new ListView<>();
        listView.setItems(items);

        VBox vbox = new VBox(comboBox, listView);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);

        tab.setContent(borderPane);

        return tab;
    }


    private void setupItemTypeComboBoxAction(ComboBox<String> itemTypeComboBox, VBox creationBox, Label messageLabel, ListView<HuurItem> producten) {
        itemTypeComboBox.setPromptText("Toevoegen");
        itemTypeComboBox.setOnAction(e -> {
            creationBox.getChildren().clear();
            messageLabel.setText("");
            switch (itemTypeComboBox.getValue()) {
                case "Personenauto":
                    new PersonenautoCreation(creationBox, messageLabel, producten, huurItems).setupItemCreation();
                    break;
                case "Vrachtwagen":
                    new VrachtwagenCreation(creationBox, messageLabel, producten, huurItems).setupItemCreation();
                    break;
                case "Boormachine":
                    new BoormachineCreation(creationBox, messageLabel, producten, huurItems).setupItemCreation();
                    break;
            }
        });
    }
    private TextArea createTextArea(ListView<HuurItem> producten) {
        TextArea textArea = new TextArea();
        textArea.setVisible(false);
        producten.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textArea.setText(newValue.getInformatie());
                textArea.setVisible(true);
            }
        });
        return textArea;
    }
    private BorderPane createMainBorderPane(ComboBox<String> itemTypeComboBox, VBox creationBox, Label messageLabel, ListView<HuurItem> producten, TextArea textArea) {
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(producten);
        borderPane.setRight(textArea);
        borderPane.setTop(new VBox(itemTypeComboBox, creationBox, messageLabel));
        Label bottomLabel = new Label("Ingelogde medewerker: " + Medewerker.huidigeMedewerker.getUsername());
        borderPane.setBottom(bottomLabel);
        BorderPane.setAlignment(bottomLabel, javafx.geometry.Pos.BOTTOM_LEFT);
        return borderPane;
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
