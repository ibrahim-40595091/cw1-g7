package com.napier.g7cw;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    Connection con = null;


    /**
     * Connect to the MySQL database.
     * Exits if MySQL JDBC driver is not found.
     *
     * @return boolean - True if connection was successful, false otherwise.
     */
    public boolean connect() {
        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found! Error message: \n" + e.getMessage());
            System.exit(-1);
        }

        // Connection to database
        con = null;
        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Note: no longer need to wait for db to start - docker compose ensures this app only starts if db is running
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://sql_db:3306/world?useSSL=false", "root", "password");
                if (con != null) {
                    System.out.println("Connected to database");
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Failed to connect to database on attempt " + i);
            }
        }

        return false;
    }



    /**
     * Disconnect from the MySQL database.
     *
     * @return boolean - True if disconnection was successful, false otherwise.
     */
    public boolean disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                con = null;
                return true;
            } catch (Exception e) {
                System.out.println("Error closing connection to database. Error message: \n" + e.getMessage());
            }
        }
        return false;
    }


    /**
     * Gets first 10 cities in GBR by population
     */
    public ArrayList<String> getCityPopulation() {
        ArrayList<String> cities = new ArrayList<>();

        if (!testConnection()) { return cities; }

        String query = "SELECT Name FROM city WHERE CountryCode = 'GBR' ORDER BY Population DESC LIMIT 10";
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                cities.add(res.getString("Name"));
            }
        } catch (Exception e) {
            System.out.println("Error executing query. Error message: \n" + e.getMessage());
        }
        return cities;
    }

    private boolean testConnection() {
        if (con == null) {
            return connect();
        }
        // i.e. there is already a connection
        return true;
    }
}
