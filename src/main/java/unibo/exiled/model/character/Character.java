package unibo.exiled.model.character;

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
    void move();
}
