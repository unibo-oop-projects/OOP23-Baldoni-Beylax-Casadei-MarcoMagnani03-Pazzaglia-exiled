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
    void movePlayer(Direction dir);
    void moveEnemies();
}
