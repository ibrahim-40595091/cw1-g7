package com.napier.g7cw;

import com.napier.g7cw.db.*;
import com.napier.g7cw.obj.City;
import com.napier.g7cw.obj.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        if (args.length < 1) {
            db.connect("localhost:33060", 10000);
        } else {
            db.connect(args[0], Integer.parseInt(args[1]));
        }

        // Do stuff
        City london = CityDBA.getCityByName(db, "London");
        if (london == null) {
            System.out.println("City not found.");
            db.disconnect();
            return;
        }
        Report report = new Report(db);
        String cityReport = report.generate(london);
        System.out.println(cityReport);

        db.disconnect();
    }
}
