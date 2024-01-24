package unibo.exiled.controller;

import unibo.exiled.model.utilities.Direction;

public interface GameController {
    InventoryController getInventoryController();
    PlayerController getPlayerController();
    MenuController getStartMenuController();
    MenuController getInGameMenuController();
    MapController getMapController();
    void movePlayer(final Direction dir);
}
