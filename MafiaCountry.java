
/**
 * Represents a country that has mafia in it and gives the player a risk of being robbed.
 * 
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class MafiaCountry extends Country
{
    /**
     * Creates a new MafiaCountry
     * 
     * @param name the name of the MafiaCountry
     */
    public MafiaCountry(String name) {
        super(name);
    }
    
    /**
     * Has a given (in the settings) risk of robbing money from the player instead of 
     * giving the player a bonus. 
     * 
     * @param the value of the city within the country
     * @return either the loss from a robbery or a bonus 
     */
    @Override 
    public int bonus(int value) {
        int risk = getGame().getSettings().getRisk();
        if (getGame().getRandom().nextInt(100 + 1) <= risk) {
            System.out.println(getGame().getLoss());
            return getGame().getLoss(); 
        } else {
            return super.bonus(value);
        }
    }
    
    
}