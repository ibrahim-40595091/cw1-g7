package com.napier.g7cw.db;

import com.napier.g7cw.obj.Country;
import com.napier.g7cw.obj.CountryLanguages;
import com.napier.g7cw.obj.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Utility object to fetch Country Language data from the database - "Country Language DataBase Access"
 */
public class CountryLanguagesDBA {
    /**
     * Fetches a country's languages from the database using its Country Code
     * @param db
     * The database to fetch from
     * @param countryCode
     * @return
     * {@link CountryLanguages}
     */
    public static CountryLanguages getCountryLanguages(DB db, String countryCode) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM countrylanguage WHERE CountryCode = '" + countryCode + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                return new CountryLanguages(countryCode, new ArrayList<>());
            }

            ArrayList<Language> countryLanguagesArrayList = new ArrayList<>();
            do {
                countryLanguagesArrayList.add(
                    new Language(
                        rs.getString("countrycode"),
                        rs.getString("language"),
                        rs.getBoolean("isOfficial"),
                        rs.getFloat("percentage")
                    )
                );
            } while (rs.next());


            CountryLanguages cl = new CountryLanguages(
                countryCode,
                countryLanguagesArrayList
            );

            return cl;

        } catch (SQLException sqle) {
            System.out.println("Failed to get country language with Country Code " + countryCode);
            System.out.println(sqle.getMessage());
        }

        return null;
    }


    /**
     * Fetches all languages from all countries
     * @param db
     * The database to fetch information from
     * @returns
     * A HashMap of country codes mapped to {@link CountryLanguages}
     */
    public static HashMap<String, CountryLanguages> getAllCountryLanguages(DB db) {
        ArrayList<Country> allCountries = CountryDBA.getAllCountries(db);

        HashMap<String, CountryLanguages> allCountryLanguages = new HashMap<>();
        for (Country country : allCountries) {
            allCountryLanguages.put(country.getCode(), getCountryLanguages(db, country.getCode()));
        }

        return allCountryLanguages;
    }
}
