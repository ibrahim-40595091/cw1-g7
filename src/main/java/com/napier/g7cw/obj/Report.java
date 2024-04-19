package com.napier.g7cw.obj;

import com.napier.g7cw.db.*;

import javax.print.DocFlavor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * A class to generate reports given various types of information
 * <p>Contains a lastGenerated variable, used for various functions which print Reports</p>
 * <p><b>Usage:</b></p>
 * <p>Create a new Report object, attach a DB object, .generate() a report (returns a string), the return value of .generate() can be printed using .display()</p>
 */
public class Report {
    DB db;
    private String lastGenerated;

    /**
     * Constructor
     * @param db
     * A DB object to "attach" to the Report class to allow access to additional data as-needed
     */
    public Report(DB db) {
        this.db = db;
        lastGenerated = "";
    }


    /**
     * Report generator for cities
     * @param c
     * The provided city to generate a report for
     * @return
     * A string containing the report generated
     */
    public String generate(City c) {
        lastGenerated = "City Report:\n" +
                "\tName: " + c.getName() + "\n" +
                // Use countryCode to get country from database
                "\tCountry: " + CountryDBA.getCountry(db, c.getCountryCode()).getLocalName() + "\n" +
                "\tPopulation: " + c.getPopulation() + "\n";
        return lastGenerated;
    }


    /**
     * Report generator for continents
     * @param c
     * The provided continent to generate a report for
     * @return
     * A string containing the report generated
     */
    public String generate(Continent c) {
        lastGenerated = "Continent: " + c.getName();
        return lastGenerated;
    }


    /**
     * Report generator for country
     * @param c
     * The provided country to generate a report for
     * @return
     * A string containing the report generated
     */
    public String generate(Country c) {
        Report capitalReport = new Report(db);
        Report countryLanguagesReport = new Report(db);
        capitalReport.generate(c.getCapital());
        lastGenerated = "Country Report:\n" +
                "\tCode: " + c.getCode() + "\n" +
                "\tName: " + c.getName() + "\n" +
                "\tContinent: " + c.getContinent() + "\n" +
                "\tRegion: " + c.getRegion() + "\n" +
                "\tCapital: " + capitalReport.generate(c.getCapital()) + "\n";
        return lastGenerated;
    }


    /**
     * Report generator for country languages
     * @param cl
     * The provided languages to generate a report for
     * @return
     * A string containing the report generated
     */
    public String generate(CountryLanguages cl) {
        // Catch empty languages
        if (cl.getOfficial().isEmpty() && cl.getOther().isEmpty()) {
            lastGenerated = "Country Languages Report:\n" +
                    "\tNo languages found for " + CountryDBA.getCountry(db, cl.getCountryCode());
            return lastGenerated;
        }

        lastGenerated = "Country Languages Report:\n" +
                "\tCountry Code: " + cl.getCountryCode() + "\n" +
                "\tOfficial Languages:\n";
        for (Language l : cl.getOfficial()) {
            lastGenerated += "\t\t" + l.getName() + " (" + l.getPercentage() + "%)\n";
        }
        lastGenerated += "\tOther Languages:\n";
        for (Language l : cl.getOther()) {
            lastGenerated += "\t\t" + l.getName() + " (" + l.getPercentage() + "%)\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for a single language
     * @param l
     * The provided languages to generate a report for
     * @return
     * A string containing the report generated
     */
    public String generate(Language l) {
        lastGenerated = "Language Report:\n" +
                "\tName: " + l.getName() + "\n" +
                "\tCountry Code: " + l.getCountryCode() + "\n" +
                "\tIs Official: " + l.getIsOfficial() + "\n" +
                "\tPercentage: " + l.getPercentage() + "\n";
        return lastGenerated;
    }


    /**
     * Report generator for all countries in the world, sorted by population
     * @param largestFirst
     * Whether to sort the countries by largest population first
     */
    public String getWorldCountriesSortPopulation(boolean largestFirst) {
        return getWorldCountriesSortPopulation(largestFirst, -1);
    }
    /**
     * Report generator for N countries in the world, sorted by population
     * @param largestFirst
     * Whether to sort the countries by largest population first
     */
    public String getWorldCountriesSortPopulation(boolean largestFirst, int n) {
        ArrayList<Country> countries = CountryDBA.getAllCountriesSortPopulation(db, largestFirst);

        if (countries == null || countries.isEmpty()) {
            lastGenerated = "No countries found";
            return lastGenerated;
        }

        lastGenerated = "World Countries Report:\n";
        if (n == -1) { n = countries.size(); }
        for (int i=0; i<n; i++) {
            Country c = countries.get(i);
            lastGenerated += "\t" + (i+1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }

    /**
     * Report generator for all countries in a specific continent, sorted by population
     * @param continent
     * The continent to get countries from
     * @param largestFirst
     * Whether to sort the countries by largest population first
     * @return
     * A string containing the report generated
     */
    public String getContinentCountriesSortPopulation(String continent, boolean largestFirst) {
        return getContinentCountriesSortPopulation(continent, largestFirst, -1);
    }
    /**
     * Report generator for N countries in a specific continent, sorted by population
     * @param continent
     * The continent to get countries from
     * @param largestFirst
     * Whether to sort the countries by largest population first
     * @param n
     * The number of countries to display
     * @return
     * A string containing the report generated
     */
    public String getContinentCountriesSortPopulation(String continent, boolean largestFirst, int n) {
        ArrayList<Country> countries = CountryDBA.getCountriesByContinent(db, continent, largestFirst);

        if (countries == null || countries.isEmpty()) {
            lastGenerated = "Continent " + continent + " not found";
            return lastGenerated;
        }

        lastGenerated = continent + " Countries Report:\n";
        if (n == -1) { n = countries.size(); }
        for (int i=0; i<n; i++) {
            Country c = countries.get(i);
            lastGenerated += "\t" + (i+1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all countries in a specific region, sorted by population
     * @param region
     * The region to get countries from
     * @param largestFirst
     * Whether to sort the countries by largest population first
     * @return
     * A string containing the report generated
     */
    public String getRegionCountriesSortPopulation(String region, boolean largestFirst) {
        return getRegionCountriesSortPopulation(region, largestFirst, -1);
    }
    /**
     * Report generator for N countries in a specific region, sorted by population
     * @param region
     * The region to get countries from
     * @param largestFirst
     * Whether to sort the countries by largest population first
     * @param n
     * The number of countries to display
     * @return
     * A string containing the report generated
     */
    public String getRegionCountriesSortPopulation(String region, boolean largestFirst, int n) {
        ArrayList<Country> countries = CountryDBA.getCountriesByRegion(db, region, largestFirst);

        if (countries == null || countries.isEmpty()) {
            lastGenerated = "Region " + region + " not found";
            return lastGenerated;
        }

        lastGenerated = region + " Countries Report:\n";
        if (n == -1) { n = countries.size(); }
        for (int i=0; i<n; i++) {
            Country c = countries.get(i);
            lastGenerated += "\t" + (i+1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all cities in the world, sorted by population
     * @param largestFirst
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getWorldCitiesSortPopulation(boolean largestFirst) {
        return getWorldCitiesSortPopulation(largestFirst, -1);
    }
    /**
     * Report generator for N cities in the world, sorted by population
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @param n
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getWorldCitiesSortPopulation(boolean largestFirst, int n) {
        ArrayList<City> cities = CityDBA.getAllCities(db, largestFirst);

        if (cities == null || cities.isEmpty()) {
            lastGenerated = "No cities found";
            return lastGenerated;
        }

        lastGenerated = "World Cities Report:\n";
        if (n == -1) {
            n = cities.size();
        }
        n = Math.min(n, cities.size());
        for (int i = 0; i < n; i++) {
            City c = cities.get(i);
            lastGenerated += "\t" + (i + 1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all cities in a specific continent, sorted by population
     * @param continent
     * The continent to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @return
     * A string containing the report generated
     */
    public String getContinentCitiesSortPopulation(String continent, boolean largestFirst) {
        return getContinentCitiesSortPopulation(continent, largestFirst, -1);
    }
    /**
     * Report generator for N cities in a specific continent, sorted by population
     * @param continent
     * The continent to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @param n
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getContinentCitiesSortPopulation(String continent, boolean largestFirst, int n) {
        ArrayList<City> cities = CityDBA.getCitiesByContinent(db, continent, largestFirst);

        if (cities == null || cities.isEmpty()) {
            lastGenerated = "Continent " + continent + " not found";
            return lastGenerated;
        }

        lastGenerated = continent + " Cities Report:\n";
        if (n == -1) {
            n = cities.size();
        }
        n = Math.min(n, cities.size());
        for (int i = 0; i < n; i++) {
            City c = cities.get(i);
            lastGenerated += "\t" + (i + 1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all cities in a specific region, sorted by population
     * @param region
     * The region to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @return
     * A string containing the report generated
     */
    public String getRegionCitiesSortPopulation(String region, boolean largestFirst) {
        return getRegionCitiesSortPopulation(region, largestFirst, -1);
    }
    /**
     * Report generator for N cities in a specific region, sorted by population
     * @param region
     * The region to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @param n
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getRegionCitiesSortPopulation(String region, boolean largestFirst, int n) {
        ArrayList<City> cities = CityDBA.getCitiesByRegion(db, region, largestFirst);

        if (cities == null || cities.isEmpty()) {
            lastGenerated = "Region " + region + " not found";
            return lastGenerated;
        }

        lastGenerated = region + " Cities Report:\n";
        if (n == -1) {
            n = cities.size();
        }
        n = Math.min(n, cities.size());
        for (int i = 0; i < n; i++) {
            City c = cities.get(i);
            lastGenerated += "\t" + (i + 1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all cities in a specific country, sorted by population
     * @param country
     * The country to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @return
     * A string containing the report generated
     */
    public String getCountryCitiesSortPopulation(String country, boolean largestFirst) {
        return getCountryCitiesSortPopulation(country, largestFirst, -1);
    }
    /**
     * Report generator for N cities in a specific country, sorted by population
     * @param country
     * The country to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @param n
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getCountryCitiesSortPopulation(String country, boolean largestFirst, int n) {
        ArrayList<City> cities = CityDBA.getCitiesByCountry(db, country, largestFirst);

        if (cities == null || cities.isEmpty()) {
            lastGenerated = "Country " + country + " not found";
            return lastGenerated;
        }

        lastGenerated = country + " Cities Report:\n";
        if (n == -1) {
            n = cities.size();
        }
        n = Math.min(n, cities.size());
        for (int i = 0; i < n; i++) {
            City c = cities.get(i);
            lastGenerated += "\t" + (i + 1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all cities in a specific district, sorted by population
     * @param district
     * The district to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @return
     * A string containing the report generated
     */
    public String getDistrictCitiesSortPopulation(String district, boolean largestFirst) {
        return getDistrictCitiesSortPopulation(district, largestFirst, -1);
    }
    /**
     * Report generator for N cities in a specific district, sorted by population
     * @param district
     * The district to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @param n
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getDistrictCitiesSortPopulation(String district, boolean largestFirst, int n) {
        ArrayList<City> cities = CityDBA.getCitiesByDistrict(db, district, largestFirst);

        if (cities == null || cities.isEmpty()) {
            lastGenerated = "District " + district + " not found";
            return lastGenerated;
        }

        lastGenerated = district + " Cities Report:\n";
        if (n == -1) {
            n = cities.size();
        }
        n = Math.min(n, cities.size());
        for (int i = 0; i < n; i++) {
            City c = cities.get(i);
            lastGenerated += "\t" + (i + 1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all capital cities in the world, sorted by population
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @return
     * A string containing the report generated
     */
    public String getWorldCapitalCitiesSortPopulation(boolean largestFirst) {
        return getWorldCapitalCitiesSortPopulation(largestFirst, -1);
    }
    /**
     * Report generator for N capital cities in the world, sorted by population
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @param n
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getWorldCapitalCitiesSortPopulation(boolean largestFirst, int n) {
        ArrayList<City> cities = CityDBA.getAllCapitalCities(db, largestFirst);

        if (cities == null || cities.isEmpty()) {
            lastGenerated = "No capital cities found";
            return lastGenerated;
        }

        lastGenerated = "World Capital Cities Report:\n";
        if (n == -1) {
            n = cities.size();
        }
        n = Math.min(n, cities.size());
        for (int i = 0; i < n; i++) {
            City c = cities.get(i);
            lastGenerated += "\t" + (i + 1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all capital cities in a specific continent, sorted by population
     * @param continent
     * The continent to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @return
     * A string containing the report generated
     */
    public String getContinentCapitalCitiesSortPopulation(String continent, boolean largestFirst) {
        return getContinentCapitalCitiesSortPopulation(continent, largestFirst, -1);
    }
    /**
     * Report generator for N capital cities in a specific continent, sorted by population
     * @param continent
     * The continent to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @param n
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getContinentCapitalCitiesSortPopulation(String continent, boolean largestFirst, int n) {
        ArrayList<City> cities = CityDBA.getCapitalCitiesByContinent(db, continent, largestFirst);

        if (cities == null || cities.isEmpty()) {
            lastGenerated = "Continent " + continent + " not found";
            return lastGenerated;
        }

        lastGenerated = continent + " Capital Cities Report:\n";
        if (n == -1) {
            n = cities.size();
        }
        n = Math.min(n, cities.size());
        for (int i = 0; i < n; i++) {
            City c = cities.get(i);
            lastGenerated += "\t" + (i + 1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }



    /**
     * Report generator for all capital cities in a specific region, sorted by population
     * @param region
     * The region to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @return
     * A string containing the report generated
     */
    public String getRegionCapitalCitiesSortPopulation(String region, boolean largestFirst) {
        return getRegionCapitalCitiesSortPopulation(region, largestFirst, -1);
    }
    /**
     * Report generator for N capital cities in a specific region, sorted by population
     * @param region
     * The region to get cities from
     * @param largestFirst
     * Whether to sort the cities by largest population first
     * @param n
     * The number of cities to display
     * @return
     * A string containing the report generated
     */
    public String getRegionCapitalCitiesSortPopulation(String region, boolean largestFirst, int n) {
        ArrayList<City> cities = CityDBA.getCapitalCitiesByRegion(db, region, largestFirst);

        if (cities == null || cities.isEmpty()) {
            lastGenerated = "Region " + region + " not found";
            return lastGenerated;
        }

        lastGenerated = region + " Capital Cities Report:\n";
        if (n == -1) {
            n = cities.size();
        }
        n = Math.min(n, cities.size());
        for (int i = 0; i < n; i++) {
            City c = cities.get(i);
            lastGenerated += "\t" + (i + 1) + ".\t" + c.getName() + " (" + c.getPopulation() + ")\n";
        }

        return lastGenerated;
    }


    /**
     * Report generator for all spoken languages in the world, sorted by most spoken
     * @param largestFirst
     * Whether to sort languages by most-spoken first
     * @return
     * A string containing the report generated
     */
    public String getWorldLanguagesSortMostSpoken(boolean largestFirst) {
        return getWorldLanguagesSortMostSpoken(largestFirst, -1);
    }

    /**
     * Report generator for N most spoken languages in the world
     * @param largestFirst
     * Whether to sort languages by most-spoken first
     * @param n
     * The number of languages to display
     * @return
     * A string containing the report generated
     */
    public String getWorldLanguagesSortMostSpoken(boolean largestFirst, int n) {
        // Get a list of all languages from all countries
        HashMap<String, CountryLanguages> languages = CountryLanguagesDBA.getAllCountryLanguages(db);

        if (languages.isEmpty()) {
            lastGenerated = "No languages fetched from database.";
            return lastGenerated;
        }

        // For each country's list of languages, collect them into a single list and tally all populations
        ArrayList<LanguageWithPopulation> worldLanguages = new ArrayList<>();
        for (CountryLanguages l : languages.values()) {
            ArrayList<Language> allCountryLanguages = l.getOfficial();
            allCountryLanguages.addAll(l.getOther());

            for (Language countryLanguage : allCountryLanguages) {
                int population = (int)(
                        // Get the country for this language
                        CountryDBA.getCountry(db, countryLanguage.getCountryCode())
                                // Get its full population
                                .getPopulation()
                                // Get the population that speaks this language
                                * countryLanguage.getPercentage()
                );

                // Find the index of the language in the list of all languages
                int worldLanguagesIndex = LanguageWithPopulation.indexOf(worldLanguages, countryLanguage.getName());
                if (worldLanguagesIndex == -1) {
                    // Add a new entry if one doesn't exist for this language
                    worldLanguages.add(new LanguageWithPopulation(countryLanguage.getName(), population));
                    continue;
                }

                // Get the current LanguageWithPopulation object and update its population value
                LanguageWithPopulation current = worldLanguages.get(worldLanguagesIndex);
                current.setPopulation(current.getPopulation() + population);
                worldLanguages.set(worldLanguagesIndex, current); // Set new value
            }
        }


        if (worldLanguages.isEmpty()) {
            lastGenerated = "No world languages fetched from database.";
            return lastGenerated;
        }
        // LanguageWithPopulation is comparable by population
        Collections.sort(worldLanguages);


        lastGenerated = "World Languages Report:\n";
        if (n == -1) {
            n = worldLanguages.size();
        }
        n = Math.min(n, worldLanguages.size());
        // Invert n if we're working backward in the sorted list (since we want smallest first)
        if (!largestFirst) { n = worldLanguages.size() - n; }

        if (largestFirst) {
            for (int i = 0; i < n; i++) {
                lastGenerated += "\t" + (i + 1) + ".\t" + worldLanguages.get(i).getName() + " is spoken by " + worldLanguages.get(i).getPopulation() + " people\n";
            }
        } else {
            for (int i = n - 1; i >= n; i--) {
                lastGenerated += "\t" + (i + 1) + ".\t" + worldLanguages.get(i).getName() + " is spoken by " + worldLanguages.get(i).getPopulation() + " people\n";
            }
        }

        return lastGenerated;
    }


    /**
     * Report generator for N most spoken languages in the world
     * @param largestFirst
     * Whether to sort languages by most-spoken first
     * @param n
     * The number of languages to display
     * @return
     * A string containing the report generated
     */
//    public String getWorldLanguageSortMostSpoken(String language, boolean largestFirst, int n) {
//
//    }



    /**
     * Prints the last report generated
     */
    public void display() {
        System.out.println(lastGenerated);
    }

    /**
     * Prints the last report generated by a Report object
     * @param report
     * The {@link com.napier.g7cw.obj.Report} object to print the report of
     */
    public static void display(Report report) {
        report.display();
    }


    /**
     * String representation of a Report is its last generated report
     */
    @Override
    public String toString() {
        return lastGenerated;
    }
}
