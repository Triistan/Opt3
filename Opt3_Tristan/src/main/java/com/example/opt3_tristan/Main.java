package com.example.opt3_tristan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 760, 570);
        stage.setTitle("Rent-a-Thing");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();

        HuurItemFactory autoFabriek = new PersonenautoFactory("Toyota", 1200, "Een comfortabele personenauto");
        HuurItem auto = autoFabriek.maakHuurItem();

        System.out.println("Personenauto - Huurprijs per dag: " + auto.getHuurPrijsPerDag());
        System.out.println("Personenauto - Verzekeringskosten per dag: " + auto.getVerzekeringsKostenPerDag());
        System.out.println("Personenauto - Beschrijving: " + auto.getBeschrijving());

        HuurItemFactory vrachtwagenFabriek = new VrachtwagenFactory(3000, 8000, "Een grote vrachtwagen met veel laadvermogen");
        HuurItem vrachtwagen = vrachtwagenFabriek.maakHuurItem();

        System.out.println("Vrachtwagen - Huurprijs per dag: " + vrachtwagen.getHuurPrijsPerDag());
        System.out.println("Vrachtwagen - Verzekeringskosten per dag: " + vrachtwagen.getVerzekeringsKostenPerDag());
        System.out.println("Vrachtwagen - Beschrijving: " + vrachtwagen.getBeschrijving());

        HuurItemFactory boormachineFabriek = new BoormachineFactory("Bosch", "X123", "Een krachtige boormachine van Bosch");
        HuurItem boormachine = boormachineFabriek.maakHuurItem();

        System.out.println("Boormachine - Huurprijs per dag: " + boormachine.getHuurPrijsPerDag());
        System.out.println("Boormachine - Verzekeringskosten per dag: " + boormachine.getVerzekeringsKostenPerDag());
        System.out.println("Boormachine - Beschrijving: " + boormachine.getBeschrijving());

    }
}