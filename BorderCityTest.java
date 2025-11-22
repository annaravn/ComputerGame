
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class BorderCityTest.
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class BorderCityTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityG;
    private BorderCity cityC, cityD, cityE, cityF;

    @Test
    public void arriveFromOtherCountry() {
        country1.setGame(game);
        country2.setGame(game);
        //Test arrive from cityE to cityC
        for(int seed = 0; seed<1000; seed++) {          //Try different seeds
            Player player = new GUIPlayer(new Position(cityE, cityC, 0), 250);
            game.getRandom().setSeed(seed);             //Set seed
            int bonus = country1.bonus(40);             //Remember bonus
            int toll = 250/5;                           //20% toll
            int result = bonus - toll;                  //remember the player's money after bonus
            game.getRandom().setSeed(seed);             //Reset seed
            assertEquals(result, cityC.arrive(player)); //Same bonus
            assertEquals(40-result, cityC.getValue());  //Correct value after arrive
            cityC.reset();
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
        cityC = new BorderCity("City C", 40, country1);
        cityD = new BorderCity("City D", 100, country1);
        cityE = new BorderCity("City E", 50, country2);
        cityF = new BorderCity("City F", 90, country2);
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