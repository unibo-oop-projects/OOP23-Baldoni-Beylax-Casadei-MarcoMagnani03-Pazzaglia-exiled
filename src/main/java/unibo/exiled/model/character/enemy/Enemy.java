package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.GameCharacter;

/**
 * Interface representing an enemy character in the game.
 */
public interface Enemy extends GameCharacter {

    /**
     * Gets the amount of experience points dropped by the enemy when defeated.
     *
     * @return The amount of experience points.
     */
    double getDroppedExperience();
}
