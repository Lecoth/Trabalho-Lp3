module com.example.trabalholp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics; //as vezes do nada aparece uma linha com com.trabalholp3, só apagar e seguir com a vida.


    opens trabalholp3 to javafx.fxml;
    exports trabalholp3;
    exports controller;
    opens controller to javafx.fxml;
}