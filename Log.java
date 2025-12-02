import java.util.*;
import java.io.Serializable;

/**
 * Represents a Log of a game
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class Log implements Serializable
{
    private int seed;                           //Represents the initial seed value
    private Settings settings;                  //Represents the games settings
    private HashMap<Integer, String> choices;   //Represents the choices made in each step

    /**
     * Creates a new Log.
     * 
     * @param seed the seed value
     * @param settings the current settings of the game
     */
    public Log(int seed, Settings settings) {
        this.seed = seed;
        this.settings = settings;
        choices = new HashMap<>();
    }

    /**
     * Retrieves and returns the initial seed value
     * 
     * @return the seed value
     */
    public int getSeed() {
        return seed;
    }

    /**
     * Retrieves and returns the settings
     * 
     * @return the settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Retrieves and returns the choice made in the specific step
     * 
     * @param step the specific step we want to access
     * @return the choice made in that step or null if no choice was made
     */
    public String getChoice(int step) {
        return choices.get(step);
    }

    /**
     * Adds a choice to the choices map. If the choice was a city, we add
     * the city name. Otherwise we add null.
     * 
     * @param step the step
     * @param city the choice made for the step 
     */
    public void add(int step, City city) {
        if (city != null) {
            choices.put(step,city.getName());
        } else {
            choices.put(step, null);
        }
    }

}