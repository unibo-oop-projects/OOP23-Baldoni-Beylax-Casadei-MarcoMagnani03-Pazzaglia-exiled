package unibo.exiled.model.character;

import unibo.exiled.model.character.attributes.Attributes;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.Position;

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

    /**
     * Returns the position of the current character.
     * @return The position of the current character.
     */
    Position getPosition();

    /**
     * Returns the attributes of the current character.
     * @return The attributes of the current character.
     */
    Attributes getAttributes();
}
