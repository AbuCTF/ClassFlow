/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.attendancetracker.qr;

/**
 *
 * @author Abdur
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class QRCodeGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        try {
            // Get the teacher's session ID manually from a scanner
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the teacher's session ID: ");
            String teacherSessionID = scanner.nextLine();

            String secretKeyContents = readSecretKeyFromFile("C:\\Documents2\\Programming\\Java\\ClassFlow\\src\\main\\resources\\files\\secret_key.txt");

            // Generate a UUID based on the secret key contents
            String generatedUUID = generateUniqueUUID(secretKeyContents);

            // Save the generated UUID to a file
            saveUUIDToFile(generatedUUID, "C:\\Documents2\\Programming\\Java\\ClassFlow\\src\\main\\resources\\images\\UUID\\GenUUID.txt");

            // Display the contents of the secret key and generated UUID
            System.out.println("Secret Key Contents: " + secretKeyContents);
            System.out.println("Generated UUID: " + generatedUUID);

            // Create a JFrame and display the QR code
            JFrame frame = new JFrame("QR Code Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            BitMatrix bitMatrix = new MultiFormatWriter().encode(secretKeyContents, BarcodeFormat.QR_CODE, 300, 300);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            JLabel qrCodeLabel = new JLabel(new ImageIcon(image));
            frame.add(qrCodeLabel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            String outputFilePath = "C:\\Documents2\\Programming\\Java\\ClassFlow\\src\\main\\resources\\images\\qrcodes\\qrcode01.png";
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(outputFilePath).toPath());
            System.out.println("QR code saved to: " + outputFilePath);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateUniqueUUID(String secretKeyContents) {
        return UUID.nameUUIDFromBytes(secretKeyContents.getBytes()).toString();
    }

    private static String readSecretKeyFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void saveUUIDToFile(String uuid, String filePath) {
        try {
            Files.write(Paths.get(filePath), uuid.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
