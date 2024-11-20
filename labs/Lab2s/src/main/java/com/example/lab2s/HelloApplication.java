package com.example.lab2s;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.setTitle("Рівняння Ейлера");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}