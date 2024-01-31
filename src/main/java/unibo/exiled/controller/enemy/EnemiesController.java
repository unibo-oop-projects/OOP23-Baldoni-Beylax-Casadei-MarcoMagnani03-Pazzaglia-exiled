package unibo.exiled.controller.enemy;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.enemy.EnemyCollection;

public interface EnemiesController {
        void moveEnemies();
        EnemyCollection getEnemies();
}
