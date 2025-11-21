import java.util.Objects;
/**
•  Represents a city on the board.
 *
•  @author Johanne Holmstrøm Have
•  @author Anna Nygaard Ravn
•  @version 1.0
 */
public class City implements Comparable<City>
{
    private final String name;      //name of city
    private int value;              //current value of city
    private int initialValue;       //initial value of city
    private Country country;        //the country of the city

    /**
     * Creates a new city with the specified name and value. 
     * 
     * @param name the name of the new city.
     * @param value the initial value of the new city. 
     */ 
    public City(String name, int value, Country country) {
        this.name = Objects.requireNonNull(name);
        this.value = value;
        this.initialValue = value;
        this.country = country;
    }

    /**
     * Adds a given amount to the value of the city
     * 
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
     * 
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * Retrieves and returns the country where the city resides 
     * 
     * @return the country
     */
    public Country getCountry(){
        return country;
    }

    /**
     * Retrieves and returns the value of the city
     * 
     * @return the value
     */
    public int getValue(){
        return value;
    }

    /**
     * Retrieves and returns the initial value of the city
     * 
     * @return the initial value
     */
    public int getInitialValue(){
        return initialValue;
    }

    /**
     * Retrives a random bonus depending on which country the city resides in. Subtracts 
     * the bonus from the cities current value.
     * 
     * @return the random bonus
     */
    public int arrive() {
        int bonus = country.bonus(value);
        value -= bonus;
        System.out.println("Bonus: " + bonus);
        return bonus;
    }
    
    public int arrive (Player p) 
    { 
        return arrive(); 
    }

    /**
     * Returns the City object in string form. The string contains the city's name 
     * and value
     * 
     * @return A string containing the city's name and value
     */
    @Override
    public String toString() {
        return name + " (" + value + ")";  
    }

    /**
     * Compares one City object to another. Is used to sort city Collections. Cities
     * are sorted in the alphabetical order of their names.
     * 
     * @param other another City object
     * @return An Integer for the comparator. 
     */
    @Override
    public int compareTo(City other) {
        return name.compareTo(other.name);
    }

    /**
     * Returns a boolean depending on whether the input is equal to this object or not.
     * A City object is equal to another country object if and only if
     * they have the same name and they are in the same country
     * 
     * @param o some other object we want to compare to this object
     * @return true if the param object is equal to this object, false if not
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
        City other = (City) o;
        return name.equals(other.name) && country.equals(other.country);
    }

    /**
     * Takes the hashCode for the field variables used in the equals method of the 
     * City object and creates a new hashCode.
     * 
     * @return hashCode for the City object
     */
    @Override
    public int hashCode() {
        return 11 * name.hashCode() + 13 * country.hashCode();
    }

}