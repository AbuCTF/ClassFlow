/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trial.qr;

/**
 *
 * @author Abdur
 */

import java.util.UUID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class UniqueQRCodeGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static UUID generateUniqueQRCode() {
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
            return UUID.nameUUIDFromBytes(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        UUID uniqueQRCode = generateUniqueQRCode();
        System.out.println("Unique QR Code: " + uniqueQRCode);
    }
}

