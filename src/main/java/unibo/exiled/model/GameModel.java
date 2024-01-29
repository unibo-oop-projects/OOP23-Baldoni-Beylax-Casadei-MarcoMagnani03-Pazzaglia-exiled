package unibo.exiled.model;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Position;

import java.util.List;

public interface GameModel {
    Player getPlayer();
    GameMap getMap();
    List<Enemy> getEnemies();
    void killEnemy(final Position position, final Enemy enemy);
}
