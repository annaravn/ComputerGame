import java.util.*;

/**
 * Represents a capital city.
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class CapitalCity extends BorderCity
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class CapitalCity
     */
    public CapitalCity(String name, int value, Country country) {
        super(name, value, country);
    }

    @Override
    public int arrive(Player p) {
        int arrive = super.arrive(p);
        int spending = getCountry().getGame().getRandom().nextInt(p.getMoney() + 1);
        changeValue(spending);
        return (arrive - spending);
    }
}