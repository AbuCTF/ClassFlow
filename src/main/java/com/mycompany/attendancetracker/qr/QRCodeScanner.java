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

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import java.util.Hashtable;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class QRCodeScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the QR code image: ");
        String imagePath = scanner.nextLine();

        System.out.print("Enter the session ID: ");
        String sessionID = scanner.nextLine();

        // Read session information from the session_info.txt file
        String sessionInfo = readSessionInfoFromFile("session_info.txt");

        // Extract Session ID, UUID, Start Timestamp, and Duration
        String[] sessionData = sessionInfo.split(":");
        String storedSessionID = sessionData[0];
        String storedUUID = sessionData[1];
        long startTime = Long.parseLong(sessionData[2]);
        long duration = Long.parseLong(sessionData[3]);

        // Verify the Session ID
        if (sessionID.equals(storedSessionID)) {
            // Verify the QR code contents
            if (verifyQRCode(imagePath, storedUUID)) {
                long currentTime = System.currentTimeMillis();

                // Verify that the session is within its duration
                if (currentTime >= startTime && currentTime <= startTime + duration) {
                    // Session is valid, record attendance or perform the required action
                    System.out.println("Attendance recorded for Session ID: " + sessionID);
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
