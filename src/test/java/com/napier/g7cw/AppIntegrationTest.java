package com.napier.g7cw;

import com.napier.g7cw.db.CityDBA;
import com.napier.g7cw.db.DB;
import com.napier.g7cw.obj.City;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static DB db;

    @BeforeAll
    static void init() {
        db = new DB();
        db.connect("localhost:33060", 10000);
    }

    @Test
    void testGetCity() {
         City city = CityDBA.getCityByID(db, 1);
         assertNotNull(city);
         assertEquals(city.ID, 1);
         assertEquals(city.Name, "Kabul");
         assertEquals(city.CountryCode, "AFG");
         assertEquals(city.District, "Kabol");
         assertEquals(city.Population, 1780000);
    }

    @AfterAll
    static void disconnect() {
        db.disconnect();
    }
}
