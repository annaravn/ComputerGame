import java.util.Objects;
/**
 * Represents a player's position on a road between two cities on the board.
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class Position
{
   private City from;
   private City to;
   private int distance;
   private int total;
/**
     * Creates a new position on a road between two cities. 
     * @param from the origin city.
     * @param to the destination city. 
     * @param distance the length between the position and the total length of the road
     */ 
    public Position(City from, City to, int distance) {
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        this.distance = distance;
        total = distance;
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
     * Retrieves and returns the distance between the postion and the destination city
     * @return the distance
     */
    public int distance() {
        return distance;
    }
    
       /**
     * Retrieves and returns the total distance between the origin city and the destination city
     * @return the total distance
     */
    public int totalDistance() {
        return total;
    }
    
    /**
     * Moves the position to be at a closer distance from the destination city. 
     * @return true if the position was moved and false if it was not.
     */
    public boolean move() {
        if (distance > 0) {
            distance--;
            return true;
        }
        return false;
    }
    
    /**
     * Turns the direction around so that the origin city becomes the destination city and vice versa.
     * Also updates the remaining distance between the position and the new destination city. 
     */
    public void turnAround() {
        City tmp = from;
        from = to;
        to = tmp;
        distance = total - distance;
    }
    
    /**
     * Returns a boolean value that depends on whether or not the position is at the destination city
     * @return true if the position is at the destination city and false if it is not
     */
    public boolean hasArrived() {
        return distance == 0;
    }
    
    @Override 
    public String toString() {
        return from + " -> " + to + " : " + distance + "/" + total;
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
       Position other = (Position) o;
       return from.equals(other.from) && to.equals(other.to) && distance == other.distance && total == other.total;
    }
    
    @Override
    public int hashCode() {
         return Objects.hash(to, from, distance, total);
         // her har jeg gjort det lidt "smartere" :)
    }
}