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
}
