/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.attendancetracker.qr;

/**
 *
 * @author Abdur
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class QRCodeUtil {

    // Generate a QR code with the given content and save it to a file
    public static void generateQRCode(String content, int width, int height, String filePath) {
        QRCodeGenerator.generateQRCode(content, width, height, filePath);
    }

    // Decode a QR code and return the content
    public static String decodeQRCode(String filePath) {
        return QRCodeScanner.scanQRCode(filePath);
    }
}
