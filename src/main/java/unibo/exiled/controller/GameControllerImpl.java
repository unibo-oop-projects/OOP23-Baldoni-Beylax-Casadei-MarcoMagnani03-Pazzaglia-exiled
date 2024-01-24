package unibo.exiled.controller;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

public class GameControllerImpl implements GameController {
    private final GameModel gameModel;
    private final PlayerController pc;
    private final InventoryController ic;
    private final MenuController startMenuController;
    private final MenuController inGameMenuController;
    private final MapController mpc;

    public GameControllerImpl(final int mapSize) {
        this.gameModel = new GameModel(mapSize);
        this.pc = new PlayerController(gameModel.getPlayer());
        this.ic = new InventoryController(pc.getPlayer().getInventory());
        this.startMenuController = new MenuController(gameModel.getStartMenu());
        this.inGameMenuController = new MenuController(gameModel.getInGameMenu());
        this.mpc = new MapController(gameModel.getMap());
    }

    public void movePlayer(final Direction dir){
        final Position oldPosition = this.getPlayerController().getPlayer().getPosition();
        final Position newPosition = new Position(
                oldPosition.x() + dir.getPosition().x(),
                oldPosition.y() + dir.getPosition().y());
        if(this.getMapController().getMap().isInBoundaries(newPosition)){
            this.getPlayerController().move(newPosition);
        }
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
    public MenuController getStartMenuController() {
        return this.startMenuController;
    }

    @Override
    public MenuController getInGameMenuController() {
        return this.inGameMenuController;
    }

    @Override
    public MapController getMapController(){return this.mpc;}
}
