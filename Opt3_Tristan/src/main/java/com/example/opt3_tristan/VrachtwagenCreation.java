package com.example.opt3_tristan;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class VrachtwagenCreation extends ItemCreationTemplate {

    private TextField laadGewichtField;
    private TextField gewichtField;
    private TextField beschrijvingField;

    public VrachtwagenCreation(VBox creationBox, Label messageLabel, ListView<HuurItem> producten, List<HuurItem> huurItems) {
        super(creationBox, messageLabel, producten, huurItems);
    }


    @Override
    protected HuurItem createItem() {
        double laadgewicht = Double.parseDouble(laadGewichtField.getText());
        double gewicht = Double.parseDouble(gewichtField.getText());
        String beschrijving = beschrijvingField.getText();
        return new Vrachtwagen(laadgewicht, gewicht, beschrijving);
    }

    @Override
    protected List<Node> getItemInputFields() {
        laadGewichtField = new TextField();
        laadGewichtField.setPromptText("Laadgewicht");

        gewichtField = new TextField();
        gewichtField.setPromptText("Gewicht");

        beschrijvingField = new TextField();
        beschrijvingField.setPromptText("Beschrijving");

        return Arrays.asList(laadGewichtField, gewichtField, beschrijvingField);
    }

    @Override
    protected String getButtonText() {
        return "Create Vrachtwagen";
    }

    @Override
    protected String getSuccessMessage() {
        return "Vrachtwagen aangemaakt!";
    }
}
