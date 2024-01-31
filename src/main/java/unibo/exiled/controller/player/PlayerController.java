package unibo.exiled.controller.player;

import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;


/**
 * Interface representing a controller for the player in the game.
 */
public interface PlayerController {
    /**
     * Retrieves the player object.
     * @return the player object.
     */
    Player getPlayer();

    /**
     * Moves the player in the specified direction within the given game map.
     * @param dir is the direction in which the player should move.
     * @param map is the game map on which the player is moving.
     */
    void movePlayer(final Direction dir, final GameMap map);

    /**
     * Gets the health attribute of the player.
     * @return the health attribute value of the player.
     */
    double getHealth();

    /**
     * Gets the level of the player.
     * @return the level of the player.
     */
    int getLevel();

    /**
     * Gets the class of the player.
     * @return the class of the player.
     */
    ElementalType getPlayerClass();

    /**
     * Gets the current position of the player on the game map.
     * @return the position of the player.
     */
    Position getPlayerPosition();
}
