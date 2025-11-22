import java.util.Objects;
import java.util.*;
import java.util.Random;

/**
 * Represents a country with a network of roads and cities.
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class Country
{
    private String name;                    //name of country
    private Map<City, Set<Road>> network;   //cities in the country and their roads
    private Game game;                      //the countrys game object

    /**
     * Creates a new country
     * 
     * @param name the name of the country
     */
    public Country(String name) {
        this.name = name;
        network = new TreeMap();
    }

    /**
     * Retrieves and returns the name of the country
     * 
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves and returns all the cities in the network
     * 
     * @return all the cities in the network
     */
    public Set<City> getCities() {
        return network.keySet();    
    }

    /**
     * Retrieves and returns the city with the given name
     * 
     * @param the name of the city we want to find
     * @return the city or null if no city with the given name exists
     */
    public City getCity(String name) {
        for (City c : network.keySet()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null; // If no city with that name exists
    }

    /**
     * Retrieves and returns all roads starting in the given city
     * 
     * @param the city from which the roads we want to find starts
     * @return the roads starting in the given city
     */
    public Set<Road> getRoads(City c) {
        Set<Road> roads = network.get(c);
        if (roads == null) {
            return new TreeSet<>();
        }
        return roads;
    }

    /**
     * Retrieves and returns the countrys game object
     * 
     * @return the countrys game object
     */
    public Game getGame() {
        return game;
    }
    
    /**
     * Resets the value of all cities in the country to their inital value.
     */
    public void reset() {
        for (City c : network.keySet()) {
            c.reset();
        }
    }

    /**
     * Calculates the size of the bonus the player recieves
     * 
     * @param value of the specific city within the country
     * @return the bonus given to the player
     */
    public int bonus(int value) {
        Random random = game.getRandom();
        int bonus = 0;
        if (value > 0) {
            bonus = random.nextInt(value+1);
        }
        return bonus;
    }

    /**
     * Adds the given city to the network
     * 
     * @param the city we wish to add to the network
     */
    public void addCity(City c) {
        network.put(c, new TreeSet<>());
    }

    /**
     * Adds roads between a and b
     * 
     * @param a the first city
     * @param b the second city
     * @param length the length between a and b
     */
    public void addRoads(City a, City b, int length) {
        if (!(length>0)) {
            return;
        }

        if (! a.equals(b)) {
            if (a.getCountry().getName() == name) {
                //creates road from a to b
                network.get(a).add(new Road(a, b, length));
            }
            if (b.getCountry().getName() == name) {
                //creates road from b to a
                network.get(b).add(new Road(b, a, length));
            }
        }
    }

    /**
     * Retrieves and returns the given city's position within the country
     * 
     * @param the city that we want the position of
     * @return the city's position
     */
    public Position position(City city) {
        Position position = new Position(city, city, 0);
        return position;
    }

    /**
     * Changes the position so that the player is heading towards the destination city, if there
     * is a direct road.
     * 
     * @param from the origin city
     * @param to the destination city
     * @return a position in the origin city heading towards the destination city. If the destination
     * city is the same as the origin city or if there is not a direct road from the origin city to 
     * the destination city, the origin city's position is returned.
     */
    public Position readyToTravel(City from, City to) {
        if (from.equals(to)) {
            return position(from);
        }
        if (!from.getCountry().equals(this)) {
            return position(from);
        }
        Set<Road> roadsFrom = network.get(from);
        if (roadsFrom == null) {
            return position(from);
        }
        for (Road r : roadsFrom) {
            if (r.getTo().equals(to)) {
                return new Position(from, to, r.getLength()); 
            }
        }
        return position(from);
    }

    /**
     * Sets game to the given game
     * 
     * @param the game you wish to change to
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    /**
     * Returns the country object in string form
     * 
     * @return the countrys name
     */
    @Override
    public String toString() {
        return name;  
    }

    /**
     * Returns a boolean
     * A country object is equal to another country object if and only if
     * they have the same name

     * @param the object we want to compare with
     * 
     * @return true if the two countries are equal and false if they are not
     */
    @Override 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Country other = (Country) o;
        return name.equals(other.name);
    }

    /**
     * Returns hashcode of name
     * 
     * @return hashcode of name
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}