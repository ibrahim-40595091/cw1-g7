package com.napier.g7cw.obj;

/**
 * Represents a City, as fetched from the MySQL database<br>
 * Includes:<br>
 * - A static NULL_CITY object for cases where a {@link City} object is needed, but there is no city to include (such as countries with no capital)<br>
 * - Country database ID as an int<br>
 * - Country name as a String<br>
 * - Country code as a String<br>
 * - City district as a String<br>
 * - City population as an int<br>
 */
public class City {
    // "Empty" city; used when a country doesn't have a capital, for example
    public static final City NULL_CITY = new City(-1, "NULL", "NULL", "NULL", -1);
    private int ID;
    private String Name;
    private String CountryCode;
    private String District;
    private int Population;

    public City(int id, String name, String countryCode, String district, int population) {
        ID = id;
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }

    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getName() { return Name; }
    public void setName(String name) { this.Name = name; }

    public String getCountryCode() { return CountryCode; }
    public void setCountryCode(String countryCode) { this.CountryCode = countryCode; }

    public String getDistrict() { return District; }
    public void setDistrict(String district) { this.District = district; }

    public int getPopulation() { return Population; }
    public void setPopulation(int population) { this.Population = population; }

}
