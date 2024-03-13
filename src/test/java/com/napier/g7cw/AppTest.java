package com.napier.g7cw;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    static DB db;

    @BeforeAll
    static void init() {
        db = new DB();
        db.connect();
    }



    @Test
    void test_getCityPopulation() {
        ArrayList<String> cities = App.getCitiesByPopulation();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
    }



    @AfterAll
    static void de_init() {
        db.disconnect();
    }
}
