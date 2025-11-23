
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
/**
 * The test class MafiaCountryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MafiaCountryTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @Test
    public void bonus() {
        country2.setGame(game);

        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            Set<Integer> values = new HashSet<>();
            for (int i = 0; i < 50000; i++) {
                int bonus = country2.bonus(80);
                if (bonus < 0) { //robbery
                    robs++;
                    assertTrue(10 <= bonus && bonus <= 50);
                    loss -= bonus;
                    values.add(-bonus);
                } else { //no robbery 
                    assertTrue(0 <= bonus && bonus <= 80);
                }
            }
            int expectedRobs = robs * 20 / 100;
            int expectedLoss = 30;
            assertTrue(expectedRobs * 0.97 <= robs && expectedRobs * 1.03 >= robs);
            
            System.out.println("Loss: " + loss + "     , Expected Loss: " + expectedLoss);
            //assertTrue(expectedLoss * 0.97 <= loss && expectedLoss * 1.03 >= loss);
            assertEquals(41, values.size());
        }
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