package com.napier.g7cw.obj;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a country's languages<br>
 * Includes:<br>
 * - Country Code as a string<br>
 * - An ArrayList of {@link Language} objects, which are the official languages of the country<br>
 * - An ArrayList of {@link Language} object, which are other languages spoken in the country<br>
 */
public class CountryLanguages {
    private String CountryCode;
    private ArrayList<Language> Official;
    private ArrayList<Language> Other;

    public CountryLanguages(String countryCode, ArrayList<Language> officialLanguage, ArrayList<Language> otherLanguages) {
        CountryCode = countryCode;
        Official = officialLanguage;
        Other = otherLanguages;
    }

    public CountryLanguages(String countryCode, ArrayList<Language> allLanguages) {
        CountryCode = countryCode;
        Official = new ArrayList<>();
        Other = new ArrayList<>();

        for (Language language : allLanguages) {
            // Skip languages not in this country
            if (!language.getCountryCode().equals(countryCode)) { continue; }

            if (language.getIsOfficial()) {
                Official.add(language);
            } else {
                Other.add(language);
            }
        }
    }

    public ArrayList<Language> All() {
        ArrayList<Language> all = Official;
        all.addAll(Other);
        return all;
    }



    public String getCountryCode() {
        return CountryCode;
    }
    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }


    public ArrayList<Language> getOfficial() {
        return Official;
    }
    public void setOfficial(ArrayList<Language> Official) {
        this.Official = Official;
    }


    public ArrayList<Language> getOther() {
        return Other;
    }
    public void setOther(ArrayList<Language> Other) {
        this.Other = Other;
    }


}
