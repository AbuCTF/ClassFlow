/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trial;

/**
 *
 * @author Abdur
 */
import org.mindrot.jbcrypt.BCrypt;

public class BCryptExample {
    public static void main(String[] args) {
        // Example plaintext password
        String plaintextPassword = "P@ss123!";

        // Hash the password
        HashedPasswordResult hashedResult = hashPassword(plaintextPassword);

        // Check if a provided password matches the stored hash
        boolean passwordMatches = checkPassword("P@ss123!", hashedResult.getHashedPassword());

        if (passwordMatches) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Password does not match.");
        }

        System.out.println("Plaintext Password: " + plaintextPassword);
        System.out.println("Hashed Password: " + hashedResult.getHashedPassword());
    }

    // A class to store the hashed password and plaintext password
    public static class HashedPasswordResult {
        private final String hashedPassword;
        private final String plaintextPassword;

        public HashedPasswordResult(String hashedPassword, String plaintextPassword) {
            this.hashedPassword = hashedPassword;
            this.plaintextPassword = plaintextPassword;
        }

        public String getHashedPassword() {
            return hashedPassword;
        }

        public String getPlaintextPassword() {
            return plaintextPassword;
        }
    }

    // Hash a password using BCrypt and return the hashed and plaintext passwords
    public static HashedPasswordResult hashPassword(String password) {
        String salt = BCrypt.gensalt(); // Generate a random salt
        String hashedPassword = BCrypt.hashpw(password, salt);
        return new HashedPasswordResult(hashedPassword, password);
    }

    // Check if a provided password matches the stored hash
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
