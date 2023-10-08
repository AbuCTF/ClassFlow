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
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class QRCodeGenerator {
    public static void main(String[] args) {
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
        
        // Create a QR code content including Session ID and UUID
        String qrCodeContent = teacherSessionID + ":" + uuidString;
        
        // Store session information in a text file
        storeSessionInfo(teacherSessionID, uuidString, sessionDurationMinutes);
        
        // Generate the QR code
        try {
            int width = 300;
            int height = 300;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", Paths.get("qrcode.png"));
            System.out.println("QR code saved as 'qrcode.png'");
        } catch (Exception e) {
            System.out.println("QR code generation failed.");
            e.printStackTrace();
        }
    }

    private static void storeSessionInfo(String sessionID, String uuid, long duration) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Documents2\\Programming\\Java\\ClassFlow\\src\\main\\resources\\images\\UUID\\session_info.txt", true))) {
            String line = sessionID + ":" + uuid + ":" + System.currentTimeMillis() + ":" + duration;
            writer.println(line);
        } catch (IOException e) {
            System.out.println("Failed to store session information.");
            e.printStackTrace();
        }
    }
}

