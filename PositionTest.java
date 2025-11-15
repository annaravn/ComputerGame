

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PositionTest.
 *
 * @author  Johanne Holmstrøm Have 
 * @author Anna Nygaard Ravn
 * @version 1.0 (15/11-25)
 */
public class PositionTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
    private Position pos1, pos2;

    /**
     * Default constructor for test class PositionTest
     */
    public PositionTest()
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
        
        //create positions
        pos1 = new Position(cityA, cityB, 4);
        pos2 = new Position(cityC, cityD, 2);
    }
    
    @Test 
    public void constructor() {
        //test constructor for pos1
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(4, pos1.getDistance());
        assertEquals(4, pos1.getTotal());
        
        //test constructor for pos2
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(2, pos2.getDistance());
        assertEquals(2, pos2.getTotal());

    }
    
    @Test 
    public void move() {
        //move 1 from cityC towards cityD. The total distance is 2 so 
        //now the distance should be 1
        assertEquals(true, pos2.move());
        assertEquals(1, pos2.getDistance());
        
        //move 1 from cityC towards cityD. The remaining distance is 1 so 
        //now the distance should be 0
        assertEquals(true, pos2.move());
        assertEquals(0, pos2.getDistance());
        
        //move 1 from cityC towards cityD. The remaining distance is 0 so should return
        //false and the distance should remain 0. 
        assertEquals(false, pos2.move());
        assertEquals(0, pos2.getDistance());
    }
    
    @Test
    public void turnAround() {
        //move 1 from cityA towards cityB and turn around
        pos1.move();
        pos1.turnAround();
        
        //now the to and from city should be flipped. The same goes for 
        // the distance
        assertEquals(cityB, pos1.getFrom());
        assertEquals(cityA, pos1.getTo());
        assertEquals(1, pos1.getDistance());
        
        pos1.turnAround();
        
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(3, pos1.getDistance());
        
        pos2.turnAround();
        
        assertEquals(cityD, pos2.getFrom());
        assertEquals(cityC, pos2.getTo());
        assertEquals(0, pos2.getDistance());
        
        pos2.turnAround();
        
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(2, pos2.getDistance());
    }
    
    @Test
    public void hasArrived() {
        //the distance between cityC and cityD is 2 and since we are standing in the
        //from city we have not arrived yet
        assertEquals(false, pos2.hasArrived());
        pos2.move();
        
        //after having moved we are standing between the cities and 
        //and since there is still a distance of 1 remaining before we reach cityD
        //we have not arrived yet
        assertEquals(false, pos2.hasArrived());
        pos2.move();
        
        //we have now arrived
        assertEquals(true, pos2.hasArrived());
        pos2.move();
        
        //we try to move but cannot, since we have already arrived.
        assertEquals(true, pos2.hasArrived());
    }
    
    @Test
    public void testToString() {
        //we check that our position's toString method updates what is returned 
        //according to the relevant field variables
        assertEquals("City A (80) -> City B (60) : 4/4", pos1.toString());
        assertEquals("City C (40) -> City D (100) : 2/2", pos2.toString());
        
        pos2.move();
        assertEquals("City C (40) -> City D (100) : 1/2", pos2.toString());
        
        pos2.move();
        assertEquals("City C (40) -> City D (100) : 0/2", pos2.toString());
        
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