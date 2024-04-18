package com.napier.g7cw.obj;

public class Country {
    /*
        All variables are final since country information is fetched from the database
            - it doesn't change during execution
     */
    public final String Code;
    public final String Name;
    public final Continent Continent;
    public final String Region;
    public final float SurfaceArea;
    public final int IndepYear;
    public final int Population;
    public final float LifeExpectancy;
    public final float GNP;
    public final float GNPOld;
    public final String LocalName;
    public final String GovernmentForm;
    public final String HeadOfState;
    public final City Capital;
    public final String Code2;
    public final CountryLanguages Languages;



    public Country(String code, String name, Continent continent, String region, float surfaceArea, int indepYear, int population, float lifeExpectancy, float gnp, float gnpOld, String localName, String governmentForm, String headOfState, City capital, String code2, CountryLanguages countryLanguages) {
        Code = code;
        Name = name;
        Continent = continent;
        Region = region;
        SurfaceArea = surfaceArea;
        IndepYear = indepYear;
        Population = population;
        LifeExpectancy = lifeExpectancy;
        GNP = gnp;
        GNPOld = gnpOld;
        LocalName = localName;
        GovernmentForm = governmentForm;
        HeadOfState = headOfState;
        Capital = capital;
        Code2 = code2;
        Languages = countryLanguages;
    }
}
