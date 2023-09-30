/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abdur
 */
package com.mycompany.attendancetracker.user;

import com.mycompany.attendancetracker.data.DatabaseConnector;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistrationManager {

    public boolean registerUser(String username, String password, int maxUsers) {
        // Check if the maximum number of users has been reached
        if (countRegisteredUsers() >= maxUsers) {
            return false; // Registration limit reached
        }

        // Hash and salt the password using BCrypt
        String hashedPassword = hashAndSaltPassword(password);

        // Insert user data into the database
        if (insertUserIntoDatabase(username, hashedPassword)) {
            return true; // Registration successful
        }

        return false; // Registration failed
    }

    private int countRegisteredUsers() {
        try (Connection connection = DatabaseConnector.connect()) {
            String countQuery = "SELECT COUNT(*) FROM users";
            try (PreparedStatement countStatement = connection.prepareStatement(countQuery)) {
                var resultSet = countStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    private String hashAndSaltPassword(String password) {
        // Generate a salt (work factor of 12)
        String salt = BCrypt.gensalt(12);

        // Hash the password with the generated salt
        return BCrypt.hashpw(password, salt);
    }

    private boolean insertUserIntoDatabase(String username, String password) {
        try (Connection connection = DatabaseConnector.connect()) {
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, username);
                insertStatement.setString(2, password);
                int rowsAffected = insertStatement.executeUpdate();
                return rowsAffected == 1; // Registration successful if one row was affected
            }
        } catch (SQLException e) {
        }
        return false; // Registration failed
    }
}
