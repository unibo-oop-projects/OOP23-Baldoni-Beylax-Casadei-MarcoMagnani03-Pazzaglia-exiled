package unibo.exiled.controller.enemy;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;

import java.util.Random;

/**
 * The implementation of the EnemiesController interface responsible for
 * handling enemy movement and behavior.
 */
public class EnemiesControllerImpl implements EnemiesController {

    private final GameModel model;

    /**
     * Constructs an EnemiesControllerImpl with the specified GameModel.
     *
     * @param model The GameModel to associate with the controller.
     */
    public EnemiesControllerImpl(final GameModel model) {
        this.model = model;
    }

    /**
     * Moves all the enemies in the game.
     */
    @Override
    public void moveEnemies() {
        model.moveEnemies();
    }

}
