package com.example.lab2s;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField paramBField;

    @FXML
    private LineChart<Number, Number> chartX;

    @FXML
    private LineChart<Number, Number> chartPhase;

    @FXML
    private void calculate() {
        double b;
        try {
            b = Double.parseDouble(paramBField.getText());
        } catch (NumberFormatException e) {
            paramBField.setText("Неправильний ввід");
            return;
        }

        double h = 0.005;
        double x = 1.0, v = 0.0;
        int steps = 1000;

        XYChart.Series<Number, Number> seriesX = new XYChart.Series<>();
        seriesX.setName("x(t) при b = " + b);

        XYChart.Series<Number, Number> seriesPhase = new XYChart.Series<>();
        seriesPhase.setName("Фазова траєкторія при b = " + b);

        for (int i = 0; i < steps; i++) {
            double t = i * h;
            double newX = x + h * v;
            double newV = v + h * (b * (1 - x * x) * v - x);

            seriesX.getData().add(new XYChart.Data<>(t, x));
            seriesPhase.getData().add(new XYChart.Data<>(x, v));

            x = newX;
            v = newV;
        }

        chartX.getData().clear();
        chartX.getData().add(seriesX);

        chartPhase.getData().clear();
        chartPhase.getData().add(seriesPhase);
    }
}