import java.util.Objects;
/**
•  Represents a road bewteen two cities on the board.
 *
•  @author Johanne Holmstrøm Have
•  @author Anna Nygaard Ravn
•  @version 1.0
 */
public class Road implements Comparable<Road>
{
    private final City from;
    private final City to;
    private final int length;
    
    /**
     * Creates a new road between two cities. 
     * @param from the origin city.
     * @param to the destination city. 
     */ 
    public Road(City from, City to, int length) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        this.length = length;
    }
    
    /**
     * Retrieves and returns road's origin city
     * @return the origin city
     */
    public City getFrom() {
        return from;
    }
    
    /** 
      * Retrieves and returns road's destination city
      * @return the destination city
     */
    public City getTo() {
        return to;
    }
    
    /**
     * Retrieves and returns road's length
     * @return the length
     */
    public int getLength() {
        return length;
    }
    
    /** 
     * Returns the Road object in string form 
     * @return the departure and destination city and the length between these cities
     */
    @Override
    public String toString() {
        return from + " -> " + to + " : " + length;
    }
    
    /**
     * Compares one Road object to another. Is used to sort Collections.
     * Roads are first sorted in the alphabetical order of the cities they start in.
     * Secundary they are sorted after the city they end in.
     * At last they are sorted after length (short to long). 
     * @param other Road we want to compare to 
     * @return an Integer for the comperator.
     */
    @Override
    public int compareTo(Road other) {
        if(from.getName().equals(other.from.getName())){
            if (to.getName().equals(other.to.getName())) {
                return length - other.length;
            }
            return to.getName().compareTo(other.to.getName());
        }
        return from.getName().compareTo(other.from.getName());
    }
    
    /**
     * Returns a boolean true or false depending on whether two objects are equal.
     * A Road object is equal to another Road object if and only if
     * they have the same departure and destination city (from and to)
     * and the same length
     * 
     * @param the object we want to compare with
     * @return true if the two roads are equal and false if they are not
     */
    @Override
    public boolean equals(Object o){
        if (this == o) {
           return true;
       }
       if (o == null) {
           return false;
       }
       if (getClass() != o.getClass()) {
           return false;
       }
       Road other = (Road) o;
       return from.equals(other.from) && to.equals(other.to) && length == other.length;
    }
    
    /**
     * Returns hashcode of to, from and length
     * @return hashcode of to, from and length
     */
    @Override 
    public int hashCode() {
        return 11 * to.hashCode() + 13 * from.hashCode() + 17 * Integer.hashCode(length); 
    }

    
}