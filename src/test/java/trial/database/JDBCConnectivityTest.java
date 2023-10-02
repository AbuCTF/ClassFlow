/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trial.database;

/**
 *
 * @author Abdur
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectivityTest {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/trial";
        String user = "postgres";
        String password = "P@ss123!";
        
        Connection connection = null;

        try {
            // Attempt to establish a connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL database!");
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}
