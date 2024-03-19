package com.napier.g7cw.db;

import com.napier.g7cw.obj.Continent;
import com.napier.g7cw.obj.Country;

import java.sql.*;

/**
 * Utility object to fetch country data from the database - "Country DataBase Access"
 */
public class CountryDBA {
    public static Country getCountry(DB db, String attribute, String value) {
        switch (attribute) {
            case "name":
                return getCountryByName(db, value);
            case "capital":
                return getCountryByCapital(db, value);
            case "code2":
                return getCountryByCode2(db, value);
            default: // case "code"
                return getCountryByCode(db, value);
        }
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
                CityDBA.getCityByID(db, rs.getInt("Capital")),
                rs.getString("Code2"),
                CountryLanguagesDBA.getCountryLanguage(db, rs.getString("Code"))
            );
            return c;
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
        return null;
    }



    private static Country getCountryByCapital(DB db, String capital) {
        return null;
    }



    private static Country getCountryByCode(DB db, String code) {
        return null;
    }



    private static Country getCountryByCode2(DB db, String code2) {
        return null;
    }
}
