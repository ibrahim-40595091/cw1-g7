package com.napier.g7cw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    */



    @Test
    void test_getCityPopulation() {
        ArrayList<String> cities = App.getCitiesByPopulation();
        assertNotNull(cities);
    }


    /*
    @AfterAll
    static void de_init() {
        db.disconnect();
    }
    */


}
