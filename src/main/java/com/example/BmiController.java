
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
import java.text.DecimalFormat;

import javafx.fxml.Initializable;

public class BmiController implements Initializable {
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

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private java.util.Map<String, String> localizedStrings;
    private Locale currentLocale;

    public void calculateBmi() {
        try {
            double weight = Double.parseDouble(weightInput.getText());
            double height = Double.parseDouble(heightInput.getText()) / 100.0;
            double bmi = weight / (height * height);
            DecimalFormat df = new DecimalFormat("#0.00");
            resultLabel.setText(localizedStrings.getOrDefault("result", "Your BMI is") + " " + df.format(bmi));
            BMIResultService.saveResult(weight, height * 100, bmi, currentLocale.getLanguage());
        } catch (NumberFormatException e) {
            resultLabel.setText(localizedStrings.getOrDefault("invalid", "Invalid input"));
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
                case "Vietnamese" -> new Locale("vn", "VN");
                default -> new Locale("en", "US");
            };
            setLanguage(locale);
        });
        setLanguage(new Locale("en", "US"));
        startClock();
    }

    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateClock()));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
        updateClock();
    }

    private void updateClock() {
        if (timeLabel != null && localizedStrings != null) {
            String timeText = localizedStrings.getOrDefault("localTime", "Time");
            String currentTime = LocalTime.now().format(timeFormatter);
            timeLabel.setText(timeText + ": " + currentTime);
        }
    }

    private void setLanguage(Locale locale) {
        localizedStrings = LocalizationService.getLocalizedStrings(locale);
        weightLabel.setText(localizedStrings.getOrDefault("weight", "Weight"));
        heightLabel.setText(localizedStrings.getOrDefault("height", "Height"));
        calculateButton.setText(localizedStrings.getOrDefault("calculate", "Calculate"));
        resultLabel.setText(localizedStrings.getOrDefault("result", "Result"));
        timeLabel.setText(localizedStrings.getOrDefault("localTime", "Time"));
        updateClock();
        currentLocale = locale;
    }
}
