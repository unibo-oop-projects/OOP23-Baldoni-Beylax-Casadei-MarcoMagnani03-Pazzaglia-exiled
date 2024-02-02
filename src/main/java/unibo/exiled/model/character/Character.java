package unibo.exiled.model.character;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.Position;

import java.util.Map;

/**
 * A character that can move.
 */
public interface Character {
    /**
     * Gets the MoveSet of the character to be used in a battle.
     * 
     * @return the MoveSet of the current character.
     */
    MoveSet getMoveSet();

    /**
     * Moves the character to new position.
     * 
     * @param newPosition
     */
    void move(final Position newPosition);

    /**
     * Returns the position of the current character.
     * 
     * @return the position of the current character.
     */
    Position getPosition();

    /**
     * Returns the attributes of the current character.
     * 
     * @return the attributes of the current character.
     */
    Map<AttributeIdentifier, Attribute> getAttributes();

    /**
     * Gets the standard health attribute value.
     * 
     * @return The evaluated amount of health of the character.
     */
    double getHealth();

    /**
     * Gets the name of the character.
     * 
     * @return A string representing the name of the character.
     */
    String getName();
}
