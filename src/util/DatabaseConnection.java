package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database credentials and URL
    private static final String URL = "jdbc:mysql://localhost:3306/game_project";
    private static final String USER = "bn_processmaker";
    private static final String PASS = "5659ecd8ab";

    // Method to get a connection to the database
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver (optional for newer JDBC, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database. Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Error: " + e.getMessage());
        }
        return connection;
    }

    // Method to close the connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the connection. Error: " + e.getMessage());
            }
        }
    }
}
