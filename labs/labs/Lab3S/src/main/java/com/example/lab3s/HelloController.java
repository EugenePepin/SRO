package com.example.lab3s;


import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField muField;

    @FXML
    private LineChart<Number, Number> chart;

    private static final double TIME_STEP = 0.01;
    private static final double T_MAX = 100.0;

    @FXML
    private void startSimulation() {
        chart.getData().clear();

        double mu;
        try {
            mu = Double.parseDouble(muField.getText());
        } catch (NumberFormatException e) {
            muField.setText("Невірний вхід");
            return;
        }

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Коливання x");

        double x = 1.0;
        double y = 0.0;
        double prevMinX = Double.MAX_VALUE;
        double lastMinTime = 0;
        boolean foundFirstMinimum = false;
        double period = 0;

        for (double t = 0; t < T_MAX; t += TIME_STEP) {
            series.getData().add(new XYChart.Data<>(t, x));

            double[] newValues = trapezoidalStep(x, y, mu, TIME_STEP);
            x = newValues[0];
            y = newValues[1];


            if (x < prevMinX) {
                prevMinX = x;
                lastMinTime = t;
                foundFirstMinimum = true;
            } else if (foundFirstMinimum && x > prevMinX + 0.01) {
                period = t - lastMinTime;
                System.out.printf("Мінімум знайдено на t=%.2f, період коливань: %.2f\n", t, period);
                break;
            }
        }

        chart.getData().add(series);
    }

    private double[] trapezoidalStep(double x, double y, double mu, double h) {

        double dxdt1 = y;
        double dydt1 = mu * (1 - x * x) * y - x;


        double xTemp = x + h * dxdt1;
        double yTemp = y + h * dydt1;


        double dxdt2 = yTemp;
        double dydt2 = mu * (1 - xTemp * xTemp) * yTemp - xTemp;

        double newX = x + (h / 2) * (dxdt1 + dxdt2);
        double newY = y + (h / 2) * (dydt1 + dydt2);

        return new double[]{newX, newY};
    }
}