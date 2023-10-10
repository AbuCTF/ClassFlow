/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abdur
 */

package com.mycompany.attendancetracker.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/userdata"; // Replace with your database URL
    private static final String DB_USER = "postgres"; // Replace with your database username
    private static final String DB_PASSWORD = "P@ss123!"; // Replace with your database password

    // Establish a database connection
    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);  // Disable auto-commit
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    // Commit and close the database connection
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.commit(); // Commit any pending changes
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Rollback and close the database connection
    public static void rollbackAndClose(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback(); // Roll back changes
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

