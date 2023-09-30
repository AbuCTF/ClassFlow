/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Abdur
 */

package com.mycompany.attendancetracker.app;

import com.mycompany.attendancetracker.data.DatabaseHandler;
import com.mycompany.attendancetracker.user.UserLoginManager;
import com.mycompany.attendancetracker.user.UserRegistrationManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        UserRegistrationManager registrationManager = new UserRegistrationManager();
        UserLoginManager loginManager = new UserLoginManager();

        try {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Welcome to Attendance Tracker");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1 -> {
                        // User Registration
                        System.out.println("User Registration");
                        System.out.print("Enter username: ");
                        String regUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String regPassword = scanner.nextLine();
                        
                        int maxUsers = 10; // Set your registration limit here

                        boolean registrationResult = registrationManager.registerUser(regUsername, regPassword, maxUsers);

                        if (registrationResult) {
                            System.out.println("User registered successfully!");
                        } else {
                            System.out.println("User registration failed. Registration limit reached or other error.");
                        }
                    }

                    case 2 -> {
                        // User Login
                        System.out.println("User Login");
                        System.out.print("Enter username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String loginPassword = scanner.nextLine();

                        boolean loginResult = loginManager.loginUser(loginUsername, loginPassword);

                        if (loginResult) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Login failed. Invalid username or password.");
                        }
                    }

                    case 3 -> {
                        // Exit the program
                        System.out.println("Exiting Attendance Tracker.");
                        databaseHandler.close();
                        scanner.close();
                        System.exit(0);
                    }

                    default -> System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
