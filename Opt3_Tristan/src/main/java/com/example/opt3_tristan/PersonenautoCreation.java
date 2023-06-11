package com.example.opt3_tristan;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class PersonenautoCreation extends ItemCreationTemplate {

    private TextField merkField;
    private TextField gewichtField;
    private TextField beschrijvingField;

    public PersonenautoCreation(VBox creationBox, Label messageLabel, ListView<HuurItem> producten, List<HuurItem> huurItems) {
        super(creationBox, messageLabel, producten, huurItems);
    }


    @Override
    protected HuurItem createItem() {
        String merk = merkField.getText();
        double gewicht = Double.parseDouble(gewichtField.getText());
        String beschrijving = beschrijvingField.getText();
        return new Personenauto(merk, gewicht, beschrijving);
    }

    @Override
    protected List<Node> getItemInputFields() {
        merkField = new TextField();
        merkField.setPromptText("Merk");

        gewichtField = new TextField();
        gewichtField.setPromptText("Gewicht");

        beschrijvingField = new TextField();
        beschrijvingField.setPromptText("Beschrijving");

        return Arrays.asList(merkField, gewichtField, beschrijvingField);
    }

    @Override
    protected String getButtonText() {
        return "Create Personenauto";
    }

    @Override
    protected String getSuccessMessage() {
        return "Personenauto aangemaakt!";
    }
}
