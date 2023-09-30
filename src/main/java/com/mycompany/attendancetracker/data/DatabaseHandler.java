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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
    private final Connection connection;

    public DatabaseHandler() {
        connection = DatabaseConnector.connect();
    }

    public void close() {
        DatabaseConnector.close(connection);
    }

    // Insert a new attendance record
    public boolean insertAttendanceRecord(String userId, String dateTime, String qrCodeData) {
        try {
            String insertQuery = "INSERT INTO attendance (user_id, date_time, qr_code_data) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, userId);
            statement.setString(2, dateTime);
            statement.setString(3, qrCodeData);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected == 1; // Insert successful if one row was affected
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Insert failed
    }

    // Retrieve attendance records for a user
    public ResultSet getAttendanceRecordsForUser(String userId) {
        try {
            String selectQuery = "SELECT * FROM attendance WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setString(1, userId);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Other database operations can be added here

}

