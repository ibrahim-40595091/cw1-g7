package com.napier.g7cw;

import com.napier.g7cw.db.*;
import com.napier.g7cw.obj.City;
import com.napier.g7cw.obj.Continent;
import com.napier.g7cw.obj.Report;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    // Static database connection
    static DB db;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args)
    {
        // Create new database instance
        db = new DB();

        // Connect to the database, locally or with the given arguments
        if (args.length < 1) {
            db.connect("localhost:33060", 10000);
        } else {
            db.connect(args[0], Integer.parseInt(args[1]));
        }


        // Only need one Report object, it can generate any report
        // You need to provide it database access
        Report report = new Report(db);

        // Any report can be generated with
        //      Report.someReportName(list, of, arguments)
        int n = getNFromUser("Top N populated countries in the world");
        report.getWorldCountriesSortPopulation(true, n);

        // All report methods return a string with the report, but sometimes fail with the wrong arguments or database problems and report False or Null

        // You can print in 2 ways:
        //      System.out.println(Report.someReport(arguments));

        // OR
        // Once a report is finished, it stores its result in Report.lastGenerated, which you can print using
        report.display();


        // Disconnects from the database, returns True/False on success/failure but doesn't really matter
        //      since the program finishes here anyway
        db.disconnect();
    }


    // Can use later if a user needs to give input
    public static int getMultichoice(String question, ArrayList<String> answers) {
        int choiceInt;
        do {
            System.out.println(question);
            for (int i = 0; i < answers.size(); i++) {
                System.out.println("[" + (i + 1) + "]\t" + answers.get(i));
            }
            System.out.print("Choice: ");
            String choice = System.console().readLine();
            try {
                choiceInt = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                choiceInt = -1;
            }
        } while (choiceInt < 0 || choiceInt > answers.size());

        return choiceInt;
    }


    // Get N from user
    public static int getNFromUser(String question) {
        int n;

        do {
            System.out.println(question);
            System.out.print("N: ");
//            String choice = br.readLine();
            String choice = scanner.next();
            try {
                n = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                n = -1;
            }
        } while (n < 0);

//        scanner.close();

        return n;
    }
}
