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

//            for (int i = mainPane.getTabs().size() - 1; i >= 0; i--) {
//                Tab tab = mainPane.getTabs().get(i);
//                if (!tab.getText().equals("Hoofdmenu")) {
//                    mainPane.getTabs().remove(i);
//                }
//            }
        }
    }

    public void openOverzicht() {
        // Creates a new tab in the tabpane
        Tab overzichtTab = new Tab();
        overzichtTab.setText("Overzicht");
        overzichtTab.setClosable(true);

        // Creates a ListView to display all products
        ListView<HuurItem> producten = new ListView<>();
        producten.setItems(huurItems);

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

   //-------------------code voor beheervenster---------------------------------
    public void openBeheer() {
        Tab beheerTab = createBeheerTab();

        ComboBox<String> itemTypeComboBox = createItemTypeComboBox();

        ListView<HuurItem> producten = createProductListView();

        Label messageLabel = new Label();

        VBox creationBox = new VBox();

        setupItemTypeComboBoxAction(itemTypeComboBox, creationBox, messageLabel, producten);

        TextArea textArea = createTextArea(producten);

        BorderPane borderPane = createMainBorderPane(itemTypeComboBox, creationBox, messageLabel, producten, textArea);

        beheerTab.setContent(borderPane);
        mainPane.getTabs().add(beheerTab);
    }
    private Tab createBeheerTab() {
        Tab beheerTab = new Tab();
        beheerTab.setText("Beheer");
        beheerTab.setClosable(true);
        return beheerTab;
    }
    private ComboBox<String> createItemTypeComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Personenauto", "Vrachtwagen", "Boormachine");
        return comboBox;
    }
    private ListView<HuurItem> createProductListView() {
        ListView<HuurItem> listView = new ListView<>();
        listView.setItems(huurItems);
        return listView;
    }
    private void setupItemTypeComboBoxAction(ComboBox<String> itemTypeComboBox, VBox creationBox, Label messageLabel, ListView<HuurItem> producten) {
        itemTypeComboBox.setOnAction(e -> {
            creationBox.getChildren().clear();
            messageLabel.setText("");
            switch (itemTypeComboBox.getValue()) {
                case "Personenauto":
                    setupPersonenautoCreation(creationBox, messageLabel, producten);
                    break;
                case "Vrachtwagen":
                    setupVrachtwagenCreation(creationBox, messageLabel, producten);
                    break;
                case "Boormachine":
                    setupBoormachineCreation(creationBox, messageLabel, producten);
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

    private void setupPersonenautoCreation(VBox creationBox, Label messageLabel, ListView<HuurItem> producten) {
        TextField merkField = new TextField();
                    merkField.setPromptText("Merk");
                    TextField gewichtField = new TextField();
                    gewichtField.setPromptText("Gewicht");
                    TextField beschrijvingField = new TextField();
                    beschrijvingField.setPromptText("Beschrijving");
                    Button createPersonenautoButton = new Button("Create Personenauto");
                    createPersonenautoButton.setOnAction(event -> {
                        try {
                            String merk = merkField.getText();
                            double gewicht = Double.parseDouble(gewichtField.getText());
                            String beschrijving = beschrijvingField.getText();
                            HuurItem newItem = new Personenauto(merk, gewicht, beschrijving);
                            huurItems.add(newItem);
                            producten.setItems(FXCollections.observableArrayList(huurItems));
                            messageLabel.setText("Personenauto aangemaakt!");
                        } catch (NumberFormatException ex) {
                            messageLabel.setText("Error: Zorg dat je getallen gebruikt bij de gewichten");
                        }
                    });
                    creationBox.getChildren().addAll(merkField, gewichtField, beschrijvingField, createPersonenautoButton);

    }
    private void setupVrachtwagenCreation(VBox creationBox, Label messageLabel, ListView<HuurItem> producten) {
        TextField maxGewichtField = new TextField();
                    maxGewichtField.setPromptText("Max Gewicht");
                    TextField laadGewichtField = new TextField();
                    laadGewichtField.setPromptText("Laad Gewicht");
                    TextField vrachtwagenBeschrijvingField = new TextField();
                    vrachtwagenBeschrijvingField.setPromptText("Beschrijving");
                    Button createVrachtwagenButton = new Button("Maak Vrachtwagen");
                    createVrachtwagenButton.setOnAction(event -> {
                        try {
                            double maxGewicht = Double.parseDouble(maxGewichtField.getText());
                            double laadGewicht = Double.parseDouble(laadGewichtField.getText());
                            String beschrijving = vrachtwagenBeschrijvingField.getText();
                            HuurItem newItem = new Vrachtwagen(maxGewicht, laadGewicht, beschrijving);
                            huurItems.add(newItem);
                            producten.setItems(FXCollections.observableArrayList(huurItems));
                            messageLabel.setText("Vrachtwagen aangemaakt!");
                        } catch (NumberFormatException ex) {
                            messageLabel.setText("Error: Zorg dat je getallen gebruikt bij de gewichten");
                        }
                    });
                    creationBox.getChildren().addAll(maxGewichtField, laadGewichtField, vrachtwagenBeschrijvingField, createVrachtwagenButton);

    }
    private void setupBoormachineCreation(VBox creationBox, Label messageLabel, ListView<HuurItem> producten) {
        TextField merkBoormachineField = new TextField();
                    merkBoormachineField.setPromptText("Merk");
                    TextField modelBoormachineField = new TextField();
                    modelBoormachineField.setPromptText("Model");
                    TextField boormachineBeschrijvingField = new TextField();
                    boormachineBeschrijvingField.setPromptText("Beschrijving");
                    Button createBoormachineButton = new Button("Create Boormachine");
                    createBoormachineButton.setOnAction(event -> {
                        try {
                            String merk = merkBoormachineField.getText();
                            String model = modelBoormachineField.getText();
                            String beschrijving = boormachineBeschrijvingField.getText();
                            HuurItem newItem = new Boormachine(merk, model, beschrijving);
                            huurItems.add(newItem);
                            producten.setItems(FXCollections.observableArrayList(huurItems));
                            messageLabel.setText("Boormachine aangemaakt!");
                        } catch (NumberFormatException ex) {
                            messageLabel.setText("Error: Zorg dat je getallen gebruikt bij de gewichten");
                        }
                    });
                    creationBox.getChildren().addAll(merkBoormachineField, modelBoormachineField, boormachineBeschrijvingField, createBoormachineButton);

    }
    //---------------------------------------------------------------------------------------------------------

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
