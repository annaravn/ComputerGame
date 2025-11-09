import java.util.Objects;
/**
 * Represents a road bewteen two cities on the board.
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
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
    public City from() {
        return from;
    }
    
     /**
     * Retrieves and returns road's destination city
     * @return the destination city
     */
    public City to() {
        return to;
    }
    
      /**
     * Retrieves and returns road's length
     * @return the length
     */
    public int length() {
        return length;
    }
    
    @Override
    public String toString() {
        return from + " -> " + to + " : " + length;
    }
    
    @Override
    public int compareTo(Road other) {
        if(from.name().equals(other.from.name())){
            if (to.name().equals(other.to.name())) {
                return length - other.length;
            }
            return to.name().compareTo(other.to.name());
        }
        return from.name().compareTo(other.from.name());
    }
    
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
    
    @Override 
    public int hashCode() {
        return 11 * to.hashCode() + 13 * from.hashCode() + 17 * Integer.hashCode(length); 
        //her har jeg gjort det som Kurt har vist det 
    }

    
}