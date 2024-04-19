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


    /**
     * Fetches all languages by a specific name, from all countries which use that language
     * @param db
     * The database to fetch information from
     * @param name
     * The name of the language to search for
     * @return
     * An ArrayList of {@link CountryLanguages} from all countries which use the language, or null
     */
    public static ArrayList<CountryLanguages> getAllCountryLanguagesByName(DB db, String name) {
        try {
            Statement stmt = db.con.createStatement();
            String query = "SELECT * FROM countrylanguage WHERE Language = '" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            HashMap<String, ArrayList<Language>> countryLanguagesHashMap = new HashMap<>();
            while (rs.next()) {
                String countryCode = rs.getString("CountryCode");
                if (!countryLanguagesHashMap.containsKey(countryCode)) {
                    countryLanguagesHashMap.put(countryCode, new ArrayList<>());
                }

                countryLanguagesHashMap.get(countryCode).add(
                    new Language(
                        rs.getString("CountryCode"),
                        rs.getString("Language"),
                        rs.getBoolean("IsOfficial"),
                        rs.getFloat("Percentage")
                    )
                );
            }
            
            ArrayList<CountryLanguages> countryLanguages = new ArrayList<>();
            for (String languageCountryCode : countryLanguagesHashMap.keySet()) {
                countryLanguages.add(new CountryLanguages(languageCountryCode, countryLanguagesHashMap.get(languageCountryCode)));
            }
            
            return countryLanguages;

        } catch (SQLException sqle) {
            System.out.println("Failed to get country language with Language " + name);
            System.out.println(sqle.getMessage());
        }

        return null;
    }
}
