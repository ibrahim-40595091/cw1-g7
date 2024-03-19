package com.napier.g7cw.obj;

import com.napier.g7cw.db.DB;
import com.napier.g7cw.db.CityDBA;
import com.napier.g7cw.db.CountryLanguagesDBA;
import com.napier.g7cw.db.CountryDBA;


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
     * A string containing a
     */
    public String generate(City c) {
        lastGenerated = "City Report:\n" +
                "\tName: " + c.Name + "\n" +
                // Use countryCode to get country from database
                "\tCountry: " + CountryDBA.getCountry(db, c.CountryCode).LocalName + "\n" +
                "\tDistrict: " + c.District + "\n" +
                "\tPopulation: " + c.Population + "\n";
        return lastGenerated;
    }


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
