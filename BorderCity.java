
/**
 * Write a description of class BorderCity here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
        int toll = p.getMoney() * percentage/100;
        changeValue(toll);
        return arrive() - toll; 
        }
        
    }
