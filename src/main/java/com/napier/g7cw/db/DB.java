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
    public boolean connect(String location, int delay) {
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
        boolean wait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait for DB to start
                if (wait) { Thread.sleep(delay); }

                // Connect to database - default location sql_db:3306
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "password");
                if (con != null) {
                    System.out.println("Connected to database");
                    return true;
                }
            } catch (SQLException e) {
                wait = true;
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
    };

    private boolean testConnection(String location, int delay) {
        if (con == null) {
            return connect(location, delay);
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
    public ResultSet customQuery(String sql) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
            return stmt.getResultSet();
        } catch (SQLException e) {
            System.err.println("Error fetching data from database");
            return null;
        }
    }
}
