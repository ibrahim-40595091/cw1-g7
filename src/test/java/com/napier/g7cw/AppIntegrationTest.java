package com.napier.g7cw;

import com.napier.g7cw.db.CityDBA;
import com.napier.g7cw.db.DB;
import com.napier.g7cw.obj.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;
    static DB db;

    @BeforeAll
    static void init() {
        db = new DB();
        db.connect("localhost:33060", 10000);
    }

    @Test
    void testGetCity() {
        City city = CityDBA.getCityByName(db, "London");
        assertNotNull(city);
        assertEquals("London", city.Name);
        assertEquals("GBR", city.CountryCode);
        assertEquals("England", city.District);
        assertEquals(7285000, city.Population);
    }
}
