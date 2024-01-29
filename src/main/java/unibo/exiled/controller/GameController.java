package unibo.exiled.controller;

import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.model.character.Character;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;

public interface GameController {
    // Controllers
    InventoryController getInventoryController();
    PlayerController getPlayerController();
    MenuController getInGameMenuController();
    MapController getMapController();
    List<EnemyController> getEnemyController();

    // Utility game controller methods
    void movePlayer(final Direction dir);
    void moveEnemies();
    boolean isEnemyInCell(final Position pos);

    Character getCharacterInPosition(final Position pos);

    Enemy getEnemyInPosition(final Position pos);
    List<String> getImagePathOfCharacter(final Character character);
    boolean isOver();
}
