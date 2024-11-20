module com.example.lab2s {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab2s to javafx.fxml;
    exports com.example.lab2s;
}