package com.napier.g7cw.db;

import com.napier.g7cw.obj.City;

import java.sql.*;


/**
 * Utility object to fetch city data from the database - "City DataBase Access"
 */
public class CityDBA {
    /**
     * Fetch city from the database by ID
     * @param db
     * The database to fetch from
     * @param ID
     * The city ID
     * @returns
     * The city fetched from the database, otherwise null
     */
    public static City getCityByID(DB db, int ID) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM city WHERE ID = " + ID;
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("City ID " + ID + " not found.");
                return null;
            }

            City c = new City(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("CountryCode"),
                    rs.getString("District"),
                    rs.getInt("Population")
            );

            return c;
        } catch (SQLException sqle) {
            System.out.println("Failed to get city with ID " + ID);
            System.out.println(sqle.getMessage());
        }

        return null;
    }

    /**
     * Fetch a city from the database by name
     * @param db
     * The database to fetch from
     * @param name
     * The city name
     * @returns
     * The city fetched from the database, otherwise null
     */
    public static City getCityByName(DB db, String name){
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM city WHERE Name = '" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("City " + name + " not found.");
                return null;
            }

            City c = new City(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("CountryCode"),
                    rs.getString("District"),
                    rs.getInt("Population")
            );

            return c;
        } catch (SQLException sqle) {
            System.out.println("Failed to get city " + name);
            System.out.println(sqle.getMessage());
        }

        return null;
    }
}
