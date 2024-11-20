module com.example.lab1s {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab1s to javafx.fxml;
    exports com.example.lab1s;
}