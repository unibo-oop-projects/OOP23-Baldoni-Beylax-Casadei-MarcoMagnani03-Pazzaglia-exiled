package unibo.exiled.model;

import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Direction;

/**
 * The model of the game, its core.
 */
public interface GameModel {
    /**
     * Gets the player, the movable character.
     *
     * @return The main player.
     */
    Player getPlayer();

    /**
     * Gets the map of the game.
     *
     * @return The GameMap, the main map of the game.
     */
    GameMap getMap();

    /**
     * Gets the enemies generated on the map.
     *
     * @return An EnemyCollection with every enemy in the game.
     */
    EnemyCollection getEnemies();

    /**
     * Moves the player in a selected direction.
     *
     * @param dir The direction where to move the player.
     */
    void movePlayer(Direction dir);
}
