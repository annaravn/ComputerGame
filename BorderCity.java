
/**
 * Represents a border city which is a subclass of city
 *
 * @author Johanne Holmstrøm Have
 * @author Anna Nygaard Ravn
 * @version 1.0
 */
public class BorderCity extends City
{
    public BorderCity(String name, int value, Country country) {
        super(name, value, country);
    }
    
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
