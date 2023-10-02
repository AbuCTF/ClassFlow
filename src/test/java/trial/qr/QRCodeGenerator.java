/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trial.qr;

/**
 *
 * @author Abdur
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QRCodeGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final String secretKeyFilePath = "secret_key.txt";

    public static void main(String[] args) {
        // Generate a unique QR code
        String uniqueQRCode = generateUniqueQRCode();

        // Create a Swing window to display the QR code
        JFrame frame = new JFrame("QR Code Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        try {
            // Create a BitMatrix from the generated QR code data
            BitMatrix bitMatrix = new MultiFormatWriter().encode(uniqueQRCode, BarcodeFormat.QR_CODE, 300, 300);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // Fill the BufferedImage with the QR code data
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            // Display the QR code image in a JLabel
            JLabel qrCodeLabel = new JLabel(new ImageIcon(image));
            frame.add(qrCodeLabel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);

            // Save the QR code to a file
            String outputFilePath = "C:\\Documents2\\Programming\\Java\\ClassFlow\\src\\main\\resources\\images\\qrcodes\\qrcode02.png";
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(outputFilePath).toPath());
            System.out.println("QR code saved to: " + outputFilePath);

            // Store the secret key in a text file
            String secretKey = generateSecretKey();
            Files.write(Paths.get(secretKeyFilePath), secretKey.getBytes());

            System.out.println("Secret key saved to: " + secretKeyFilePath);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateUniqueQRCode() {
        try {
            // Create a timestamp component
            long timestamp = Instant.now().toEpochMilli();

            // Create a custom string component
            String customString = "CFSession01";

            // Create an incremental counter component
            int count = counter.incrementAndGet();

            // Create a combined input string
            String combinedInput = timestamp + customString + count;

            // Hash the combined input using SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(combinedInput.getBytes(StandardCharsets.UTF_8));

            // Create a UUID from the hashed bytes
            return UUID.nameUUIDFromBytes(hashBytes).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String generateSecretKey() {
        // Generate a random secret key or use a specific algorithm to create one
        return "your_secret_key_here";
    }
}
