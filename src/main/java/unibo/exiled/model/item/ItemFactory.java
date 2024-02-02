package unibo.exiled.model.item;

import unibo.exiled.model.character.attributes.AttributeIdentifier;

/**
 * The ItemFactory interface provides a set of methods for creating different
 * types of items
 * that the player can earn.
 */
public interface ItemFactory {

    /**
     * Creates a healing item with the specified name, description, and healing
     * value.
     *
     * @param name         The name of the healing item.
     * @param description  The description of the healing item.
     * @param healingValue The amount of healing provided by the item.
     * @return A new Item representing a healing item.
     */
    Item createHealingItem(final String name, final String description, final double healingValue);

    /**
     * Creates a power-up item with the specified name, description, power-up value,
     * and duration.
     *
     * @param name             The name of the power-up item.
     * @param description      The description of the power-up item.
     * @param powerUpValue     The value representing the power-up effect of the
     *                         item.
     * @param duration         The duration for which the power-up effect lasts.
     * @param boostedAttribute The attribute that the power up infect.
     * @return A new Item representing a power-up item.
     */
    Item createPowerUpItem(final String name, final String description, final double powerUpValue, final int duration,
            final AttributeIdentifier boostedAttribute);

    /**
     * Creates an unusable item with the specified name and description.
     *
     * @param name        The name of the unusable item.
     * @param description The description of the unusable item.
     * @return A new Item representing an unusable item.
     */
    Item createUnUsableItem(final String name, final String description);
}
