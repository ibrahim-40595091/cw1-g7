package com.napier.g7cw;

import com.napier.g7cw.db.CountryDBA;
import com.napier.g7cw.db.DB;
import com.napier.g7cw.obj.Country;
import com.napier.g7cw.obj.Report;

import java.util.HashMap;

public class App {
    static DB db = null;



    public static void main(String[] args) {
        db = new DB();
        db.connect();

        // Do stuff
//        HashMap<String, String> london = db.getCapitalCity("London");
//        String capitalCityReport = generateReport(london);
//        System.out.println(capitalCityReport);

        Country a = CountryDBA.getCountry(db, "name", "United Kingdom");
        System.out.println("Country Name: " + a.LocalName);
        System.out.println("Testing");
        Report report = new Report(db);
        report.generate(a.Capital);
        report.display();


        db.disconnect();
    }
}
