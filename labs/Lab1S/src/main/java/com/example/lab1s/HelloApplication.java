package com.example.lab1s;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    AirplaneRecognitionSystem system = new AirplaneRecognitionSystem();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Система розпізнавання літаків");

        initializeData();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Поля для введення характеристик літака
        Label weightLabel = new Label("Вага літака (кг):");
        TextField weightInput = new TextField();
        grid.add(weightLabel, 0, 0);
        grid.add(weightInput, 1, 0);

        Label speedLabel = new Label("Швидкість літака (км/год):");
        TextField speedInput = new TextField();
        grid.add(speedLabel, 0, 1);
        grid.add(speedInput, 1, 1);

        Label wingspanLabel = new Label("Ширина крил (м):");
        TextField wingspanInput = new TextField();
        grid.add(wingspanLabel, 0, 2);
        grid.add(wingspanInput, 1, 2);



        Button classifyButton = new Button("Класифікувати");
        grid.add(classifyButton, 1, 5);

        Label resultLabel = new Label();
        grid.add(resultLabel, 1, 6);


        classifyButton.setOnAction(e -> {
            try {
                double weight = Double.parseDouble(weightInput.getText());
                double speed = Double.parseDouble(speedInput.getText());
                double wingspan = Double.parseDouble(wingspanInput.getText());

                Airplane unknownAirplane = new Airplane(weight, speed, wingspan, "Unknown");

                String category = system.classifyAirplane(unknownAirplane);
                StringBuilder distanceInfo = new StringBuilder("Літак належить до класу: " + category + "\n");

                for (Airplane airplane : system.airplanes) {
                    double distance = unknownAirplane.distanceTo(airplane);
                    distanceInfo.append("Класифікація до ").append(airplane.category)
                            .append(": ").append(String.format("%.2f", distance)).append(" од.\n");
                }

                resultLabel.setText(distanceInfo.toString());
            } catch (NumberFormatException ex) {
                resultLabel.setText("Помилка: введіть числові значення!");
            }
        });





        Scene scene = new Scene(grid, 430, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeData() {
        system.addAirplane(new Airplane(20000, 900, 35, "Цивільний"));
        system.addAirplane(new Airplane(10000, 1200, 15, "Швидкісний"));
        system.addAirplane(new Airplane(15000, 800, 20, "Винищувач"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}