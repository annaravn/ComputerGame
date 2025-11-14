

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class RoadTest.
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version (1.0, 14.11.2025)
 */
public class RoadTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
    private Road road1, road2;

    
    @Test
    public void constructor() {
        //Test road 1 - cityA -> cityB, 4
        assertEquals(cityA, road1.getFrom());
        assertEquals(cityB, road1.getTo());
        assertEquals(4, road1.getLength());
        
        //Test road 2 - cityC -> cityD, 2
        assertEquals(cityC, road2.getFrom());
        assertEquals(cityD, road2.getTo());
        assertEquals(2, road2.getLength());
        
    }
    
    @Test
    public void testToString() {
        //Test road 1
        assertEquals("City A (80) -> City B (60) : 4", road1.toString());
        
        //Test road 2
        assertEquals("City C (40) -> City D (100) : 2", road2.toString());
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
        
        road1 = new Road(cityA, cityB, 4);
        road2 = new Road(cityC, cityD, 2);
        
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