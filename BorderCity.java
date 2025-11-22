
/**
 * Represents a border city which is a subclass of city
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class BorderCity extends City
{
    /**
     * Creates a new BorderCity, i.e a City that has Road connections to Cities in other countries. 
     * 
     * @param name the name of the new BorderCity
     * @param value the BorderCity's initial value 
     * @param country the country where the BorderCity resides 
     */
    public BorderCity(String name, int value, Country country) {
        super(name, value, country);
    }

    /**
     * The player is billed a toll (a percentage of their total money)
     * when entering a new country, and the toll is subtracted from the 
     * players money and added to the BorderCity's value. 
     * 
     * @param p the Player
     * @return the bonus that the player gets, minus toll
     */
    @Override
    public int arrive(Player p) {
        int percentage = 0;
        if (!(getCountry().equals(p.getFromCountry()))) {
            //Calculate the toll percentage
            percentage = getCountry().getGame().getSettings().getTollToBePaid();
        }
        int toll = (int)((double)p.getMoney() * (double)percentage/(double)100);
        int result = super.arrive() - toll; //subract the toll from the bonus the player recieves
        changeValue(toll);
        return result; //subract the toll from the bonus the player recieves
    }
}
