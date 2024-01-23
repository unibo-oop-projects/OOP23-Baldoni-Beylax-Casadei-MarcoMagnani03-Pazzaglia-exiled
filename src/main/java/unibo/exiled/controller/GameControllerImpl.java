package unibo.exiled.controller;

import unibo.exiled.model.GameModel;

public class GameControllerImpl implements GameController {
    private final GameModel gameModel;
    private final PlayerController pc;
    private final InventoryController ic;
    private final MenuController mc;

    public GameControllerImpl(final int mapSize) {
        this.gameModel = new GameModel(mapSize);
        this.pc = new PlayerController(gameModel.getPlayer());
        this.ic = new InventoryController(pc.getPlayer().getInventory());
        this.mc = new MenuController(gameModel.getMenu());
    }

    @Override
    public InventoryController getInventoryController() {
        return this.ic;
    }

    @Override
    public PlayerController getPlayerController() {
        return this.pc;
    }

    @Override
    public MenuController getMenuController() {
        return this.mc;
    }
}
