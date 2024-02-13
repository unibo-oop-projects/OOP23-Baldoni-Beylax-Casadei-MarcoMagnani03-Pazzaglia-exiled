package unibo.exiled.model.character;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Position;

import java.util.Map;

/**
 * A character that can move.
 */
public interface GameCharacter {
    /**
     * Gets the MoveSet of the character to be used in a battle.
     *
     * @return the MoveSet of the current character.
     */
    MoveSet getMoveSet();

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
     * Gets the health cap attribute value.
     *
     * @return The evaluated amount of health cap of the character.
     */
    double getHealthCap();

    /**
     * Gets the name of the character.
     *
     * @return A string representing the name of the character.
     */
    String getName();

    /**
     * Gets the last direction of a character.
     *
     * @return A direction, the last one the character followed.
     */
    Direction getLastDirection();

    /**
     * Gets the Elemental Type of the enemy.
     *
     * @return The Elemental Type of the enemy.
     */
    ElementalType getType();

    /**
     * Adds a new magical move to the set.
     *
     * @param newMove The new magical move to be added.
     */
    void addMagicMove(MagicMove newMove);

    /**
     * Sets the last direction of the character.
     *
     * @param direction The direction to set.
     */
    void setLastDirection(Direction direction);

    /**
     * Increases the modifier of the specified attribute for the character.
     *
     * @param id       The identifier of the attribute.
     * @param modifier The amount by which the modifier is increased.
     */
    void increaseAttributeModifier(AttributeIdentifier id, double modifier);

    /**
     * Increases the value of the specified attribute for the character.
     *
     * @param id    The identifier of the attribute.
     * @param value The amount by which the attribute value is increased.
     */
    void increaseAttributeValue(AttributeIdentifier id, double value);

    /**
     * Decreases the value of the specified attribute for the character.
     *
     * @param id    The identifier of the attribute.
     * @param value The amount by which the attribute value is decreased.
     */
    void decreaseAttributeModifier(AttributeIdentifier id, double value);

    /**
     * Decreases the value of the specified attribute for the character.
     *
     * @param id    The identifier of the attribute.
     * @param value The amount by which the attribute value is decreased.
     */
    void decreaseAttributeValue(AttributeIdentifier id, double value);

    /**
     * Moves the character to the specified position.
     *
     * @param newPosition The new position to which the character is moved.
     */
    void move(Position newPosition);

    /**
     * States which kind of sprite is to be used.
     * 
     * @return True if the moving sprite is the right one, false otherwise.
     */
    boolean spriteIsMoving();

}
