/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Abdur
 */

package com.mycompany.attendancetracker.user;

import com.mycompany.attendancetracker.auth.AuthHandler;
import com.mycompany.attendancetracker.auth.AuthManager;
import com.mycompany.attendancetracker.data.DatabaseConnector;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginManager {

    public boolean loginUser(String username, String password) {
        // Retrieve the hashed password from the database for the given username
        String hashedPasswordFromDB = getHashedPasswordFromDatabase(username);

        // Check if the entered password matches the hashed password from the database
        if (hashedPasswordFromDB != null && BCrypt.checkpw(password, hashedPasswordFromDB)) {
            // Upon successful login, generate an authentication token.
            String authToken = AuthHandler.generateToken(username);

            // Transfer control to AuthManager to set the token and update login status.
            AuthManager.setAuthToken(authToken);
            AuthManager.setLoggedIn(true);

            return true; // Login successful
        }

        return false; // Login failed (username not found or incorrect password)
    }

    private String getHashedPasswordFromDatabase(String username) {
        try (Connection connection = DatabaseConnector.connect()) {
            String selectQuery = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, username);
                ResultSet resultSet = selectStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found in the database
    }
}


