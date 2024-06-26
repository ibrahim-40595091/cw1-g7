package com.napier.g7cw.obj;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a language as fetched from the database, including the number that speak it<br>
 * Includes:<br>
 * - The name of the language as a String<br>
 * - The population of the area which speaks the language, as an int<br>
 */
public class LanguageWithPopulation implements Comparable<LanguageWithPopulation> {
    private String Name;
    private long Population;


    public LanguageWithPopulation(String Name, long Population) {
        this.Name = Name;
        this.Population = Population;
    }


    /**
     * LanguageWithPopulation constructor from a Language
     */
    public LanguageWithPopulation(Language language, long Population) {
        this.Name = language.getName();
        this.Population = Population;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public long getPopulation() {
        return Population;
    }
    public void setPopulation(long Population) {
        this.Population = Population;
    }


    @Override
    public int compareTo(LanguageWithPopulation o) {
        long diff = Population - o.Population;
        if (diff > 0) { return 1; }
        if (diff < 0) { return -1; }
        return 0;
    }


    /**
     * Method to find the index of a language in a list of {@link LanguageWithPopulation}s
     * @param languages
     * The ArrayList of {@link LanguageWithPopulation} to search through
     * @param Name
     * The name of the language to search for
     * @return
     * The index of the {@link LanguageWithPopulation} in the ArrayList, or -1 if not found
     */
    public static int indexOf(ArrayList<LanguageWithPopulation> languages, String Name) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(Name)) {
                return i;
            }
        }
        return -1;
    }
}
