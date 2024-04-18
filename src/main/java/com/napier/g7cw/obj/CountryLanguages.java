package com.napier.g7cw.obj;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a country's languages
 * <p></p>
 */
public class CountryLanguages {
    public final String CountryCode;
    public final ArrayList<Language> Official;
    public final ArrayList<Language> Other;

    public CountryLanguages(String countryCode, ArrayList<Language> officialLanguage, ArrayList<Language> otherLanguages) {
        CountryCode = countryCode;
        Official = officialLanguage;
        Other = otherLanguages;
    }

    public CountryLanguages(String countryCode, ArrayList<Language> allLanguages) {
        CountryCode = countryCode;
        //Language foundOfficialLanguage = null;
        Official = new ArrayList<>();
        Other = new ArrayList<>();

        for (Language language : allLanguages) {
            // Skip languages not in this country
            if (!language.CountryCode.equals(countryCode)) { continue; }

            if (language.IsOfficial) {
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
}
