package com.napier.g7cw.obj;

/**
 * Represents a language as fetched from the database<br>
 * Includes:<br>
 * - The country code of the country which this entry applies to as a String<br>
 * - The name of the language as a String<br>
 * - A boolean indicating whether this is an official language of the country code stated<br>
 * - The percentage of the country which speaks the language, as a float<br>
 */
public class Language {
    private String CountryCode;
    private String Name;
    private boolean IsOfficial;
    private float Percentage;



    /** Language constructor for boolean isOfficial parameters */
    public Language(String countryCode, String name, boolean isOfficial, float percentage) {
        CountryCode = countryCode;
        Name = name;
        IsOfficial = isOfficial;
        Percentage = percentage;
    }

    /** Language constructor for String isOfficial parameters */
    public Language(String countryCode, String name, String isOfficial, float percentage) {
        CountryCode = countryCode;
        Name = name;
        IsOfficial = isOfficial.equals("T");
        Percentage = percentage;
    }


    public String getCountryCode() {
        return CountryCode;
    }
    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }


    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }


    public boolean getIsOfficial() {
        return IsOfficial;
    }
    public void setIsOfficial(boolean IsOfficial) {
        this.IsOfficial = IsOfficial;
    }


    public float getPercentage() {
        return Percentage;
    }
    public void setPercentage(float Percentage) {
        this.Percentage = Percentage;
    }


}
