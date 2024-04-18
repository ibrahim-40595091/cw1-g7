package com.napier.g7cw;

import com.napier.g7cw.db.CityDBA;
import com.napier.g7cw.db.CountryDBA;
import com.napier.g7cw.db.DB;
import com.napier.g7cw.obj.City;
import com.napier.g7cw.obj.Country;
import com.napier.g7cw.obj.Report;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {
    static DB db;


    /* None of these tests work on GitHub Actions
        - Docker containers and databases don't seem to work with it */
    @BeforeAll
    static void init() {
        db = new DB();
        db.connect("localhost:33060", 10000);
    }


    @Test
    void testGetCity() {
//        City city = CityDBA.getCityByID(db, 1);
//        assertNotNull(city);
//        assertEquals(city.ID, 1);
//        assertEquals(city.Name, "Kabul");
//        assertEquals(city.CountryCode, "AFG");
//        assertEquals(city.District, "Kabol");
//        assertEquals(city.Population, 1780000);
    }

    @Test
    void testGetCityNull() {
//        City city = CityDBA.getCityByID(db, 0);
//        assertEquals(city, City.NULL_CITY);
    }

    @Test
    void testGetCountry(){
//        Country country = CountryDBA.getCountry(db, "AFG");
//        assertNotNull(country);
//        assertEquals(country.Code, "AFG");
//        assertEquals(country.Name, "Afghanistan");
//        assertEquals(country.Continent.Name, "Asia");
//        assertEquals(country.Region, "Southern and Central Asia");
//        assertEquals(country.Population, 22720000);
//        assertEquals(country.Capital.CountryCode, "AFG");
    }


    @AfterAll
    static void tearDown() {
//        db.disconnect();
    }
}
