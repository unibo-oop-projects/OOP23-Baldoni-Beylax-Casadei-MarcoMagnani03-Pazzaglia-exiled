package unibo.exiled.model.character.player;

import unibo.exiled.model.utilities.ElementalType;

/**
 * An interface that represent the elemental type of the player.
 */
public interface PlayerClass {
    /**
     * Gets the modifier associated with the player class.
     *
     * @return A double representing the modifier.
     */
    double getAttributeModifier();

    /**
     * The elemental type of the player (its class).
     *
     * @return The ElementalType of the player.
     */
    ElementalType elementalType();
}
