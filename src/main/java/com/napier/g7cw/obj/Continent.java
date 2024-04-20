package com.napier.g7cw.obj;

/**
 * Represents a continent as fetched from the MySQL database<br>
 *      Enumerable field - cannot create "custom" continents<br>
 *      They must be selected from the set static fields within the class<br>
 */
public class Continent {
    public static Continent Asia = new Continent("Asia");
    public static Continent Europe = new Continent("Europe");
    public static Continent NorthAmerica = new Continent("North America");
    public static Continent Africa = new Continent("Africa");
    public static Continent Oceania = new Continent("Oceania");
    public static Continent Antarctica = new Continent("Antarctica");
    public static Continent SouthAmerica = new Continent("South America");


    private String Name;
    private Continent(String name) {
        Name = name;
    }

    public String getName() { return Name; }

    /**
     * Returns the Continent object that corresponds to the given name
     * @param name
     * Name of the Continent object to return
     * @return
     * Continent object that corresponds to the given name
     */
    public static Continent valueOf(String name) {
        switch (name) {
            case "Asia":
                return Asia;
            case "Europe":
                return Europe;
            case "North America":
                return NorthAmerica;
            case "Africa":
                return Africa;
            case "Oceania":
                return Oceania;
            case "Antarctica":
                return Antarctica;
            case "South America":
                return SouthAmerica;
            default:
                return null;
        }
    }
}
