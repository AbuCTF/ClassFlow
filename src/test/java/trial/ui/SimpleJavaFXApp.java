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
import javafx.stage.Stage;
 
public class SimpleJavaFXApp extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage theStage) 
    {
        theStage.setTitle("Hello, World!");
        theStage.show();
    }
}