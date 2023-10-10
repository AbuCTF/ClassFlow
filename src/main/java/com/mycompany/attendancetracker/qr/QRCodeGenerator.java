/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Abdur
 */
package com.mycompany.attendancetracker.qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class QRCodeGenerator {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("C:\\Documents2\\Programming\\Java\\ClassFlow\\src\\main\\resources\\config\\config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error reading configuration file: " + e.getMessage());
            return;
        }

        String qrcodeOutputPath = properties.getProperty("qrcode.output.path");
        String sessionInfoFilePath = properties.getProperty("session.info.file.path");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the teacher's session ID: ");
        String teacherSessionID = scanner.nextLine();

        System.out.print("Enter the session duration in minutes: ");
        int sessionDurationMinutes = scanner.nextInt() * 60 * 1000; // Convert minutes to milliseconds

        scanner.nextLine(); // Consume newline

        // Generate a UUID based on the Session ID and Timestamp
        String sessionInfo = teacherSessionID + ":" + System.currentTimeMillis();
        UUID generatedUUID = UUID.nameUUIDFromBytes(sessionInfo.getBytes());
        String uuidString = generatedUUID.toString();

        // Generate a timestamp at the beginning
        long timestamp = System.currentTimeMillis();

        // Create a QR code content including Session ID, UUID, timestamp, and Duration
        String qrCodeContent = teacherSessionID + ":" + uuidString + ":" + timestamp + ":" + sessionDurationMinutes;

        // Store session information in a text file
        storeSessionInfo(teacherSessionID, uuidString, timestamp, (long) sessionDurationMinutes, sessionInfoFilePath);

        // Generate the QR code
        try {
            int width = 300;
            int height = 300;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", Paths.get(qrcodeOutputPath));
            System.out.println("QR code saved as 'qrcode.png'");
            
            // Display the QR code in a JFrame
            displayQRCode(qrcodeOutputPath);
        } catch (Exception e) {
            System.out.println("QR code generation failed.");
            e.printStackTrace();
        }
    }

    private static void storeSessionInfo(String sessionID, String uuid, long timestamp, long duration, String sessionInfoFilePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(sessionInfoFilePath, true))) {
            String line = sessionID + ":" + uuid + ":" + timestamp + ":" + duration;
            writer.print(line); // Use print instead of println to avoid adding a newline character
        } catch (IOException e) {
            System.out.println("Failed to store session information.");
            e.printStackTrace();
        }
    }




    private static void displayQRCode(String imagePath) {
        try {
            // Load the image from the file
            BufferedImage image = ImageIO.read(new File(imagePath));
            
            // Create a JFrame and display the QR code
            JFrame frame = new JFrame("QR Code Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JLabel label = new JLabel(new ImageIcon(image));
            frame.add(label, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            System.out.println("Failed to display QR code.");
            e.printStackTrace();
        }
    }
}


