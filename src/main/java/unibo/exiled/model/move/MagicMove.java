package unibo.exiled.model.move;

import unibo.exiled.utilities.ElementalType;

/**
 * The MagicMove interface represents a magical move in the game.
 */
public interface MagicMove {

    /**
     * Gets the name of the magic move.
     *
     * @return The name of the magic move.
     */
    String name();

    /**
     * Gets the description of the magic move.
     *
     * @return The description of the magic move.
     */
    String description();

    /**
     * Gets the power of the magic move.
     *
     * @return The power of the magic move.
     */
    double power();

    /**
     * Gets the elemental type of the magic move.
     *
     * @return The elemental type of the magic move.
     */
    ElementalType type();

    /**
     * Gets the minimum level to learn the magic move.
     *
     * @return The minimum level needed to learn the move.
     */
    int minimumLevelToLearn();
}
