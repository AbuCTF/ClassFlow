/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Abdur
 */
/*
package trial.exp;

    package com.mycompany.attendancetracker.app;

    import com.mycompany.attendancetracker.auth.AuthManager;
    import com.mycompany.attendancetracker.user.UserLoginManager;
    import com.mycompany.attendancetracker.user.UserRegistrationManager;

    import java.util.InputMismatchException;
    import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {
            UserRegistrationManager registrationManager = new UserRegistrationManager();
            UserLoginManager loginManager = new UserLoginManager();
            boolean isLoggedIn = false;
            String loggedInUsername = "";

            try {
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.println("Welcome to ClassFlow - QR Based Attendance Tracking");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Delete User");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");

                    try {
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("User Registration");
                                System.out.print("Enter username: ");
                                String regUsername = scanner.nextLine();
                                System.out.print("Enter password: ");
                                String regPassword = scanner.nextLine();
                                System.out.print("Enter email: ");
                                String regEmail = scanner.nextLine();

                                int maxUsers = 10;

                                boolean registrationResult = registrationManager.registerUser(regUsername, regPassword, regEmail, maxUsers);

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

                            case 2:
                                System.out.println("User Login");
                                System.out.print("Enter username: ");
                                String loginUsername = scanner.nextLine();
                                System.out.print("Enter password: ");
                                String loginPassword = scanner.nextLine();

                                boolean loginResult = loginManager.loginUser(loginUsername, loginPassword);

                                if (loginResult) {
                                    loggedInUsername = loginUsername;
                                    System.out.println("Login successful!");
                                    isLoggedIn = true;
                                } else {
                                    System.out.println("Login failed. Invalid username or password.");
                                }
                                break;

                            case 3:
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

                            case 4:
                                System.out.println("Exiting Attendance Tracker.");
                                scanner.close();
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid choice. Please select a valid option.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine();
                    }

                    if (isLoggedIn) {
                        System.out.println("\nLogged in as: " + loggedInUsername);
                        System.out.println("1. Access Authentication Token");
                        System.out.println("2. Generate QR");
                        System.out.println("3. Scan QR");
                        System.out.println("4. Logout");
                        System.out.print("Enter your choice: ");

                        try {
                            int loggedInChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (loggedInChoice) {
                                case 1 -> {
                                    // Access Authentication Token
                                    System.out.println("Accessing Authentication Token...");
                                    if (AuthManager.isLoggedIn()) {
                                        String authToken = AuthManager.getAuthToken();
                                        System.out.println("Accessing Authentication Token: " + authToken);
                                    } else {
                                        System.out.println("User is not logged in.");
                                    }
                                }

                                case 2 -> // Generate QR
                                     System.out.println("Generating QR...");

                                case 3 -> // Scan QR
                                    System.out.println("Scanning QR...");

                                case 4 -> {
                                    isLoggedIn = false;
                                    loggedInUsername = "";
                                    System.out.println("Logout successful!");
                                }
                                default -> System.out.println("Invalid choice. Please select a valid option.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
*/