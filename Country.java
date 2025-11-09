import java.util.Objects;
import java.util.*;

/**
 * Represents a country with a network of roads and cities.
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class Country
{
    private String name;
    private Map<City, Set<Road>> network;
    
    /**
     * Creates a new country
     * @param name the name of the country
     */
    public Country(String name) {
        this.name = name;
        network = new TreeMap();
    }
    
    /**
     * Retrieves and returns all the cities in the network
     * @return all the cities in the network
     */
    public Set<City> getCities() {
        return network.keySet();    
    }
}