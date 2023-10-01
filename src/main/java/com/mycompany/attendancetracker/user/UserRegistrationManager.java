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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserRegistrationManager {

    public boolean registerUser(String username, String password, String email, int maxUsers) {
        if (isUsernameValid(username) && isPasswordValid(password) && isEmailValid(email)) {
            if (countRegisteredUsers() >= maxUsers) {
                return false; // Registration limit reached
            }

            if (isCommonPassword(password)) {
                return false; // Common password, registration failed
            }

            String hashedPassword = hashAndSaltPassword(password);

            if (insertUserIntoDatabase(username, hashedPassword, email)) {
                return true; // Registration successful
            }
        }

        return false; // Registration failed
    }

    private boolean isUsernameValid(String username) {
        return Pattern.matches("^[a-zA-Z]+$", username);
    }

    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false; // Password is too short
        }

        if (!Pattern.matches(".*[A-Z].*", password) ||
            !Pattern.matches(".*[a-z].*", password) ||
            !Pattern.matches(".*\\d.*", password) ||
            !Pattern.matches(".*[!@#$%^&*()].*", password) ||
            isCommonPassword(password)) {
            return false;
        }

        return true;
    }

    private boolean isEmailValid(String email) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email);
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
            e.printStackTrace();
        }
        return 0;
    }

    private String hashAndSaltPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    private boolean insertUserIntoDatabase(String username, String password, String email) {
        try (Connection connection = DatabaseConnector.connect()) {
            String insertQuery = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, username);
                insertStatement.setString(2, password);
                insertStatement.setString(3, email);
                int rowsAffected = insertStatement.executeUpdate();
                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isCommonPassword(String password) {
    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Documents2\\Drops\\commonPasswords100k.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().equalsIgnoreCase(password)) {
                return true; // Password is in the common password list
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false; // Password is not in the common password list
}

}
