/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
/**
 *
 * @author Abdur
 */
package com.mycompany.attendancetracker.ui.gui;

import com.mycompany.attendancetracker.user.UserRegistrationManager;
import com.mycompany.attendancetracker.user.UserLoginManager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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

        Label regEmailLabel = new Label("Email:");
        GridPane.setConstraints(regEmailLabel, 0, 2);
        TextField regEmailInput = new TextField();
        GridPane.setConstraints(regEmailInput, 1, 2);

        Label regRoleLabel = new Label("Role:");
        GridPane.setConstraints(regRoleLabel, 0, 3);
        ComboBox<String> regRoleInput = new ComboBox<>();
        regRoleInput.getItems().addAll("user", "student");
        GridPane.setConstraints(regRoleInput, 1, 3);

        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 1, 4);

        registrationForm.getChildren().addAll(regUsernameLabel, regUsernameInput, regPasswordLabel, regPasswordInput, regEmailLabel, regEmailInput, regRoleLabel, regRoleInput, registerButton);

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

        // Create a delete user form
        GridPane deleteUserForm = new GridPane();
        deleteUserForm.setPadding(new Insets(20, 20, 20, 20));
        deleteUserForm.setVgap(8);
        deleteUserForm.setHgap(10);

        Label deleteUserLabel = new Label("Delete User:");
        GridPane.setConstraints(deleteUserLabel, 0, 0);
        TextField deleteUserInput = new TextField();
        GridPane.setConstraints(deleteUserInput, 1, 0);

        Button deleteUserButton = new Button("Delete User");
        GridPane.setConstraints(deleteUserButton, 1, 1);

        deleteUserForm.getChildren().addAll(deleteUserLabel, deleteUserInput, deleteUserButton);

        // Create a tabbed layout for registration, login, and delete user
        TabPane tabPane = new TabPane();

        Tab regTab = new Tab("Register");
        regTab.setContent(registrationForm);

        Tab loginTab = new Tab("Login");
        loginTab.setContent(loginForm);

        Tab deleteUserTab = new Tab("Delete User");
        deleteUserTab.setContent(deleteUserForm);

        tabPane.getTabs().addAll(regTab, loginTab, deleteUserTab);

        Scene scene = new Scene(tabPane, 400, 250);
        primaryStage.setScene(scene);

        primaryStage.show();

        registerButton.setOnAction(e -> {
            String username = regUsernameInput.getText();
            String password = regPasswordInput.getText();
            String email = regEmailInput.getText();
            String role = regRoleInput.getValue();
            boolean registrationResult = registerUser(username, password, email, role);
            showRegistrationResultAlert(registrationResult);
        });

        loginButton.setOnAction(e -> {
            String username = loginUsernameInput.getText();
            String password = loginPasswordInput.getText();
            boolean loginResult = loginUser(username, password);
            showLoginResultAlert(loginResult);
        });

        deleteUserButton.setOnAction(e -> {
            String username = deleteUserInput.getText();
            boolean deleteResult = deleteUser(username);
            showDeleteResultAlert(deleteResult);
        });

        primaryStage.show();
    }

    private boolean registerUser(String username, String password, String email, String role) {
        UserRegistrationManager registrationManager = new UserRegistrationManager();
        int maxUsers = 10; // Set your registration limit here

        return registrationManager.registerUser(username, password, email, role, maxUsers);
    }

    private boolean loginUser(String username, String password) {
        UserLoginManager loginManager = new UserLoginManager();
        boolean loginResult = loginManager.loginUser(username, password);

        if (loginResult) {
            // Login successful, open the UserMenuGUI
            UserMenuGUI userMenuGUI = new UserMenuGUI();
            userMenuGUI.start(new Stage());
        }

        return loginResult;
    }


    private boolean deleteUser(String username) {
        // Replace this with your actual user deletion logic
        UserRegistrationManager registrationManager = new UserRegistrationManager();

        // Here, we'll assume that you have a method in your UserRegistrationManager class
        // that handles user deletion. You should replace this with the actual method.

        boolean deleteResult = registrationManager.deleteUserWithConfirmation(username, "adminPassword");

        return deleteResult;
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

    private void showDeleteResultAlert(boolean deleteResult) {
        if (deleteResult) {
            showAlert("Delete User Successful", "User deleted successfully!");
        } else {
            showAlert("Delete User Failed", "User deletion failed.");
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

