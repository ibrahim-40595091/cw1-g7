package com.napier.g7cw;

import com.napier.g7cw.db.CityDBA;
import com.napier.g7cw.db.CountryDBA;
import com.napier.g7cw.db.CountryLanguagesDBA;
import com.napier.g7cw.db.DB;
import com.napier.g7cw.obj.City;
import com.napier.g7cw.obj.Continent;
import com.napier.g7cw.obj.Country;
import com.napier.g7cw.obj.CountryLanguages;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static DB db;

    @BeforeAll
    static void init() {
        db = new DB();
        db.connect("localhost:33060", 10000);
    }

    @Test
    void testGetCityByID() {
        City city = CityDBA.getCityByID(db, 1);
        assertNotNull(city);
        assertEquals(1, city.getID());
        assertEquals("Kabul", city.getName());
        assertEquals("AFG", city.getCountryCode());
        assertEquals("Kabol", city.getDistrict());
        assertEquals(1780000, city.getPopulation());
    }

    @Test
    void testGetCityByName() {
        City city = CityDBA.getCityByName(db, "Kabul");
        assertNotNull(city);
        assertEquals(1, city.getID());
        assertEquals("Kabul", city.getName());
        assertEquals("AFG", city.getCountryCode());
        assertEquals("Kabol", city.getDistrict());
        assertEquals(1780000, city.getPopulation());
    }

    @Test
    void testGetCitiesByCountry() {
        ArrayList<City> cities = CityDBA.getCitiesByCountry(db, "GBR", true);
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertEquals("London", cities.get(0).getName());
    }

    @Test
    void testGetCitiesByContinent() {
        ArrayList<City> cities = CityDBA.getCitiesByContinent(db, Continent.Europe.getName(), true);
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertEquals("Moscow", cities.get(0).getName());
    }

    @Test
    void testGetCitiesByRegion() {
        ArrayList<City> cities = CityDBA.getCitiesByRegion(db, "Eastern Europe", true);
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertEquals("Moscow", cities.get(0).getName());
    }

    @Test
    void testGetCitiesByDistrict() {
        ArrayList<City> cities = CityDBA.getCitiesByDistrict(db, "Scotland", true);
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertEquals("Glasgow", cities.get(0).getName());
    }

    @Test
    void testGetCityNull() {
        City city = CityDBA.getCityByID(db, -1);
        assertNotNull(city);
        assertEquals(-1, city.getID());
        assertEquals("NULL", city.getName());
        assertEquals("NULL", city.getCountryCode());
        assertEquals("NULL", city.getDistrict());
        assertEquals(-1, city.getPopulation());
    }

    @Test
    void testGetAllCities() {
        ArrayList<City> cities = CityDBA.getAllCities(db, true);
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertEquals("Mumbai (Bombay)", cities.get(0).getName());
    }

    @Test
    void testGetAllCapitalCities() {
        ArrayList<City> cities = CityDBA.getAllCapitalCities(db, true);
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertEquals("Seoul", cities.get(0).getName());
    }

    @Test
    void testGetCapitalCitiesByContinent() {
        ArrayList<City> cities = CityDBA.getCapitalCitiesByContinent(db, Continent.Europe.getName(), true);
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertEquals("Moscow", cities.get(0).getName());
    }

    @Test
    void testGetCapitalCitiesByRegion() {
        ArrayList<City> cities = CityDBA.getCapitalCitiesByRegion(db, "Eastern Europe", true);
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
        assertEquals("Moscow", cities.get(0).getName());
    }

    @Test
    void testGetCountry() {
        Country country = CountryDBA.getCountry(db, "AFG");
        assertNotNull(country);
        assertEquals("AFG", country.getCode());
        assertEquals("Afghanistan" ,country.getName());
        assertEquals(Continent.Asia.getName(), country.getContinent().getName());
        assertEquals("Southern and Central Asia", country.getRegion());
        assertEquals(22720000, country.getPopulation());
        assertEquals("Kabul", country.getCapital().getName());
    }

    @Test
    void testGetAllCountries() {
        ArrayList<Country> countries = CountryDBA.getAllCountries(db);
        assertNotNull(countries);
        assertTrue(countries.size() > 0);
        assertEquals("Aruba", countries.get(0).getName());
    }

    @Test
    void testGetAllCountriesByPopulation() {
        ArrayList<Country> countries = CountryDBA.getAllCountriesSortPopulation(db, true);
        assertNotNull(countries);
        assertTrue(countries.size() > 0);
        assertEquals("China", countries.get(0).getName());
    }

    @Test
    void testGetCountriesByContinent() {
        ArrayList<Country> countries = CountryDBA.getCountriesByContinent(db, Continent.Europe.getName(), true);
        assertNotNull(countries);
        assertTrue(countries.size() > 0);
        assertEquals("Russian Federation", countries.get(0).getName());
    }

    @Test
    void testGetCountriesByRegion() {
        ArrayList<Country> countries = CountryDBA.getCountriesByRegion(db, "Eastern Europe", true);
        assertNotNull(countries);
        assertTrue(countries.size() > 0);
        assertEquals("Russian Federation", countries.get(0).getName());
    }

    @Test
    void testGetAllCountryLanguages() {
        HashMap<String, CountryLanguages> cl = CountryLanguagesDBA.getAllCountryLanguages(db);
        assertNotNull(cl);
        assertTrue(cl.size() > 0);
        assertEquals(5, cl.get("AFG").All().size());
    }

    @Test
    void testGetCountryLanguagesByName() {
        ArrayList<CountryLanguages> cl = CountryLanguagesDBA.getAllCountryLanguagesByName(db, "English");
        assertNotNull(cl);
        assertTrue(cl.size() > 0);
        assertEquals("DNK", cl.get(0).getCountryCode());
    }


    @AfterAll
    static void disconnect() {
        db.disconnect();
    }
}
