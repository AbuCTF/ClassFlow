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
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class QRCodeScanner {
    public static void main(String[] args) throws ChecksumException, FormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the QR code image: ");
        String imagePath = scanner.nextLine();
        scanner.close();

        try {
            System.out.println("Scanning QR code...");
            FileInputStream inputStream = new FileInputStream(new File(imagePath));
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            QRCodeReader reader = new QRCodeReader();
            Result result = reader.decode(bitmap);

            String scannedContent = result.getText();

            // Split the scanned content into its components
            String[] components = scannedContent.split("\\|");
            if (components.length == 1) {
                String secretKey = components[0];

                String expectedSecretKey = readSecretKeyFromFile("C:\\Documents2\\Programming\\Java\\ClassFlow\\src\\main\\resources\\files\\secret_key.txt");

                if (secretKey.equals(expectedSecretKey)) {
                    System.out.println("QR code successfully verified. Content matches the secret key.");
                } else {
                    System.out.println("QR code verification failed. Content does not match the secret key.");
                }
            } else {
                System.out.println("Invalid QR code content format.");
            }
        } catch (NotFoundException | IOException e) {
            System.out.println("QR code verification failed. An error occurred.");
            e.printStackTrace();
        }
    }

    private static String readSecretKeyFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("QR code verification failed. An error occurred while reading the secret key file.");
            e.printStackTrace();
            return null;
        }
    }
}


