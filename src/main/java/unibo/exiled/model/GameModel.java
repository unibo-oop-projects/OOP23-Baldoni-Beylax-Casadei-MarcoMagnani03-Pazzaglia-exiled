package unibo.exiled.model;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.utilities.Position;

import java.util.Map;

public interface GameModel {
    Player getPlayer();
    Menu getStartMenu();
    Menu getInGameMenu();
    GameMap getMap();
    Map<Position,Enemy> getEnemies();
}
