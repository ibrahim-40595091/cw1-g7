package com.napier.g7cw.obj;

/**
 * Represents a language
 *
 */
public class Language {
    public final String CountryCode;
    public final String Name;
    public final boolean IsOfficial;
    public final float Percentage;



    public Language(String countryCode, String name, boolean isOfficial, float percentage) {
        CountryCode = countryCode;
        Name = name;
        IsOfficial = isOfficial;
        Percentage = percentage;
    }

    public Language(String countryCode, String name, String isOfficial, float percentage) {
        CountryCode = countryCode;
        Name = name;
        IsOfficial = isOfficial.equals("T");
        Percentage = percentage;
    }
}
