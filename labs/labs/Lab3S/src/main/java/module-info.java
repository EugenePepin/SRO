module com.example.lab3s {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab3s to javafx.fxml;
    exports com.example.lab3s;
}