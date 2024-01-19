package unibo.exiled.model.character;

import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.move.MoveSet;

/**
 * A character that can move.
 */
public interface Character {
    /**
     * Gets the MoveSet of the character to be used in a battle.
     * @return The MoveSet of the current character.
     */
    MoveSet getMoveSet();

    /**
     * Moves the character by the selected direction.
     * @param direction
     */
    void move(final Direction direction);
}
