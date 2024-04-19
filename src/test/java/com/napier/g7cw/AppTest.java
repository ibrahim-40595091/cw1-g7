package com.napier.g7cw;

import com.napier.g7cw.obj.*;
import org.junit.jupiter.api.Test;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    /* Assert City objects get/set data correctly */
    @Test
    void testCity() {
        City c = new City(
                123,
                "London City",
                "GBR",
                "London",
                123456789
        );
        assertNotNull(c);
        assertEquals(123, c.getID());
        assertEquals("London City", c.getName());
        assertEquals("GBR", c.getCountryCode());
        assertEquals("London", c.getDistrict());
        assertEquals(123456789, c.getPopulation());

        c.setID(124);
        c.setName("Paris");
        c.setCountryCode("FRA");
        c.setDistrict("ParisDistrict");
        c.setPopulation(987654321);

        assertEquals(124, c.getID());
        assertEquals("Paris", c.getName());
        assertEquals("FRA", c.getCountryCode());
        assertEquals("ParisDistrict", c.getDistrict());
        assertEquals(987654321, c.getPopulation());
    }

    /* Assert NULL_CITY has correct values */
    @Test
    void testNullCity() {
        City nc = City.NULL_CITY;
        assertNotNull(nc);
        assertEquals(-1, nc.getID());
        assertEquals("NULL", nc.getName());
        assertEquals("NULL", nc.getCountryCode());
        assertEquals("NULL", nc.getDistrict());
        assertEquals(-1, nc.getPopulation());
    }

    /* Assert Continent objects have correct values */
    @Test
    void testContinent() {
        Continent Asia = Continent.Asia;
        assertNotNull(Asia);
        assertEquals(Asia, Continent.valueOf("Asia"));
        assertEquals("Asia", Asia.getName());
    }

    /* Assert Country objects get/set values correctly */
    @Test
    void testCountry() {
        City capital = new City(
                123,
                "London City",
                "GBR",
                "London",
                123456789
        );

        Language english = new Language(
                "GBR",
                "English",
                true,
                80.0f
        );
        Language french = new Language(
                "GBR",
                "French",
                false,
                10.0f
        );
        Language chinese = new Language(
                "GBR",
                "Chinese",
                false,
                10.0f
        );

        ArrayList<Language> officialLanguages = new ArrayList<>();
        officialLanguages.add(english);
        ArrayList<Language> otherLanguages = new ArrayList<>();
        otherLanguages.add(french);
        otherLanguages.add(chinese);

        CountryLanguages languages = new CountryLanguages("GBR", officialLanguages, otherLanguages);

        Country c = new Country(
                "GBR",
                "United Kingdom",
                Continent.Europe,
                "North Europe",
                123.123f,
                1000,
                123456789,
                70.5f,
                123456,
                123455,
                "UK",
                "Democratic",
                "Rishi Sunak",
                capital,
                "GB",
                languages
        );



        /* ***** Assertions ***** */
        assertEquals("GBR", c.getCode());
        assertEquals("United Kingdom", c.getName());
        assertEquals(Continent.Europe, c.getContinent());
        assertEquals("North Europe", c.getRegion());
        assertEquals(123.123f, c.getSurfaceArea());
        assertEquals(1000, c.getIndepYear());
        assertEquals(123456789, c.getPopulation());
        assertEquals(70.5f, c.getLifeExpectancy());
        assertEquals(123456, c.getGNP());
        assertEquals(123455, c.getGNPOld());
        assertEquals("UK", c.getLocalName());
        assertEquals("Democratic", c.getGovernmentForm());
        assertEquals("Rishi Sunak", c.getHeadOfState());
        assertEquals(capital, c.getCapital());
        assertEquals("GB", c.getCode2());
        assertEquals(languages, c.getLanguages());


        /* Test setters */
        c.setCode("UK");
        c.setName("United Kingdom 2");
        c.setContinent(Continent.Asia);
        c.setRegion("Northern Europe");
        c.setSurfaceArea(321.321f);
        c.setIndepYear(2000);
        c.setPopulation(987654321);
        c.setLifeExpectancy(80.0f);
        c.setGNP(654321);
        c.setGNPOld(654320);
        c.setLocalName("Britain");
        c.setGovernmentForm("Democracy");
        c.setHeadOfState("Boris Johnson");

        City newCapital = new City(
                123,
                "Edinburgh",
                "GBR",
                "Scotland",
                123456
        );
        Language gaelic = new Language(
                "GBR",
                "Gaelic",
                true,
                70.0f
        );
        Language scots = new Language(
                "GBR",
                "Scots",
                true,
                10.0f
        );
        Language german = new Language(
                "GBR",
                "German",
                false,
                10.0f
        );
        english.setIsOfficial(false);
        english.setPercentage(10.0f);

        ArrayList<Language> officialLanguagesNew = new ArrayList<>();
        officialLanguagesNew.add(gaelic);
        officialLanguagesNew.add(scots);
        ArrayList<Language> otherLanguagesNew = new ArrayList<>();
        otherLanguagesNew.add(german);
        otherLanguagesNew.add(english);

        CountryLanguages languagesNew = new CountryLanguages(
                "GBR",
                officialLanguagesNew,
                otherLanguagesNew
        );

        c.setCapital(newCapital);
        c.setCode2("UK");
        c.setLanguages(languagesNew);



        /* ***** Assertions ***** */
        assertEquals("UK", c.getCode());
        assertEquals("United Kingdom 2", c.getName());
        assertEquals(Continent.Asia, c.getContinent());
        assertEquals("Northern Europe", c.getRegion());
        assertEquals(321.321f, c.getSurfaceArea());
        assertEquals(2000, c.getIndepYear());
        assertEquals(987654321, c.getPopulation());
        assertEquals(80.0f, c.getLifeExpectancy());
        assertEquals(654321, c.getGNP());
        assertEquals(654320, c.getGNPOld());
        assertEquals("Britain", c.getLocalName());
        assertEquals("Democracy", c.getGovernmentForm());
        assertEquals("Boris Johnson", c.getHeadOfState());
        assertEquals(newCapital, c.getCapital());
        assertEquals("UK", c.getCode2());
        assertEquals(languagesNew, c.getLanguages());
    }


    /* Assert CountryLanguages construct correctly from an unsorted list of all languages */
    @Test
    void testCountryLanguagesConstructor() {
        Language english = new Language(
                "A",
                "English",
                true,
                50.0f
        );

        Language french = new Language(
                "A",
                "French",
                true,
                50.0f
        );

        Language german = new Language(
                "A",
                "German",
                false,
                50.0f
        );

        Language spanish = new Language(
                "A",
                "Spanish",
                false,
                50.0f
        );

        ArrayList<Language> allLanguages = new ArrayList<>();
        allLanguages.add(english);
        allLanguages.add(german);
        allLanguages.add(french);
        allLanguages.add(spanish);

        CountryLanguages cl = new CountryLanguages(
                "A",
                allLanguages
        );


        /* Assertions */
        ArrayList<Language> official = cl.getOfficial();
        ArrayList<Language> other = cl.getOther();

        assertEquals(2, official.size());
        assertEquals(2, other.size());

        assertTrue(official.contains(english));
        assertTrue(official.contains(french));
        assertTrue(other.contains(german));
        assertTrue(other.contains(spanish));
    }


    /* Test CountryLanguages objects get/set values correctly */
    @Test
    void testCountryLanguagesGetterSetters() {

        Language english = new Language(
                "A",
                "English",
                true,
                50.0f
        );

        Language french = new Language(
                "A",
                "French",
                true,
                50.0f
        );

        Language german = new Language(
                "A",
                "German",
                false,
                50.0f
        );

        Language spanish = new Language(
                "A",
                "Spanish",
                false,
                50.0f
        );


        ArrayList<Language> official = new ArrayList<>();
        ArrayList<Language> other = new ArrayList<>();

        official.add(english);
        official.add(french);
        other.add(german);
        other.add(spanish);


        CountryLanguages cl = new CountryLanguages(
                "A",
                official,
                other
        );



        /* Assertions */
        assertEquals("A", cl.getCountryCode());
        assertEquals(official, cl.getOfficial());
        assertEquals(other, cl.getOther());




        /* Set values */
        french.setIsOfficial(false);
        german.setIsOfficial(true);

        ArrayList<Language> officialNew = new ArrayList<>();
        officialNew.add(english);
        officialNew.add(german);

        ArrayList<Language> otherNew = new ArrayList<>();
        otherNew.add(french);
        otherNew.add(spanish);

        cl.setCountryCode("B");
        cl.setOfficial(officialNew);
        cl.setOther(otherNew);


        /* Assertions */
        assertEquals("B", cl.getCountryCode());
        assertEquals(officialNew, cl.getOfficial());
        assertEquals(otherNew, cl.getOther());
    }


    /* Assert Language objects get/set values correctly */
    @Test
    void testLanguage() {
        Language l = new Language(
                "GBR",
                "English",
                true,
                50.0f
        );

        assertEquals("GBR", l.getCountryCode());
        assertEquals("English", l.getName());
        assertTrue(l.getIsOfficial());
        assertEquals(50.0f, l.getPercentage());


        l.setCountryCode("FRA");
        l.setName("French");
        l.setIsOfficial(false);
        l.setPercentage(20.0f);

        assertEquals("FRA", l.getCountryCode());
        assertEquals("French", l.getName());
        assertFalse(l.getIsOfficial());
        assertEquals(20.0f, l.getPercentage());
    }

    /* Assert Language objects are built correctly when given a string as isOfficial parameter */
    @Test
    void testLanguageStringOfficialParameter() {
        Language l = new Language(
                "GBR",
                "English",
                "T",
                20.0f
        );

        assertTrue(l.getIsOfficial());


        Language l2 = new Language(
                "GBR",
                "English",
                "F",
                20.0f
        );

        assertFalse(l2.getIsOfficial());
    }
}
