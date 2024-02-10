package unibo.exiled.model.move;

import unibo.exiled.utilities.ElementalType;

/**
 * Implementation of the MagicMove interface representing a magical move in the game.
 *
 * @param name        The name of the magic move.
 * @param description The description of the magic move.
 * @param power       The power of the magic move.
 * @param type        The elemental type of the magic move.
 */
public record MagicMoveImpl(String name, String description, double power, ElementalType type) implements MagicMove {
}
