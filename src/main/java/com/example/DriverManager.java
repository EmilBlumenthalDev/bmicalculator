package com.example;
import java.sql.*;

public class DriverManager {
    private static DriverManager instance;

    private DriverManager() {}

    public static synchronized DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public static Connection getConnection(String url, String user, String password) throws SQLException {
        return java.sql.DriverManager.getConnection(url, user, password);
    }
}
