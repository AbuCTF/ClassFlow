/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abdur
 */

package com.mycompany.attendancetracker.qr;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.mycompany.attendancetracker.attendance.AttendanceManager;
import com.mycompany.attendancetracker.attendance.AttendanceRecord;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class QRCodeScanner {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = QRCodeScanner.class.getClassLoader().getResourceAsStream("config\\config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String qrCodeImagePath = properties.getProperty("qrCodeFilePath");
        // Read QR code contents from the image
        String qrCodeContent = readQRCodeContent(qrCodeImagePath);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the session ID: ");
        String sessionID = scanner.nextLine();

        System.out.print("Enter the session duration in minutes: ");
        String durationInput = scanner.nextLine();
        long sessionDuration;

        try {
            sessionDuration = Long.parseLong(durationInput) * 60 * 1000; // Convert minutes to milliseconds
        } catch (NumberFormatException e) {
            System.out.println("Invalid session duration. Please enter a valid numeric value.");
            return;
        }

        // Read session information from the session_info.txt file
        String sessionInfoFilePath = properties.getProperty("sessionInfoFilePath");
        String sessionInfo = readSessionInfoFromFile(sessionInfoFilePath);

        // Extract Session ID, UUID, Start Timestamp, and Duration
        String[] sessionData = sessionInfo.split(":");
        String storedSessionID = sessionData[0];
        String storedUUID = sessionData[1];
        long startTime = Long.parseLong(sessionData[2]);
        long duration = Long.parseLong(sessionData[3]);

        // Debug: Print the extracted values
        System.out.println("Extracted Session ID: " + storedSessionID);
        System.out.println("Extracted UUID: " + storedUUID);
        System.out.println("Extracted Start Time: " + startTime);
        System.out.println("Extracted Duration: " + duration);
        System.out.println("QR Code Content: " + qrCodeContent);

        // Verify the Session ID
        if (sessionID.equals(storedSessionID)) {

            if (qrCodeContent != null && qrCodeContent.equals(sessionInfo)) {
                long currentTime = System.currentTimeMillis();

                // Debug: Print the extracted and current timestamps
                System.out.println("Current Time: " + currentTime);

                // Verify that the session is within its duration
                if (currentTime >= startTime && currentTime <= startTime + duration) {
                    // Session is valid, record attendance using the AttendanceManager
                    AttendanceRecord attendanceRecord = new AttendanceRecord();
                        attendanceRecord.recordAttendance();
                } else {
                    System.out.println("QR code has expired. Session duration has ended.");
                }
            } else {
                System.out.println("QR code contents do not match the session.");
            }
        } else {
            System.out.println("Invalid session ID.");
        }
    }
    
    private static String readQRCodeContent(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();
        } catch (Exception e) {
            System.out.println("Failed to read QR code content.");
            e.printStackTrace();
        }

        return null;
    }

    private static String readSessionInfoFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Failed to read session information from the file.");
            e.printStackTrace();
        }
        return "";
    }

    private static boolean verifyQRCode(String imagePath, String expectedUUID) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);

            String qrCodeContent = result.getText();

            // Verify that the QR code content matches the expected UUID
            return qrCodeContent.equals(expectedUUID);
        } catch (Exception e) {
            System.out.println("Failed to verify QR code.");
            e.printStackTrace();
        }

        return false;
    }

}

