import java.util.*;

/**
 * Represents a capital city.
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class CapitalCity extends BorderCity
{
    /**
     * Creates a new CaptitalCity, i.e a City that is the Capital of it's country. All Capital cities
     * are also Border cities.
     * 
     * @param name the name of the new CapitalCity
     * @param value the CapitalCity's initial value 
     * @param country the country where the CaptitalCity resides 
     */
    public CapitalCity(String name, int value, Country country) {
        super(name, value, country);
    }

    /**
     * The player does extra spending in a CaptialCity, and the spending is subtracted from the 
     * players money (after any possible toll and bonus) and added to the CapitialCity's value. 
     * 
     * @param p the Player
     * @return the final bonus that the player gets, minus toll and spending
     */
    @Override
    public int arrive(Player p) {
        //remember status on the player's money 
        int arrive = super.arrive(p); 
        //spend a random amount of the player's money after the toll and bonus
        int spending = getCountry().getGame().getRandom().nextInt(p.getMoney() + arrive + 1);
        //add the spent amount to the city
        changeValue(spending);
        return arrive - spending;
    }
}