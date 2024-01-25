package unibo.exiled.controller;

import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

public interface GameController {
    InventoryController getInventoryController();
    PlayerController getPlayerController();
    MenuController getStartMenuController();
    MenuController getInGameMenuController();
    MapController getMapController();
    void movePlayer(final Direction dir);
    boolean isEnemyInCell(final Position pos);
}
