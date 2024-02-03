package unibo.exiled.controller.player;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

/**
 * Implementation of the PlayerController interface.
 *
 * @param model The GameModel associated with the player.
 */
public record PlayerControllerImpl(GameModel model) implements PlayerController {

    /**
     * Moves the player in the specified direction within the given game map.
     * 
     * @param dir is the direction in which the player should move.
     */
    @Override
    public void movePlayer(final Direction dir) {
        model.movePlayer(dir);
    }

    /**
     * Gets the elemental type of the player class.
     *
     * @return The elemental type of the player class.
     */
    @Override
    public ElementalType getPlayerClass() {
        return model.getPlayer().getPlayerClass();
    }

    /**
     * Gets the current position of the player on the game map.
     * 
     * @return the position of the player.
     */
    @Override
    public Position getPlayerPosition() {
        return model.getPlayer().getPosition();
    }

    /**
     * Gets the health attribute of the player.
     * 
     * @return the health attribute value of the player.
     */
    @Override
    public double getHealth() {
        return model.getPlayer().getHealth();
    }

    /**
     * Gets the level of the player.
     * 
     * @return the level of the player.
     */
    @Override
    public int getLevel() {
        return model.getPlayer().getLevel();
    }
}
