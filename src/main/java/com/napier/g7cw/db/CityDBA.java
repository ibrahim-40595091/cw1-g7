package com.napier.g7cw.db;

import com.napier.g7cw.obj.City;

import java.sql.*;
import java.util.ArrayList;


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
                return City.NULL_CITY;
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
        } catch (Exception e) {
            System.out.println("Failed to get city " + name);
            System.out.println(e.getMessage());
        }

        return null;
    }


    /**
     * Fetch all cities from the database
     * @param db
     * The database to fetch from
     * @returns
     * An array of cities fetched from the database, otherwise null
     */
    public static ArrayList<City> getAllCities(DB db, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM city ORDER BY Population " + (largestFirst ? "DESC" : "ASC");
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                City c = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                cities.add(c);
            }

            return cities;
        } catch (SQLException sqle) {
            System.out.println("Failed to get all cities");
            System.out.println(sqle.getMessage());
        }

        return null;
    }


    /**
     * Fetch all cities from the database by continent
     * @param db
     * The database to fetch from
     * @param continent
     * The continent to filter by
     * @returns
     * An array of cities fetched from the database, otherwise null
     */
    public static ArrayList<City> getCitiesByContinent(DB db, String continent, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continent + "' ORDER BY city.Population " + (largestFirst ? "DESC" : "ASC");
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                City c = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                cities.add(c);
            }

            return cities;
        } catch (SQLException sqle) {
            System.out.println("Failed to get cities by continent " + continent);
            System.out.println(sqle.getMessage());
        }

        return null;
    }


    /**
     * Fetch all cities from the database by region
     * @param db
     * The database to fetch from
     * @param region
     * The region to filter by
     * @returns
     * An array of cities fetched from the database, otherwise null
     */
    public static ArrayList<City> getCitiesByRegion(DB db, String region, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + region + "' ORDER BY city.Population " + (largestFirst ? "DESC" : "ASC");
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                City c = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                cities.add(c);
            }

            return cities;
        } catch (SQLException sqle) {
            System.out.println("Failed to get cities by region " + region);
            System.out.println(sqle.getMessage());
        }

        return null;
    }


    /**
     * Fetch all cities from the database by country
     * @param db
     * The database to fetch from
     * @param countryCode
     * The country to filter by
     * @returns
     * An array of cities fetched from the database, otherwise null
     */
    public static ArrayList<City> getCitiesByCountry(DB db, String countryCode, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM city WHERE CountryCode = '" + countryCode + "' ORDER BY Population " + (largestFirst ? "DESC" : "ASC");
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                City c = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                cities.add(c);
            }

            return cities;
        } catch (SQLException sqle) {
            System.out.println("Failed to get cities by country code " + countryCode);
            System.out.println(sqle.getMessage());
        }

        return null;
    }


    /**
     * Fetch all cities from the database by district
     * @param db
     * The database to fetch from
     * @param district
     * The district to filter by
     * @returns
     * An array of cities fetched from the database, otherwise null
     */
    public static ArrayList<City> getCitiesByDistrict(DB db, String district, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM city WHERE District = '" + district + "' ORDER BY Population " + (largestFirst ? "DESC" : "ASC");
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                City c = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                cities.add(c);
            }

            return cities;
        } catch (SQLException sqle) {
            System.out.println("Failed to get cities by district " + district);
            System.out.println(sqle.getMessage());
        }

        return null;
    }


    /**
     * Fetch all capital cities from the database
     * @param db
     * The database to fetch from
     * @returns
     * An array of cities fetched from the database, otherwise null
     */
    public static ArrayList<City> getAllCapitalCities(DB db, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM city WHERE ID IN (SELECT Capital FROM country) ORDER BY Population " + (largestFirst ? "DESC" : "ASC");
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                City c = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                cities.add(c);
            }

            return cities;
        } catch (SQLException sqle) {
            System.out.println("Failed to get capital cities");
            System.out.println(sqle.getMessage());
        }

        return null;
    }


    /**
     * Fetch all capital cities in a continent from the database
     * @param db
     * The database to fetch from
     * @param continent
     * The continent to filter by
     * @returns
     * An array of cities fetched from the database, otherwise null
     */
    public static ArrayList<City> getCapitalCitiesByContinent(DB db, String continent, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continent + "' AND city.ID IN (SELECT Capital FROM country) ORDER BY city.Population " + (largestFirst ? "DESC" : "ASC");
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                City c = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                cities.add(c);
            }

            return cities;
        } catch (SQLException sqle) {
            System.out.println("Failed to get capital cities by continent " + continent);
            System.out.println(sqle.getMessage());
        }

        return null;
    }


    /**
     * Fetch all capital cities in a region from the database
     * @param db
     * The database to fetch from
     * @param region
     * The region to filter by
     * @returns
     * An array of cities fetched from the database, otherwise null
     */
    public static ArrayList<City> getCapitalCitiesByRegion(DB db, String region, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + region + "' AND city.ID IN (SELECT Capital FROM country) ORDER BY city.Population " + (largestFirst ? "DESC" : "ASC");
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<City> cities = new ArrayList<>();
            while (rs.next()) {
                City c = new City(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("CountryCode"),
                        rs.getString("District"),
                        rs.getInt("Population")
                );
                cities.add(c);
            }

            return cities;
        } catch (SQLException sqle) {
            System.out.println("Failed to get capital cities by region " + region);
            System.out.println(sqle.getMessage());
        }

        return null;
    }
}

