package unibo.exiled.model.item;

import unibo.exiled.model.character.attributes.AttributeType;
import unibo.exiled.model.character.player.Player;

/**
 * This class represents a usable power up item, 
 * the use class allows the character to power up a certain statistic(for example the attack), 
 * the amount of power up is determined by the powerUpValue.
 * The power ups are not permanent, so they have a duration. 
 */
public class PowerUpItem extends ItemBase implements ItemWithDuration{

    private final AttributeType boostedAttribute;
    private final int duration;
    private final double powerUpValue;

    public PowerUpItem(final String name,final String description,final double powerUpValue,final int duration,final AttributeType boostedAttribute) {
        super(name, description);
        this.duration=duration;
        this.boostedAttribute = boostedAttribute;
        this.powerUpValue=powerUpValue;
    }

    @Override
    public void use(Player player) {

    }

    @Override
    public int getDuration() {
        return this.duration;
    }
}
