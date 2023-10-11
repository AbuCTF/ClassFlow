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

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.Properties;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceManager {
    private String sessionInfoFilePath;
        
    public static class UserInfo {
        String userId;
        String userDepartment;
        String username;
        String subject;
        String sessionID;
        String attendanceTimestamp;

        UserInfo(String userId, String username, String userDepartment, String subject, String sessionID, String attendanceTimestamp) {
            this.userId = userId;
            this.username = username;
            this.userDepartment = userDepartment;
            this.subject = subject;
            this.sessionID = sessionID;
            this.attendanceTimestamp = attendanceTimestamp;
        }
    }

    public UserInfo collectUserInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your user department: ");
        String userDepartment = scanner.nextLine();

        System.out.print("Enter your user_id: ");
        String userId = scanner.nextLine();

        System.out.print("Enter the subject: ");
        String subject = scanner.nextLine();

        // Load the properties
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config/config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Config file not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        
        // Read session information from the session_info.txt file
        String sessionInfoFilePath = properties.getProperty("sessionInfoFilePath");
        String sessionInfo = readSessionInfoFromFile(sessionInfoFilePath);

        // Extract Session ID, Start Timestamp
        String[] sessionData = sessionInfo.split(":");
        String sessionID = sessionData[0];
        long attendanceTimestamp = Long.parseLong(sessionData[2]);
        
        // Convert attendanceTimestamp to String
        String attendanceTimestampString = String.valueOf(attendanceTimestamp);

        // Debug: Print the extracted values
        System.out.println("Extracted Session ID: " + sessionID);
        System.out.println("Extracted attendanceTimestamp: " + attendanceTimestampString);

        return new UserInfo(userId, username, userDepartment, subject, sessionID, attendanceTimestampString);
    }   


    public UserInfo verifyUserPassword(String username, String password) {
        try (Connection connection = DatabaseConnector.connect()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT user_id, password FROM users WHERE username = ?");
            selectStatement.setString(1, username);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");

                // Verify the provided password with the hashed password from the database
                if (BCrypt.checkpw(password, hashedPassword)) {
                    String userId = resultSet.getString("user_id");
                    return new UserInfo(userId, username, null, null, null, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertAttendanceRecord(UserInfo userInfo) {
        if (userInfo != null) {
            try (Connection connection = DatabaseConnector.connect();
                 PreparedStatement insertStatement = connection.prepareStatement(
                     "INSERT INTO attendance (user_id, username, user_department, subject, session_id, attendance_timestamp) VALUES (?, ?, ?, ?, ?, ?)")) {

                System.out.println("user_id: " + userInfo.userId);
                System.out.println("username: " + userInfo.username);
                System.out.println("user_department: " + userInfo.userDepartment);
                System.out.println("subject: " + userInfo.subject);
                System.out.println("session_id: " + userInfo.sessionID);
                System.out.println("attendance_timestamp: " + userInfo.attendanceTimestamp);

                // Now set the values in the correct order
                insertStatement.setString(1, userInfo.userId);
                insertStatement.setString(2, userInfo.username);

                if (userInfo.userDepartment != null) {
                    insertStatement.setString(3, userInfo.userDepartment);
                } else {
                    insertStatement.setNull(3, java.sql.Types.VARCHAR); // Handle null user_department
                }

                if (userInfo.subject != null) {
                    insertStatement.setString(4, userInfo.subject);
                } else {
                    insertStatement.setNull(4, java.sql.Types.VARCHAR); // Handle null subject
                }

                insertStatement.setString(5, userInfo.sessionID);

                if (userInfo.attendanceTimestamp != null) {
                    long attendanceTimestamp = Long.parseLong(userInfo.attendanceTimestamp);
                    Timestamp timestamp = new Timestamp(attendanceTimestamp);
                    insertStatement.setTimestamp(6, timestamp);
                } else {
                    insertStatement.setNull(6, java.sql.Types.TIMESTAMP); // Handle null attendance_timestamp
                }

                insertStatement.executeUpdate();
                connection.commit(); // Commit the transaction

                System.out.println("Attendance recorded in the database.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Password verification failed. Attendance not recorded.");
        }
    }
    
    private String readSessionInfoFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
