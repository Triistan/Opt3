package com.example.opt3_tristan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;

public abstract class ItemCreationTemplate {
    protected VBox creationBox;
    protected Label messageLabel;
    protected ListView<HuurItem> producten;
    protected List<HuurItem> huurItems;


    public ItemCreationTemplate(VBox creationBox, Label messageLabel, ListView<HuurItem> producten, List<HuurItem> huurItems) {
        this.huurItems = huurItems;
        this.creationBox = creationBox;
        this.messageLabel = messageLabel;
        this.producten = producten;
    }


    public final void setupItemCreation() {
        List<Node> inputFields = getItemInputFields();
        Button createButton = new Button(getButtonText());

        createButton.setOnAction(event -> {
            try {
                HuurItem newItem = createItem();
                huurItems.add(newItem);
                producten.setItems(FXCollections.observableArrayList(huurItems));
                messageLabel.setText(getSuccessMessage());
            } catch (NumberFormatException ex) {
                messageLabel.setText("Error: Zorg dat je getallen gebruikt bij de gewichten");
            }
        });

        creationBox.getChildren().addAll(inputFields);
        creationBox.getChildren().add(createButton);
    }

    protected abstract HuurItem createItem();
    protected abstract List<Node> getItemInputFields();
    protected abstract String getButtonText();
    protected abstract String getSuccessMessage();
}
