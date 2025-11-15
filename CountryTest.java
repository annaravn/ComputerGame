
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.HashSet;

/**
 * The test class CountryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CountryTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    /**
     * Default constructor for test class CountryTest
     */
    public CountryTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);
        country1.addCity(cityD);

        country2.addCity(cityE);
        country2.addCity(cityF);
        country2.addCity(cityG);

        // Create roads
        country1.addRoads(cityA, cityB, 4);
        country1.addRoads(cityA, cityC, 3);
        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        country2.addRoads(cityF, cityD, 3);
        country2.addRoads(cityF, cityG, 6);
    }

    @Test
    public void constructor() {
        //test constructor for country1
        assertEquals("Country 1", country1.getName());

        //test constructor for country2
        assertEquals("Country 2", country2.getName());
    }

    @Test
    public void getCities() {
        //just testing that a set containing the correct amount of cities 
        //is returned from getCities
        assertEquals(4, country1.getCities().size());
        assertEquals(3, country2.getCities().size());
    }

    @Test 
    public void getCity() {
        //testing if we can find a city we know is in a country, and also
        //the case where the city is not in the country
        assertEquals(cityA, country1.getCity("City A"));
        assertEquals(null, country1.getCity("City F"));

        assertEquals(cityF, country2.getCity("City F"));
        assertEquals(null, country2.getCity("City A"));
    }

    @Test
    public void getRoads() {
        //making a test set for each country
        Set<Road> roads = new TreeSet<>();
        
        //testing that an empty set is returned when calling getRoads
        //with a city that doesn't have any roads in the country
        assertEquals(roads, country1.getRoads(cityG));
        
        //adding roads to the test set
        roads.add(new Road(cityA, cityB, 4));
        roads.add(new Road(cityA, cityC, 3));
        roads.add(new Road(cityA, cityD, 5));
        
        //testing for a city that has roads in the country
        assertEquals(roads, country1.getRoads(cityA));

        //repeating the same for country2
        Set<Road> roads2 = new TreeSet<>();
        
        assertEquals(roads2, country2.getRoads(cityA));
        
        roads2.add(new Road(cityE, cityC, 4));
        roads2.add(new Road(cityE, cityF, 2));
        roads2.add(new Road(cityE, cityG, 5));
        
        assertEquals(roads2, country2.getRoads(cityE));
    }
    
    @Test
    public void addRoads() {
        
        //creating new cities to be able to add more roads
        City city1 = new City("City 1", 70, country1);
        City city2 = new City("City 2", 60, country2);
        
        //adding those cities to their respective countries
        country1.addCity(city1);
        country2.addCity(city2);
        
        //adding a road between two cities in the same country 
        country1.addRoads(city1, cityA, 4);
        
        //testing that we have now added a road both to city1 and cityA
        assertEquals(1, country1.getRoads(city1).size());
        assertEquals(4, country1.getRoads(cityA).size());
        
        //adding a road between a city that is in the country and another city 
        //that is not in the country
        country1.addRoads(city1, city2, 6);
        
        //now we should have 2 roads for city1
        assertEquals(2, country1.getRoads(city1).size());
        
        //we try adding a road between the same city
        country1.addRoads(city1, city1, 8);
        
        //the amount of roads should be unchanged
        assertEquals(2, country1.getRoads(city1).size());
        
        //we try adding a road of length zero
        country1.addRoads(city1, cityA, 0);
        
        //again the amount should be unchanged
        assertEquals(2, country1.getRoads(city1).size());
        
        //we try inserting a road between two cities not in the country        
        country1.addRoads(cityF, city2, 3);
        
        //we expect that no roads will be added
        assertEquals(0, country1.getRoads(cityF).size());
    }
    
    @Test
    public void position() {
        //create a test position
        Position p1 = new Position(cityA, cityA, 0);
        assertEquals(p1, country1.position(cityA));
        
        //create another test position
        Position p2 = new Position(cityE, cityE, 0);
        assertEquals(p2, country2.position(cityE));
    }
    
    @Test 
    public void readyToTravel() {
        //test for two different cities within the same country
        assertEquals(new Position(cityA, cityB, 4), country1.readyToTravel(cityA, cityB));
        
        //test for two different cities one in the country, another in a 
        //different country
        assertEquals(new Position(cityC, cityE, 4), country1.readyToTravel(cityC, cityE));
        
        //test for the same two cities
        assertEquals(new Position(cityA, cityA, 0), country1.readyToTravel(cityA, cityA));
        
        //test the case where the from city is not in the country
        assertEquals(new Position(cityF, cityF, 0), country1.readyToTravel(cityF, cityA));
        
        //test the case where there are no roads from the given city
        City cityT = new City("City T", 80, country1);
        country1.addCity(cityT);
        assertEquals(new Position(cityT, cityT, 0), country1.readyToTravel(cityT, cityA));
        
        //test the case where there the destination city from the origin city doesn't exist
        assertEquals(new Position(cityA, cityA, 0), country1.readyToTravel(cityA, cityT));
    }
    
    @Test
    public void testToString() {
        assertEquals("Country 1", country1.toString());
        assertEquals("Country 2", country2.toString());
    }


    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}