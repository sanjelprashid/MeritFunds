package com.meritfunds.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    // Database configuration constants
    private static final String URL = "jdbc:mysql://localhost:3306/MeritFunds"; // DB name
    private static final String USER = "root"; // your DB username
    private static final String PASSWORD = ""; // your DB password (update if needed)

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
