/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package trial.ui;


/**
 *
 * @author Abdur
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimpleJavaFXApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a label with the text "Hello, JavaFX!"
        Label label = new Label("Hello, JavaFX!");

        // Create a layout pane and add the label to it
        StackPane root = new StackPane();
        root.getChildren().add(label);

        // Create a scene with the layout and set it as the scene for the primary stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);

        // Set the title of the window
        primaryStage.setTitle("Simple JavaFX Application");

        // Show the window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}