package com.example.opt3_tristan;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class BoormachineCreation extends ItemCreationTemplate {

    private TextField merkField;
    private TextField typeField;
    private TextField beschrijvingField;

    public BoormachineCreation(VBox creationBox, Label messageLabel, ListView<HuurItem> producten, List<HuurItem> huurItems) {
        super(creationBox, messageLabel, producten, huurItems);
    }

    @Override
    protected HuurItem createItem() {
        String merk = merkField.getText();
        String type = typeField.getText();
        String beschrijving = beschrijvingField.getText();
        return new Boormachine(merk, type, beschrijving);
    }

    @Override
    protected List<Node> getItemInputFields() {
        merkField = new TextField();
        merkField.setPromptText("Merk");

        typeField = new TextField();
        typeField.setPromptText("Type");

        beschrijvingField = new TextField();
        beschrijvingField.setPromptText("Beschrijving");

        return Arrays.asList(merkField, typeField, beschrijvingField);
    }

    @Override
    protected String getButtonText() {
        return "Create Boormachine";
    }

    @Override
    protected String getSuccessMessage() {
        return "Boormachine aangemaakt!";
    }
}
