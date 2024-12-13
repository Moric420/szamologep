package com.example.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloController {



    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");


        TextField number1Field = new TextField();
        TextField number2Field = new TextField();
        TextField resultField = new TextField();
        resultField.setEditable(false);
        resultField.setFont(new Font(16));


        Button addButton = new Button("+");
        Button subtractButton = new Button("-");
        Button multiplyButton = new Button("*");
        Button divideButton = new Button("/");
        Button moduloButton = new Button("%");


        addButton.setOnAction(e -> calculate(number1Field, number2Field, resultField, "+"));
        subtractButton.setOnAction(e -> calculate(number1Field, number2Field, resultField, "-"));
        multiplyButton.setOnAction(e -> calculate(number1Field, number2Field, resultField, "*"));
        divideButton.setOnAction(e -> calculate(number1Field, number2Field, resultField, "/"));
        moduloButton.setOnAction(e -> calculate(number1Field, number2Field, resultField, "%"));


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));


        gridPane.add(number1Field, 0, 0);
        gridPane.add(number2Field, 2, 0);

        gridPane.add(addButton, 0, 1);
        gridPane.add(subtractButton, 1, 1);
        gridPane.add(multiplyButton, 2, 1);
        gridPane.add(divideButton, 3, 1);
        gridPane.add(moduloButton, 4, 1);

        gridPane.add(resultField, 1, 2, 3, 1);


        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculate(TextField number1Field, TextField number2Field, TextField resultField, String operator) {
        try {

            if (number1Field.getText().isEmpty() || number2Field.getText().isEmpty()) {
                showAlert("All fields are required.");
                return;
            }

            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());
            double result = 0;


            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        showAlert("Division by zero is not allowed.");
                        return;
                    }
                    result = Math.round((num1 / num2) * 100.0) / 100.0;
                    break;
                case "%":
                    if (num2 == 0) {
                        showAlert("Modulo by zero is not allowed.");
                        return;
                    }
                    result = num1 % num2;
                    break;
            }


            resultField.setText(String.valueOf(result));

        } catch (NumberFormatException e) {
            showAlert("Please enter valid numbers.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}