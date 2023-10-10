/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

/**
 *
 * @author Abdur
 */
package com.mycompany.attendancetracker.ui.gui;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.mycompany.attendancetracker.auth.AuthManager;
import com.mycompany.attendancetracker.qr.QRCodeGenerator;
import com.mycompany.attendancetracker.qr.QRCodeScanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class UserMenuGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ClassFlow - User Menu");

        // Create a menu for user actions
        GridPane userMenu = new GridPane();
        userMenu.setPadding(new Insets(20, 20, 20, 20));
        userMenu.setVgap(8);
        userMenu.setHgap(10);

        Label menuLabel = new Label("User Menu:");
        GridPane.setConstraints(menuLabel, 0, 0);

        Button accessAuthTokenButton = new Button("Access Authentication Token");
        GridPane.setConstraints(accessAuthTokenButton, 1, 1);

        Button generateQRButton = new Button("Generate QR Code");
        GridPane.setConstraints(generateQRButton, 1, 2);

        Button scanQRButton = new Button("Scan QR Code");
        GridPane.setConstraints(scanQRButton, 1, 3);

        Button logoutButton = new Button("Logout");
        GridPane.setConstraints(logoutButton, 1, 4);

        userMenu.getChildren().addAll(menuLabel, accessAuthTokenButton, generateQRButton, scanQRButton, logoutButton);

        VBox mainLayout = new VBox(userMenu);
        Scene scene = new Scene(mainLayout, 400, 250);
        primaryStage.setScene(scene);

        primaryStage.show();

        accessAuthTokenButton.setOnAction(e -> {
            // Implement code to access authentication token
            String authToken = AuthManager.getAuthToken();
            showInfoAlert("Access Authentication Token", "Authentication Token: " + authToken);
        });

        generateQRButton.setOnAction(e -> {
            // Implement code to generate QR code
            QRCodeGenerator.main(new String[]{});
        });

        scanQRButton.setOnAction((var e) -> {
            // Implement code to scan QR code
            QRCodeScanner.main(new String[]{});
        });

        logoutButton.setOnAction(e -> {
            // Clear user credentials and perform logout actions
            AuthManager.setAuthToken(null);
            AuthManager.setLoggedIn(false);

            // Return to the login screen or main menu
            // You may need to launch a new instance of the MainGUI here
            MainGUI mainGUI = new MainGUI();
            Stage stage = new Stage();
            mainGUI.start(stage);

            // Close the current UserMenuGUI window
            ((Stage) logoutButton.getScene().getWindow()).close();
        });

    }

    private void showInfoAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
