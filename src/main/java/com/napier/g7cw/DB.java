package com.napier.g7cw;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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
                // Wait for DB to start
                Thread.sleep(1000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://sql_db:3306/world?useSSL=false", "root", "password");
                if (con != null) {
                    System.out.println("Connected to database");
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Failed to connect to database on attempt " + i);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
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
     * Get the details of a capital city
     *
     * @return
     */
    public HashMap<String, String> getCapitalCity(String capital) {
        HashMap<String, String> capitalCity = new HashMap<String, String>();
        capitalCity.put("Name", null);
        capitalCity.put("Country", null);
        capitalCity.put("Population", null);


        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Name, city.Name, city.Population FROM country, city WHERE city.Name = '" + capital + "' AND country.Capital = city.ID";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract city information
            if (!rset.next()) {
                throw new RuntimeException("No city found");
            }

            capitalCity.put("Name", rset.getString("city.Name"));
            capitalCity.put("Country", rset.getString("country.Name"));
            capitalCity.put("Population", rset.getString("city.Population"));
        } catch (Exception e) {
            System.out.println("Failed to get city details");
            System.out.println(e.getMessage());
        }

        return capitalCity;
    }


    private boolean testConnection() {
        if (con == null) {
            return connect();
        }
        // i.e. there is already a connection
        return true;
    }
}
