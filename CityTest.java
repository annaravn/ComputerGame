
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;

/**
 * The test class CityTest.
 *
 * @author  Johanne Holmstrøm Have 
 * @author Anna Nygaard Ravn
 * @version 1.0 (15/11-25)
 */
public class CityTest
{
    private Game game;
    private Country country1;
    private MafiaCountry country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @Test
    public void constructor() {
        //Test of cityA
        assertEquals("City A", cityA.getName());
        assertEquals(80, cityA.getValue());
        assertEquals(80, cityA.getInitialValue());
        assertEquals(country1, cityA.getCountry());

        //Test of cityE
        assertEquals("City E", cityE.getName());
        assertEquals(50, cityE.getValue());
        assertEquals(50, cityE.getInitialValue());
        assertEquals(country2, cityE.getCountry());

    }

    @Test
    public void arrive() {
        country1.setGame(game);
        country2.setGame(game);
        
        int sum1 = 0;
        int sum2 = 0;
        //Test arrive on cityA (normal country) & cityE (mafia country)
        for(int seed = 0; seed<1000; seed++) {          //Try different seeds
            game.getRandom().setSeed(seed);             //Set seed
            int bonus1 = country1.bonus(80);            //Remember bonus
            int bonus2 = country2.bonus(50);
            
            game.getRandom().setSeed(seed);             //Reset seed
            assertEquals(bonus1, cityA.arrive());       //same bonus
            assertEquals(80-bonus1, cityA.getValue());  //Correct value after arrive
            sum1 += bonus1;
            
            assertEquals(bonus2, cityE.arrive());
            if (bonus2 > 0) {
                assertEquals(50 - bonus2, cityE.getValue());
                sum2 += bonus2;
            } else {
                //checking that the city's values isn't changed when the player is robbed
                assertEquals(50, cityE.getValue());
            }
            cityA.reset();
            cityE.reset();
        }
        int expectedSum1 = 1000 * 80/2; // Expected sum of all bonuses.
        int expectedSum2 = 800 * 50/2; //Expected sum of 80% of bonuses, 20% are robbed
        
        // Testing if sum and expected sum are equal (with a 3% margin)
        assertTrue(expectedSum1 * 0.97 <= sum1 && sum1 <= expectedSum1 * 1.03);
        // Testing the same for the MafiaCountry (with a 5% margin)
        assertTrue(expectedSum2 * 0.95 <= sum2 && sum2 <= expectedSum2 * 1.05);
    }

    @Test
    public void changeValue() {
        //Test changeValue when adding
        for (int i = 0; i<100; i++) {
            int value = cityA.getValue();
            cityA.changeValue(i);
            assertEquals(value + i, cityA.getValue());
        }
        //Test changeValue when subtracting
        for (int i = 0; i>-100; i--) {
            int value = cityA.getValue();
            cityA.changeValue(i);
            assertEquals(value + i, cityA.getValue());
        }
        assertEquals(80, cityA.getValue());
    }

    @Test
    public void reset() {
        for (int i = 0; i<100; i++) {
            cityA.changeValue(i);
            cityA.reset();
            assertEquals(80, cityA.getValue());
        }
    }

    @Test
    public void testToString() {
        assertEquals("City A (80)", cityA.toString());

        cityA.changeValue(-80);
        assertEquals("City A (0)", cityA.toString());

        cityA.changeValue(20);
        assertEquals("City A (20)", cityA.toString());

        cityA.reset();
        assertEquals("City A (80)", cityA.toString());
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
        country2 = new MafiaCountry("Country 2");

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