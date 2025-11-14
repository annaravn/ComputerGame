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
    private City from;          //city we travel from
    private City to;            //city we travel to
    private int distance;       //distance we have travelled
    private int total;          //total distance between the two cities
    /**
     * Creates a new position on a road between two cities. 
     * 
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
     * 
     * @return the origin city
     */
    public City getFrom() {
        return from;
    }

    /**
     * Retrieves and returns road's destination city
     * 
     * @return the destination city
     */
    public City getTo() {
        return to;
    }

    /**
     * Retrieves and returns the distance between the postion and the destination city
     * 
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Retrieves and returns the total distance between the origin city and the destination city
     * 
     * @return the total distance
     */
    public int getTotal() {
        return total;
    }

    /**
     * Moves the position to be at a closer distance from the destination city.
     * 
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
     * 
     * @return true if the position is at the destination city and false if it is not
     */
    public boolean hasArrived() {
        return distance == 0;
    }

    /**
     * Returns the Position object in string form
     * 
     * @return departure and destination city (from and to) and the distance travelled of the total distance
     */
    @Override 
    public String toString() {
        return from + " -> " + to + " : " + distance + "/" + total;
    }

    /**
     * Returns a boolean true or false depending on whether two objects are equal.
     * A Position object is equal to another Position object if and only if
     * they have the same departure and destination city (from and to)
     * and they have the same distance and total distance
     * 
     * @param the object we want to compare with
     * @return true if the two positions are equal and false if they are not
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
        Position other = (Position) o;
        return from.equals(other.from) && to.equals(other.to) && distance == other.distance && total == other.total;
    }

    /**
     * Returns hashcode of to, from, distance and total
     * 
     * @return hashcode of to, from, distance and total
     */
    @Override
    public int hashCode() {
        return 11 * to.hashCode() + 13 * from.hashCode() + 17 * Integer.hashCode(distance) + 19 * Integer.hashCode(total);
    }
}