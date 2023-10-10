/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abdur
 */

// AttendanceRecord.java
package com.mycompany.attendancetracker.attendance;

import java.util.Scanner;

public class AttendanceRecord {
    public void recordAttendance() {
        AttendanceManager attendanceManager = new AttendanceManager();
        AttendanceManager.UserInfo userInfo = attendanceManager.collectUserInfo();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();  // Ask for and read the password

        // Verify the user's password and get their information
        AttendanceManager.UserInfo verifiedUserInfo = attendanceManager.verifyUserPassword(userInfo.username, password);

        if (verifiedUserInfo != null) {
            System.out.println("User verified. Attempting to record attendance...");

            // Insert attendance record
            attendanceManager.insertAttendanceRecord(verifiedUserInfo);

            System.out.println("Attendance recorded successfully.");
        } else {
            System.out.println("User verification failed. Attendance not recorded.");
        }
    }
}
