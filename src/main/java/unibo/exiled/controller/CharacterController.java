package unibo.exiled.controller;

import unibo.exiled.model.utilities.Direction;

public interface CharacterController {

    /**
     * Moves the character by the selected direction.
     * @param direction
     */
    void move(final Direction direction);
}
