package com.napier.g7cw.obj;

/**
 * Represents a country as fetched from the database<br>
 * Includes:<br>
 * - Country code as a String<br>
 * - Country name as a String<br>
 * - Continent as a {@link  Continent}<br>
 * - Region as a String<br>
 * - Surface area as a float<br>
 * - Independence year as an int<br>
 * - Populations as an int<br>
 * - Life expectancy as a float<br>
 * - Current & previous Gross National Product as floats<br>
 * - Local country name as a String<br>
 * - Form of government as a String<br>
 * - Head of state as a String<br>
 * - Capital City as a {@link City}, returns a NULL_CITY if the country doesn't have a capital in the database<br>
 * - Alternate country code as a String<br>
 * - Collection of languages used in the country, as a {@link CountryLanguages} object
 */
public class Country {
    private String Code;
    private String Name;
    private Continent Continent;
    private String Region;
    private float SurfaceArea;
    private int IndepYear;
    private int Population;
    private float LifeExpectancy;
    private float GNP;
    private float GNPOld;
    private String LocalName;
    private String GovernmentForm;
    private String HeadOfState;
    private City Capital;
    private String Code2;
    private CountryLanguages Languages;



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


    public String getCode() {
        return Code;
    }
    public void setCode(String Code) {
        this.Code = Code;
    }


    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }


    public Continent getContinent() {
        return Continent;
    }
    public void setContinent(Continent Continent) {
        this.Continent = Continent;
    }


    public String getRegion() {
        return Region;
    }
    public void setRegion(String Region) {
        this.Region = Region;
    }


    public float getSurfaceArea() {
        return SurfaceArea;
    }
    public void setSurfaceArea(float SurfaceArea) {
        this.SurfaceArea = SurfaceArea;
    }


    public int getIndepYear() {
        return IndepYear;
    }
    public void setIndepYear(int IndepYear) {
        this.IndepYear = IndepYear;
    }


    public int getPopulation() {
        return Population;
    }
    public void setPopulation(int Population) {
        this.Population = Population;
    }


    public float getLifeExpectancy() {
        return LifeExpectancy;
    }
    public void setLifeExpectancy(float LifeExpectancy) {
        this.LifeExpectancy = LifeExpectancy;
    }


    public float getGNP() {
        return GNP;
    }
    public void setGNP(float GNP) {
        this.GNP = GNP;
    }


    public float getGNPOld() {
        return GNPOld;
    }
    public void setGNPOld(float GNPOld) {
        this.GNPOld = GNPOld;
    }


    public String getLocalName() {
        return LocalName;
    }
    public String setLocalName(String LocalName) {
        return this.LocalName = LocalName;
    }


    public String getGovernmentForm() {
        return GovernmentForm;
    }
    public String setGovernmentForm(String GovernmentForm) {
        return this.GovernmentForm = GovernmentForm;
    }


    public String getHeadOfState() {
        return HeadOfState;
    }
    public String setHeadOfState(String HeadOfState) {
        return this.HeadOfState = HeadOfState;
    }


    public City getCapital() {
        return Capital;
    }
    public City setCapital(City Capital) {
        return this.Capital = Capital;
    }


    public String getCode2() {
        return Code2;
    }
    public String setCode2(String Code2) {
        return this.Code2 = Code2;
    }


    public CountryLanguages getLanguages() {
        return Languages;
    }
    public CountryLanguages setLanguages(CountryLanguages Languages) {
        return this.Languages = Languages;
    }
}
