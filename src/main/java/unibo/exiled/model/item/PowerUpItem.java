package unibo.exiled.model.item;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.player.Player;

/**
 * This class represents a usable power-up item.
 * The use class allows the character to power up a certain statistic (for
 * example, attack).
 * The amount of power-up is determined by the powerUpValue.
 * Power-ups are not permanent, so they have a duration.
 */
public final class PowerUpItem extends ItemBase implements ItemWithDuration {

    private final AttributeIdentifier boostedAttribute;
    private final int duration;
    private final double powerUpValue;

    /**
     * Constructs a PowerUpItem with the specified attributes.
     *
     * @param name             The name of the power-up item.
     * @param description      The description of the power-up item.
     * @param powerUpValue     The value by which the attribute is powered up.
     * @param duration         The duration of the power-up effect.
     * @param boostedAttribute The attribute that is boosted by the power-up.
     */
    public PowerUpItem(final String name, final String description, final double powerUpValue, final int duration,
                       final AttributeIdentifier boostedAttribute) {
        super(name, description, ItemType.POWERUP);
        this.duration = duration;
        this.boostedAttribute = boostedAttribute;
        this.powerUpValue = powerUpValue;
    }

    @Override
    public void use(final Player player) {
        player.increaseAttributeModifier(boostedAttribute, powerUpValue);
    }

    /**
     * Gets the duration for which the item's effect lasts.
     *
     * @return The duration of the item's effect.
     */
    @Override
    public int getDuration() {
        return this.duration;
    }

    /**
     * Get the amount or effectiveness of the item.
     *
     * @return Depends on the specific type of item. For example, for a healing
     * item, it represents the amount of health restored.
     */
    @Override
    public double getAmount() {
        return this.powerUpValue;
    }

    /**
     * Gets the attribute that is boosted by the power-up.
     *
     * @return The boosted attribute.
     */
    public AttributeIdentifier getBoostedAttribute() {
        return this.boostedAttribute;
    }
}
