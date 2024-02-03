package unibo.exiled.controller.enemy;

import unibo.exiled.model.character.enemy.EnemyCollection;

/**
 * This interface represents a controller for managing enemies in the game.
 */
public interface EnemiesController {
    /**
     * Moves all the enemies in the game.
     */
    void moveEnemies();

    /**
     * Gets the collection of enemies in the game.
     * 
     * @return the collection of enemies.
     */
    EnemyCollection getEnemies();
}
