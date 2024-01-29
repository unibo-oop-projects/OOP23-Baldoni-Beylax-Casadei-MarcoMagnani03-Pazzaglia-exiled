package unibo.exiled.controller.enemy;

import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.map.GameMap;

public interface EnemiesController {
        void moveEnemies(final GameMap map, final Player player);
        EnemyCollection getEnemies();
}
