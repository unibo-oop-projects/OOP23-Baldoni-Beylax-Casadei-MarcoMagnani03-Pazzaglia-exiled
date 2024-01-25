package unibo.exiled.controller;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.GameModelImpl;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

public class GameControllerImpl implements GameController {
    private final GameModel model;
    private final PlayerController pc;
    private final InventoryController ic;
    private final MenuController startMenuController;
    private final MenuController inGameMenuController;
    private final MapController mpc;

    public GameControllerImpl() {
        this.model = new GameModelImpl();
        this.pc = new PlayerController(model.getPlayer());
        this.ic = new InventoryController(pc.player().getInventory());
        this.startMenuController = new MenuController(model.getStartMenu());
        this.inGameMenuController = new MenuController(model.getInGameMenu());
        this.mpc = new MapController(model.getMap());
    }

    public void movePlayer(final Direction dir){
        final Position oldPosition = this.getPlayerController().player().getPosition();
        final Position newPosition = new Position(
                oldPosition.x() + dir.getPosition().x(),
                oldPosition.y() + dir.getPosition().y());
        if(this.getMapController().isInBoundaries(newPosition)){
            this.getPlayerController().move(newPosition);
        }
    }

    public boolean isEnemyInCell(final Position pos){
        return this.model.getEnemies().containsKey(pos);
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
