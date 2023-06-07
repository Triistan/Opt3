module com.example.opt3_tristan {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.opt3_tristan to javafx.fxml;
    exports com.example.opt3_tristan;
}