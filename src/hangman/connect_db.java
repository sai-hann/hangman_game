package hangman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect_db {
    private static String url = "jdbc:mysql://localhost:3306/hangman_db";
    private static String user = "hangman";
    private static String password = "$Hangman3142";

    private static Connection connection;

    // Constructor to establish the connection
    public connect_db() {
        connect();
    }

    // Establishes the connection to the database
    private static void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    // Returns the current connection
    public static Connection getConnection() {
        if (connection == null || isClosed()) {
            connect();  // Reconnect if the connection is closed
        }
        return connection;
    }

    // Check if the connection is closed
    private static boolean isClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // If there's an error checking, assume it's closed
        }
    }

    // Close the connection when done
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
