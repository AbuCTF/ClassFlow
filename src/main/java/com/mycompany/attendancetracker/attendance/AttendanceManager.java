/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abdur
 */
package com.mycompany.attendancetracker.attendance;

import com.mycompany.attendancetracker.data.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AttendanceManager {

    public static class UserInfo {
        String userId;
        String userDepartment;
        private String username;

        UserInfo(String userId, String userDepartment) {
            this.userId = userId;
            this.userDepartment = userDepartment;
        }
    }

    private static final String SELECT_USER_INFO_QUERY = "SELECT user_id, user_department FROM users WHERE username = ?";

    public UserInfo verifyUserPassword(String username, String password) {
        try (Connection connection = DatabaseConnector.connect()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT user_id, user_department, password FROM users WHERE username = ?");
            selectStatement.setString(1, username);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                if (password.equals(storedPassword)) {
                    String userId = resultSet.getString("user_id");
                    String userDepartment = resultSet.getString("user_department");
                    return new UserInfo(userId, userDepartment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertAttendanceRecord(UserInfo userInfo) {
        if (userInfo != null) {
            // You have the user's information, proceed to insert attendance
            // Automatically set the session information
            String sessionInfo = "Session123";

            try (Connection connection = DatabaseConnector.connect();
                 PreparedStatement insertStatement = connection.prepareStatement(
                         "INSERT INTO attendance (user_id, username, user_department, session_info) VALUES (?, ?, ?, ?)")) {

                insertStatement.setString(1, userInfo.userId);
                insertStatement.setString(2, userInfo.username);
                insertStatement.setString(3, userInfo.userDepartment);
                insertStatement.setString(4, sessionInfo);
                insertStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Attendance recorded in the database.");
        } else {
            System.out.println("Password verification failed. Attendance not recorded.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Create an instance of AttendanceManager
        AttendanceManager attendanceManager = new AttendanceManager();

        // Verify the user's password and get their information
        UserInfo userInfo = attendanceManager.verifyUserPassword(username, password);

        // Call the insertAttendanceRecord method with the user's information
        attendanceManager.insertAttendanceRecord(userInfo);
    }
}

