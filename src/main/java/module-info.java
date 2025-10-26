module com.example.trabalholp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens trabalholp3 to javafx.fxml;
    exports trabalholp3;
    exports controller;
    opens controller to javafx.fxml;
}