/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.attendancetracker.auth;

/**
 *
 * @author Abdur
 */
public class AuthManager {
    private static String authToken;
    private static boolean loggedIn = false;

    public static void setAuthToken(String token) {
        authToken = token;
        System.out.println("Authentication token set.");
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static void setLoggedIn(boolean status) {
        loggedIn = status;
        if (loggedIn) {
            System.out.println("User logged in.");
        } else {
            System.out.println("User logged out.");
        }
    }

    public static boolean isLoggedIn() {
        return loggedIn;
    }
    
}
