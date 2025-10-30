package com.example;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.ResourceBundle;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BmiCalculatorTest {
    @Test
    void testEnglishBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", new Locale("en", "US"));
        assertEquals("Weight (kg):", rb.getString("weightLabel"));
    }

    @Test
    void testFinnishBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", new Locale("fr", "FR"));
        assertEquals("Poids (kg) :", rb.getString("weightLabel"));
    }

    @Test
    void testSwedishBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", new Locale("ur", "UR"));
        assertEquals("وزن:", rb.getString("weightLabel"));
    }

    @Test
    void testJapaneseBundle() {
        ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle", new Locale("vi", "VI"));
        assertEquals("Cân nặng:", rb.getString("weightLabel"));
    }
}
