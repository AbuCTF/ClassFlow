/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abdur
 */
package com.mycompany.attendancetracker.security;

import java.io.*;
import java.io.IOException;

public class EncryptionUtil {
    public static void main(String[] args) {
        try {
            // Read the master key from user input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the master key: ");
            String masterKey = reader.readLine();

            // Read the admin key from the admin_key.txt file
            BufferedReader adminKeyReader = new BufferedReader(new FileReader("C:\\Documents2\\Drops\\Random\\admin_key.txt"));
            String adminKey = adminKeyReader.readLine();

            // Combine the master key and admin key to create the final secret key
            String secretKey = masterKey + adminKey;

            // Write the combined key to secret_key.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Documents2\\Programming\\Java\\ClassFlow\\src\\main\\resources\\files\\secret_key.txt"));
            writer.write(secretKey);
            writer.close();

            System.out.println("Secret key has been saved to secret_key.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
