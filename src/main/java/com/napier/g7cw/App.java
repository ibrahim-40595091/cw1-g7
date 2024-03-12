package com.napier.g7cw;

import java.sql.*;

public class App {
    public static void main(String[] args)
    {
        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found! Error message: \n" + e.getMessage());
            System.exit(-1);
        }

        // Connection to database
        Connection con = null;
        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait for DB to start
                Thread.sleep(1000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "password");
                if (con != null) {
                    System.out.println("Connected to database");
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Failed to connect to database on attempt " + Integer.toString(i));
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted. Error message: \n" + e.getMessage());
            }
        }

        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database. Error message: \n" + e.getMessage());
            }
        }
    }
}
