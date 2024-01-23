package unibo.exiled.controller;

import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

public interface CharacterController {

    /**
     * Moves the character in the selected position.
     * @param position The new position to move the character to.
     */
    void move(final Position position);
}
