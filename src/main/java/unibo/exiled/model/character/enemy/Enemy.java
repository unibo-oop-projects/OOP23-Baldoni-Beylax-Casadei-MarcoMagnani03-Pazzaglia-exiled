package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.item.Item;
import unibo.exiled.utilities.ElementalType;

import java.util.Optional;

/**
 * Interface representing an enemy character in the game.
 */
public interface Enemy extends GameCharacter {

    /**
     * Gets the amount of experience points dropped by the enemy when defeated.
     *
     * @return The amount of experience points.
     */
    int getDroppedExperience();

    /**
     * Gets the Elemental Type of the enemy.
     *
     * @return The Elemental Type of the enemy.
     */
    ElementalType getType();

    /**
     * The held item by the enemy.
     *
     * @return An optional containing the held item of the enemy.
     */
    Optional<Item> getHeldItem();
}
