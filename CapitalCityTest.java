

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CapitalCityTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CapitalCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityF, cityG;
    private CapitalCity cityD, cityE;

    @Test
    public void arriveFromOtherCountry() {
        country1.setGame(game);
        country2.setGame(game);
        
        //Test arrive from cityE to cityD
        for(int seed = 0; seed<1000; seed++) {          //Try different seeds
            Player player = new GUIPlayer(new Position(cityE, cityD, 0), 250);
            game.getRandom().setSeed(seed);             //Set seed
            int bonus = country1.bonus(100);            //Remember bonus
            int toll = 250/5;
            int result = bonus - toll;
            int spending = game.getRandom().nextInt(player.getMoney() + 1);
            game.getRandom().setSeed(seed);
            assertEquals(result - spending, cityD.arrive(player)); //Same bonus
            assertEquals(100 - result + spending, cityD.getValue());  //Correct value after arrive
            cityD.reset();
        }
    }
    
    @Test
    public void arriveFromSameCountry() {
        country1.setGame(game);
        //Test arrive from cityD to cityC
        Player player = new GUIPlayer(new Position(cityD, cityC, 0), 250);
        game.getRandom().setSeed(0);                //Set seed
        int bonus = country1.bonus(40);
        game.getRandom().setSeed(0);                //Reset seed
        assertEquals(bonus, cityC.arrive(player));  //Same bonus
        assertEquals(40-bonus, cityC.getValue());   //Correct value after arrive
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
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);
        
        //Create capital cities
        cityD = new CapitalCity("City D", 100, country1);
        cityE = new CapitalCity("City E", 50, country2);

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