package com.napier.g7cw;

import com.napier.g7cw.db.*;
import com.napier.g7cw.obj.City;
import com.napier.g7cw.obj.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {
    static DB db;


    public static void main(String[] args)
    {
        db = new DB();

        if (args.length < 1) {
            db.connect("localhost:33060", 10000);
        } else {
            db.connect(args[0], Integer.parseInt(args[1]));
        }

        Report report = new Report(db);
        report.getContinentCapitalCitiesSortPopulation("Europe", true, 10);
        report.display();

        db.disconnect();
    }
}
