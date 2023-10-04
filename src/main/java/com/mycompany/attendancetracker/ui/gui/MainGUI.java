/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.mycompany.attendancetracker.ui.gui;

import com.mycompany.attendancetracker.user.UserRegistrationManager;
import com.mycompany.attendancetracker.user.UserLoginManager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ClassFlow - Attendance Tracker");

        // Create a registration form
        GridPane registrationForm = new GridPane();
        registrationForm.setPadding(new Insets(20, 20, 20, 20));
        registrationForm.setVgap(8);
        registrationForm.setHgap(10);

        Label regUsernameLabel = new Label("Username:");
        GridPane.setConstraints(regUsernameLabel, 0, 0);
        TextField regUsernameInput = new TextField();
        GridPane.setConstraints(regUsernameInput, 1, 0);

        Label regPasswordLabel = new Label("Password:");
        GridPane.setConstraints(regPasswordLabel, 0, 1);
        PasswordField regPasswordInput = new PasswordField();
        GridPane.setConstraints(regPasswordInput, 1, 1);

        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 1, 2);

        registrationForm.getChildren().addAll(regUsernameLabel, regUsernameInput, regPasswordLabel, regPasswordInput, registerButton);

        // Create a login form
        GridPane loginForm = new GridPane();
        loginForm.setPadding(new Insets(20, 20, 20, 20));
        loginForm.setVgap(8);
        loginForm.setHgap(10);

        Label loginUsernameLabel = new Label("Username:");
        GridPane.setConstraints(loginUsernameLabel, 0, 0);
        TextField loginUsernameInput = new TextField();
        GridPane.setConstraints(loginUsernameInput, 1, 0);

        Label loginPasswordLabel = new Label("Password:");
        GridPane.setConstraints(loginPasswordLabel, 0, 1);
        PasswordField loginPasswordInput = new PasswordField();
        GridPane.setConstraints(loginPasswordInput, 1, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);

        loginForm.getChildren().addAll(loginUsernameLabel, loginUsernameInput, loginPasswordLabel, loginPasswordInput, loginButton);

        // Create a tabbed layout for registration and login
        TabPane tabPane = new TabPane();

        Tab regTab = new Tab("Register");
        regTab.setContent(registrationForm);

        Tab loginTab = new Tab("Login");
        loginTab.setContent(loginForm);

        tabPane.getTabs().addAll(regTab, loginTab);

        Scene scene = new Scene(tabPane, 400, 250);
        primaryStage.setScene(scene);

        registerButton.setOnAction(e -> {
            String username = regUsernameInput.getText();
            String password = regPasswordInput.getText();
            boolean registrationResult = registerUser(username, password);
            showRegistrationResultAlert(registrationResult);
        });

        loginButton.setOnAction(e -> {
            String username = loginUsernameInput.getText();
            String password = loginPasswordInput.getText();
            boolean loginResult = loginUser(username, password);
            showLoginResultAlert(loginResult);
        });

        primaryStage.show();
    }

    private boolean registerUser(String username, String password) {
        UserRegistrationManager registrationManager = new UserRegistrationManager();
        String email = "user@example.com"; // Replace with user input or leave as a default
        int maxUsers = 10; // Set your registration limit here

        return registrationManager.registerUser(username, password, email, maxUsers);
    }

    private boolean loginUser(String username, String password) {
        UserLoginManager loginManager = new UserLoginManager();
        return loginManager.loginUser(username, password);
    }

    private void showRegistrationResultAlert(boolean registrationResult) {
        if (registrationResult) {
            showAlert("Registration Successful", "User registered successfully!");
        } else {
            showAlert("Registration Failed", "User registration failed.");
        }
    }

    private void showLoginResultAlert(boolean loginResult) {
        if (loginResult) {
            showAlert("Login Successful", "Login successful!");
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    // Helper method to display an alert dialog
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
