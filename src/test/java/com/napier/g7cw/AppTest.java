package com.napier.g7cw;

import com.napier.g7cw.db.CityDBA;
import com.napier.g7cw.db.DB;
import com.napier.g7cw.obj.City;
import com.napier.g7cw.obj.Report;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest {



    // Keeping this code in case we need it in future
    // Intention is to test whether the DB is connecting/returning data/disconnecting correctly
    // Currently only testing whether the functions can process the raw DB data correctly
    static DB db;
/*
    @BeforeEach
    void init() {
        db = new DB();
        db.connect("localhost:33060", 10000);
        App.db = db;
    }
*/


    @Test
    void testGetCity() {
        return;
    }

    /*
    @AfterAll
    static void de_init() {
        db.disconnect();
    }
*/

}
