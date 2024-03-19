package com.napier.g7cw.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Allows a connection to the MySQL database
 */
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
                // Connect to database
                // On the first attempt, this connects immediately if the database is already running
                con = DriverManager.getConnection("jdbc:mysql://sql_db:3306/world?useSSL=false", "root", "password");
                if (con != null) {
                    System.out.println("Connected to database");
                    return true;
                }

                // Wait for DB to start
                Thread.sleep(30000);
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
     * Fetch top N populated capital cities from the database, in a particular area of the world
     * @param n
     * The number of capital cities to
     * @param areaType
     * @param area
     * @return
     */
    public ArrayList<HashMap<String, String>> topNCapitalCities(int n, String areaType, String area) {
        ArrayList<HashMap<String, String>> capitalCities = new ArrayList<>();

        try {

        } catch (Exception e) {
            System.out.println("Failed to get top " + n + " capital cities in " + area);
            System.out.println(e.getMessage());
        }

        return capitalCities;
    }



    private boolean testConnection() {
        if (con == null) {
            return connect();
        }
        // i.e. there is already a connection
        return true;
    }


    /**
     * For testing only. Allows for custom SQL queries to be run on the database.
     * @param sql
     * The query to be executed
     * @return
     * A {@link ResultSet} object that contains the data produced by the given query; never null
     * @throws SQLException
     * If a database access error occurs
     */
    public ResultSet customQuery(String sql) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeQuery(sql);
        return stmt.getResultSet();
    }
}
