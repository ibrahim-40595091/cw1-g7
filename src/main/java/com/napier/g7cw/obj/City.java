package com.napier.g7cw.obj;

public class City {
    public static final City NULL_CITY = new City(-1, "NULL", "NULL", "NULL", -1);
    public final int ID;
    public final String Name;
    public final String CountryCode;
    public final String District;
    public final int Population;

    public City(int id, String name, String countryCode, String district, int population) {
        ID = id;
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }
}
