/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trial.encrypt;

/**
 *
 * @author Abdur
 */
import org.mindrot.jbcrypt.BCrypt;

public class VerifyPasswordExample {
    public static void main(String[] args) {
        String hashedPassword;
        hashedPassword = "$2a$10$0ZXLYtYfXXts4/4WYiCwi.kM8XuYWgngv0jj/Ak88DRIf4lkkjWjy";
        String enteredPassword; // Replace with the password to check
        enteredPassword = "P@ss123!";

        boolean isPasswordCorrect = BCrypt.checkpw(enteredPassword, hashedPassword);

        if (isPasswordCorrect) {
            System.out.println("Password is correct!");
        } else {
            System.out.println("Password is incorrect.");
        }
    }
}