
package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.net.URL;

import javafx.fxml.Initializable;

public class BmiController implements Initializable {
    private ResourceBundle rb;
    @FXML
    private Label timeLabel;
    @FXML
    private Label weightLabel;
    @FXML
    private Label heightLabel;
    @FXML
    private TextField weightInput;
    @FXML
    private TextField heightInput;
    @FXML
    private Button calculateButton;
    @FXML
    private VBox itemsContainer;
    @FXML
    private ComboBox<String> languageSelector;
    @FXML
    private Label resultLabel;

    public void calculateBmi() {
        try {
            double weight = Double.parseDouble(weightInput.getText());
            double height = Double.parseDouble(heightInput.getText()) / 100.0;
            double bmi = weight / (height * height);
            resultLabel.setText(rb.getString("labelResult") + " " + String.format("%.2f", bmi));
        } catch (NumberFormatException e) {
            resultLabel.setText(rb.getString("errorInvalidInput"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        languageSelector.getItems().addAll("English", "French", "Urdu", "Vietnamese");
        languageSelector.setValue("English");

        languageSelector.setOnAction(e -> {
            Locale locale = switch (languageSelector.getValue()) {
                case "English" -> new Locale("en", "US");
                case "French" -> new Locale("fr", "FR");
                case "Urdu" -> new Locale("ur", "UR");
                case "Vietnamese" -> new Locale("vi", "VI");
                default -> new Locale("en", "US");
            };
            setLanguage(locale);
        });
        setLanguage(new Locale("en", "US"));
        startClock();
    }

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (timeLabel != null) {
                timeLabel.setText("Time: " + LocalTime.now().format(timeFormatter));
            }
        }));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    private void setLanguage(Locale locale) {
        rb = ResourceBundle.getBundle("MessagesBundle", locale);
        weightLabel.setText(rb.getString("weightLabel"));
        heightLabel.setText(rb.getString("heightLabel"));
        calculateButton.setText(rb.getString("buttonCalculate"));
        resultLabel.setText(rb.getString("labelResult"));
    }
}
