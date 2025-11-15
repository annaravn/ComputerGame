
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
        //Create game
        game = new Game();

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
    public void reset() {
        country1.setGame(game);
        country2.setGame(game);

        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityE.arrive(); cityE.arrive(); cityE.arrive();
        int valueE = cityE.getValue();                  //Remember value of cityE
        country1.reset();
        assertEquals(80, cityA.getValue());             //cytyA is reset
        assertEquals(valueE, cityE.getValue());         //cityE is unchanged

    }

    @Test
    public void bonus() {
        country1.setGame(game);
        
        //Test with value 80
        for(int seed=0; seed<100; seed++) {         //Try 100 different seeds
            game.getRandom().setSeed(seed);
            Set<Integer> values = new HashSet();
            int sum = 0;
            for(int i=0; i<100000; i++) {           //Call method 100.000 times
                int bonus = country1.bonus(80);
                assertTrue(0 <= bonus && bonus <= 80);
                values.add(bonus);
                sum += bonus;
            }
            int expectedSum = 100000 * 80/2;
            assertTrue(expectedSum *0.99 <= sum && sum <= expectedSum * 1.01);
            assertEquals((80+1), values.size());
        }
        
        //Test with value 1
        for(int seed=0; seed<100; seed++) {         //Try 100 different seeds
            game.getRandom().setSeed(seed);
            Set<Integer> values = new HashSet();
            int sum = 0;
            for(int i=0; i<100000; i++) {           //Call method 100.000 times
                int bonus = country1.bonus(1);
                assertTrue(0 <= bonus && bonus <= 1);
                values.add(bonus);
                sum += bonus;
            }
            int expectedSum = 100000 * 1/2;
            assertTrue(expectedSum *0.99 <= sum && sum <= expectedSum * 1.01);
            assertEquals((1+1), values.size());
        }
        
        //Test with value 0
        assertEquals(0, country1.bonus(0));
        //Since we never pick any random values, when bonus is called with 0,
        //it is sufficient to test this once.
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
        country1.addRoads(city1, cityA, 0);
        country1.addRoads(cityF, city2, 3);

        country2.addRoads(city2, cityE, 5);
        country2.addRoads(city2, city1, 6);
        country2.addRoads(city2, city2, 8);
        country2.addRoads(city2, cityE, 0);
        country2.addRoads(cityA, city1, 3);

        assertEquals(2, country2.getRoads(city2).size());
        assertEquals(4, country1.getRoads(cityA).size());
        assertEquals(4, country2.getRoads(cityE).size());

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