/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.attendancetracker.qr;

/**
 * QRCodeUtil provides utility methods for generating and decoding QR codes.
 */
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeUtil {

    public static void generateQRCode(String data, int width, int height, String filePath) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            File qrCodeFile = new File(filePath);

            try {
                if (qrCodeFile.exists() || qrCodeFile.createNewFile()) {
                    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodeFile.toPath());
                    System.out.println("QR code saved to: " + filePath);
                } else {
                    System.err.println("Failed to create QR code file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
