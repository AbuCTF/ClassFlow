/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author Abdur
 */

package com.mycompany.attendancetracker.app;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.mycompany.attendancetracker.qr.QRCodeGenerator;
import com.mycompany.attendancetracker.qr.QRCodeScanner;

import com.mycompany.attendancetracker.auth.AuthManager;
import com.mycompany.attendancetracker.auth.AuthHandler;
import com.mycompany.attendancetracker.user.UserLoginManager;
import com.mycompany.attendancetracker.user.UserRegistrationManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRegistrationManager registrationManager = new UserRegistrationManager();
        UserLoginManager loginManager = new UserLoginManager();
        String loggedInUsername = ""; // Store the logged-in username
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!AuthManager.isLoggedIn()) {
                // Display the main menu for unauthenticated users
                System.out.println("Welcome to ClassFlow - QR Based Attendance Tracking");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Delete User");
                System.out.println("4. Exit");
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
                        System.out.print("Enter email: ");
                        String regEmail = scanner.nextLine();
                        System.out.print("Enter role (user or student): ");
                        String regRole = scanner.nextLine(); // Allow the user to enter their role
                        
                        int maxUsers = 10;
                        
                        boolean registrationResult = registrationManager.registerUser(regUsername, regPassword, regEmail, regRole, maxUsers);
                        
                        if (registrationResult) {
                            System.out.println("User registered successfully!");
                        } else {
                            if (registrationManager.isCommonPassword(regPassword)) {
                                System.out.println("User registration failed. Common password detected.");
                            } else {
                                System.out.println("User registration failed. Registration limit reached or other error.");
                            }
                        }
                        break;
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
                            AuthManager.setAuthToken(AuthHandler.generateToken(loginUsername));
                            AuthManager.setLoggedIn(true);
                            System.out.println("Login successful!");
                            loggedInUsername = loginUsername; // Set the logged-in username
                        } else {
                            System.out.println("Login failed. Invalid username or password.");
                        }
                    }
                    case 3 -> {
                        // Delete User
                        System.out.println("Delete User");
                        System.out.print("Enter username to delete: ");
                        String usernameToDelete = scanner.nextLine();
                        System.out.print("Enter admin password: ");
                        String adminPassword = scanner.nextLine();
                        
                        boolean deletionResult = registrationManager.deleteUserWithConfirmation(usernameToDelete, adminPassword);
                        
                        if (deletionResult) {
                            System.out.println("User deleted successfully!");
                        } else {
                            System.out.println("User deletion failed. User not found or other error.");
                        }
                        break;
                    }
                    case 4 -> {
                        // Exit the program
                        System.out.println("Exiting Attendance Tracker.");
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice. Please select a valid option.");
                }
            } else {
                // Display the menu for logged-in users
                System.out.println("Logged in as: " + loggedInUsername);
                System.out.println("1. Access Authentication Token");
                System.out.println("2. Generate QR");
                System.out.println("3. Scan QR");
                System.out.println("4. Logout");
                System.out.print("Enter your choice: ");
                
                int loggedInChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                
                switch (loggedInChoice) {
                    case 1 -> {
                        // Access Authentication Token
                        String authToken = AuthManager.getAuthToken();
                        System.out.println("Accessing Authentication Token: " + authToken);
                    }
                    case 2 -> {
                        // Generate QR
                        // Add your QR code generation code here
                        System.out.println("Generating QR...");
                        QRCodeGenerator.main(new String[]{});
                    }
                    case 3 -> {
                        // Scan QR
                        // Add your QR code scanning code here
                        System.out.println("Scanning QR...");
                        QRCodeScanner.main(new String[]{});
                    }
                    case 4 -> {
                        // Logout
                        AuthManager.setAuthToken(null);
                        AuthManager.setLoggedIn(false);
                        System.out.println("Logged out.");
                    }
                    default -> System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        }
    }
}

