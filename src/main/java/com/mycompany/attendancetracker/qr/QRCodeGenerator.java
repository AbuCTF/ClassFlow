/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.attendancetracker.qr;

/**
 *
 * @author Abdur
 */
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    public static void generateQRCode(String text, int width, int height, String filePath) {
        try {
            // Create a QR code writer
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // Set QR code parameters
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Generate the QR code matrix
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

            // Create a BufferedImage from the BitMatrix
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            // Save the QR code image to a file
            File qrCodeFile = new File(filePath);
            ImageIO.write(image, "png", qrCodeFile);

            System.out.println("QR Code generated and saved to: " + filePath);
        } catch (WriterException | IOException e) {
            System.err.println("Error generating QR Code: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String qrCodeData; // Replace with your desired QR code data
        qrCodeData = "https://www.openai.com.";
        int width = 300; // QR code width in pixels
        int height = 300; // QR code height in pixels
        String filePath = "C:\\Documents2\\SemiTrash\\QR.png"; // Specify the output file path

        generateQRCode(qrCodeData, width, height, filePath);
    }
}
