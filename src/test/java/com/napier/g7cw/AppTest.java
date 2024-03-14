package com.napier.g7cw;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {



    // Keeping this code in case we need it in future
    // Intention is to test whether the DB is connecting/returning data/disconnecting correctly
    // Currently only testing whether the functions can process the raw DB data correctly
    /*
    static DB db;

    @BeforeEach
    void init() {
        db = new DB();
        db.connect();
        App.db = db;
    }



    @Test
    void test_getCityPopulation() {
        ArrayList<String> cities = App.getCitiesByPopulation();
        assertNotNull(cities);
    }



    @AfterAll
    static void de_init() {
        db.disconnect();
    }
    */


    /**
     * Assert the report generator is outputting the correct data for a report on London, UK
     */
    @Test
    void capital_cities() {
        HashMap<String, String> sampleCityData = new HashMap<String, String>();
        sampleCityData.put("Name", "London");
        sampleCityData.put("Country", "United Kingdom");
        sampleCityData.put("Population", "8787892");

        String report = App.generateReport(sampleCityData);
        assertEquals("Capital City Report:\n" +
                "\tName: London\n" +
                "\tCountry: United Kingdom\n" +
                "\tPopulation: 8787892\n", report);
    }
}
