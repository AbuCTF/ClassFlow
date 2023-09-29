/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.attendancetracker.qr;

/**
 *
 * @author Abdur
 */
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeScanner {

    public static String scanQRCode(String filePath) {
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);

            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            Result result = new MultiFormatReader().decode(binaryBitmap);

            if (result != null) {
                return result.getText();
            }
        } catch (IOException | NotFoundException e) {
        }

        return null; // Return null if QR code not found or an error occurred
    }

    public static void main(String[] args) {
        String filePath = "C:\\Documents2\\SemiTrash\\QR.png"; // Replace with the path to your QR code image
        String qrCodeData = scanQRCode(filePath);

        if (qrCodeData != null) {
            System.out.println("QR Code Data: " + qrCodeData);
        } else {
            System.out.println("QR Code not found or an error occurred.");
        }
    }
}
