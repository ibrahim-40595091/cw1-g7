package com.napier.g7cw;

import java.sql.*;
import java.util.HashMap;

public class App {
    static DB db = null;


    /**
     * Generates a capital city report using HashMap data taken from the database
     * @param data
     * The data fetched from the database using class DB
     * @return String
     * The report generated as a String
     */
    public static String generateReport(HashMap<String, String> data)
    {
        String out;
        out = "Capital City Report:\n" +
                "\tName: " + data.get("Name") + "\n" +
                "\tCountry: " + data.get("Country") + "\n" +
                "\tPopulation: " + data.get("Population") + "\n";

        return out;
    }


    public static void main(String[] args)
    {
        db = new DB();
        db.connect();

        // Do stuff
        HashMap<String, String> london = db.getCapitalCity("London");
        String capitalCityReport = generateReport(london);
        System.out.println(capitalCityReport);

        db.disconnect();
    }
}
