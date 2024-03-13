package com.napier.g7cw;

import java.sql.*;
import java.util.ArrayList;

public class App {
    static DB db = null;


    public static ArrayList<String> getCitiesByPopulation() {
        ArrayList<String> cities = new ArrayList<String>();
        try {
            // Create an SQL statement
            Statement stmt = db.con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Name FROM city ORDER BY Population DESC LIMIT 10";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid.
            while (rset.next()) {
                cities.add(rset.getString("Name"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by population");
        }
        return cities;
    }



    public static void main(String[] args)
    {
        db = new DB();
        db.connect();

        // Do stuff
        ArrayList<String> cities = getCitiesByPopulation();
        for (String city : cities) {
            System.out.println(city);
        }

        db.disconnect();
    }
}
