package unibo.exiled.controller;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.Character;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;
import java.util.Set;

public interface GameController {
    InventoryController getInventoryController();
    PlayerController getPlayerController();
    MenuController getStartMenuController();
    MenuController getInGameMenuController();
    MapController getMapController();
    void movePlayer(final Direction dir);
    boolean isEnemyInCell(final Position pos);
    Character getCharacterInPosition(final Position pos);
    List<String> getImagePathOfCharacter(final Character character);
}
