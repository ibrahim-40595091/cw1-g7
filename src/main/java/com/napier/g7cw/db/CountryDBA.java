package com.napier.g7cw.db;

import com.napier.g7cw.obj.City;
import com.napier.g7cw.obj.Continent;
import com.napier.g7cw.obj.Country;

import java.sql.*;
import java.util.ArrayList;

/**
 * Utility object to fetch country data from the database - "Country DataBase Access"
 */
public class CountryDBA {
    public static Country getCountry(DB db, String code) {
        return getCountry(db, "code", code);
    }
    public static Country getCountry(DB db, String attribute, String value) {
        switch (attribute) {
            case "name":
                return getCountryByName(db, value);
            case "code2":
                return getCountryByCode2(db, value);
            case "capital":
                return getCountryByCapitalID(db, Integer.parseInt(value));
            default: // case "code"
                return getCountryByCode(db, value);
        }
    }
    public static Country getCountry(DB db, String attribute, int value) {
        switch (attribute) {
            // Might want to add more ways to fetch country data (i.e. more case statements)
            default: //case "capitalid":
                return getCountryByCapitalID(db, value);
        }
    }
    public static Country getCountry(DB db, City city) {
        return getCountryByCapital(db, city);
    }


    /**
     * Fetches all countries from the database
     */
    public static ArrayList<Country> getAllCountries(DB db) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country;";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Country> countries = new ArrayList<>();
            while (rs.next()) {
                countries.add(countryFromResultSet(db, rs));
            }

            return countries;
        } catch (SQLException sqle) {
            System.out.println("SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }

    /**
     * Fetches all countries from the database, and sorts them by population
     */
    public static ArrayList<Country> getAllCountriesSortPopulation(DB db, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country ORDER BY Population " + (largestFirst ? "DESC" : "ASC") + ";";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Country> countries = new ArrayList<>();
            while (rs.next()) {
                countries.add(countryFromResultSet(db, rs));
            }

            return countries;
        } catch (SQLException sqle) {
            System.out.println("SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }


    /**
     * Gets all countries in the provided continent, sorted by population
     */
    public static ArrayList<Country> getCountriesByContinent(DB db, String continent, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country WHERE Continent = '" + continent + "' ORDER BY Population " + (largestFirst ? "DESC" : "ASC") + ";";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Country> countries = new ArrayList<>();
            while (rs.next()) {
                countries.add(countryFromResultSet(db, rs));
            }

            return countries;
        } catch (SQLException sqle) {
            System.out.println("SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }


    /**
     * Gets all countries in the provided region, sorted by population
     */
    public static ArrayList<Country> getCountriesByRegion(DB db, String region, boolean largestFirst) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country WHERE Region = '" + region + "' ORDER BY Population " + (largestFirst ? "DESC" : "ASC") + ";";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Country> countries = new ArrayList<>();
            while (rs.next()) {
                countries.add(countryFromResultSet(db, rs));
            }

            return countries;
        } catch (SQLException sqle) {
            System.out.println("SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }


    /**
     * Generates a country object using ResultSet data
     * @param db
     * {@link DB Database} object so extra information can be fetched as-needed
     * @param rs
     * A {@link ResultSet} containing country data
     * @return
     * A {@link Country} object
     */
    private static Country countryFromResultSet(DB db, ResultSet rs) {
        try {
            // Check city has a capital
            int capital_id = rs.getInt("Capital");
            City capital;
            if (capital_id == 0) { capital = City.NULL_CITY; }
            else { capital = CityDBA.getCityByID(db, capital_id); }

            Country c = new Country(
                    rs.getString("Code"),
                    rs.getString("Name"),
                    Continent.valueOf(rs.getString("Continent")),
                    rs.getString("Region"),
                    rs.getFloat("SurfaceArea"),
                    rs.getInt("IndepYear"),
                    rs.getInt("Population"),
                    rs.getFloat("LifeExpectancy"),
                    rs.getFloat("GNP"),
                    rs.getFloat("GNPOld"),
                    rs.getString("LocalName"),
                    rs.getString("GovernmentForm"),
                    rs.getString("HeadOfState"),
                    capital,
                    rs.getString("Code2"),
                    CountryLanguagesDBA.getCountryLanguages(db, rs.getString("Code"))
            );

            return c;


        } catch (SQLException sqle) {
            System.out.println("Unknown SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }


    /**
     * Fetches a country from the database using its English name
     * @param db
     * Reference to database to fetch data from
     * @param name
     * Name of country to fetch
     * @return {@link com.napier.g7cw.obj.Country} if found in database, otherwise null
     */
    private static Country getCountryByName(DB db, String name) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country WHERE name = '" + name + "' LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("Country '" + name + "' not found");
                return null;
            }

            return countryFromResultSet(db, rs);
        } catch (SQLException sqle) {
            System.out.println("Unknown SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }


    /**
     * Fetches a country from the database using its local name
     * @param db
     * Reference to database to fetch data from
     * @param localName
     * Local name of country to fetch
     * @return {@link com.napier.g7cw.obj.Country} if found in database, otherwise null
     */
    private static Country getCountryByLocalName(DB db, String localName) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country WHERE localName = '" + localName + "' LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("Country '" + localName + "' not found");
                return null;
            }

            return countryFromResultSet(db, rs);
        } catch (SQLException sqle) {
            System.out.println("Unknown SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }



    private static Country getCountryByCapital(DB db, City capital) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country WHERE capital = " + capital.getID() + " LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("Country with capital '" + capital.getName() + "' not found");
                return null;
            }

            return countryFromResultSet(db, rs);
        } catch (SQLException sqle) {
            System.out.println("Unknown SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }



    private static Country getCountryByCapitalID(DB db, int id) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country WHERE capital = " + id + " LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("Country with capital with id " + id + " not found");
                return null;
            }

            return countryFromResultSet(db, rs);
        } catch (SQLException sqle) {
            System.out.println("Unknown SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }



    private static Country getCountryByCode(DB db, String code) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country WHERE code = '" + code + "' LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("Country with code '" + code + "' not found");
                return null;
            }

            return countryFromResultSet(db, rs);
        } catch (SQLException sqle) {
            System.out.println("Unknown SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }



    private static Country getCountryByCode2(DB db, String code2) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM country WHERE code2 = '" + code2 + "' LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                System.out.println("Country with initials '" + code2 + "' not found");
                return null;
            }

            return countryFromResultSet(db, rs);
        } catch (SQLException sqle) {
            System.out.println("Unknown SQL Error.");
            System.out.println(sqle.getMessage());
        }
        return null;
    }
}
