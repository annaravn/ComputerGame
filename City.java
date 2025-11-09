import java.util.Objects;
/**
 * Represents a city on the board.
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class City implements Comparable<City>
{
    private final String name;
    private int value;
    private int initialValue;
    
    /**
     * Creates a new city with the specified name and value. 
     * @param name the name of the new city.
     * @param value the initial value of the new city. 
     */ 
    public City(String name, int value) {
        this.name = Objects.requireNonNull(name);
        this.value = value;
        this.initialValue = value;
    }
    
    /**
     * Adds a given amount to the value of the city
     * @param amount the number of points we want to add to the city's value
     */
    public void changeValue(int amount) {
        value += amount; 
    }
    
    /**
     * Resets the value of the city to it's inital value.
     */
    public void reset() {
        value = initialValue;
    }
    
    /**
     * Retrieves and returns the name of the city
     * @return the name
     */
    public String name(){
        return name;
    }
    
    /**
     * Retrieves and returns the value of the city
     * @return the value
     */
    public int Value(){
        return value;
    }
    
     /**
     * Retrieves and returns the initial value of the city
     * @return the initial value
     */
    public int initialValue(){
        return initialValue;
    }
    
    @Override
    public String toString() {
        return name + " (" + value + ")";  
    }
    
    @Override
    public int compareTo(City other) {
        return name.compareTo(other.name);
    }
    
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
       City other = (City) o;
       return name.equals(other.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
}