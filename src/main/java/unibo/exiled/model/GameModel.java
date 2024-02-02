package unibo.exiled.model;

import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Direction;

public interface GameModel {
    Player getPlayer();

    GameMap getMap();

    EnemyCollection getEnemies();

    void movePlayer(Direction dir);
}
