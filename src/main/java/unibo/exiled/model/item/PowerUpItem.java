package unibo.exiled.model.item;

/**
 * This class represents a usable power up item, 
 * the use class allows the character to power up a certain statistic(for example the attack), 
 * the amount of power up is determined by the powerUpValue.
 * The power ups are not permanent, so they have a duration. 
 */
public class PowerUpItem extends ItemBase implements ItemWithDuration{

    /*private final Statistic boostedStatistic es. attacco, difesa */

    private final int duration;

    public PowerUpItem(final String name,final String description,final double powerUpValue,final int duration) {
        super(name, description);
        this.duration=duration;
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyItem'");
    }

    @Override
    public int getDuration() {
        return this.duration;
    }
    
}
