package unibo.exiled.controller;

import unibo.exiled.controller.menu.InGameMenuController;
import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.model.GameModel;
import unibo.exiled.model.GameModelImpl;
import unibo.exiled.model.character.Character;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;

public class GameControllerImpl implements GameController {
    private final GameModel model;
    private final PlayerController pc;
    private final InventoryController ic;
    private final MenuController inGameMenuController;
    private final MapController mpc;

    public GameControllerImpl() {
        this.model = new GameModelImpl();
        this.pc = new PlayerController(model.getPlayer());
        this.ic = new InventoryController(pc.player().getInventory());
        this.inGameMenuController = new InGameMenuController();
        this.mpc = new MapController(model.getMap());
    }

    @Override
    public void movePlayer(final Direction dir){
        final Position oldPosition = this.getPlayerController().player().getPosition();
        final Position newPosition = new Position(
                    oldPosition.x() + dir.getPosition().x(),
                    oldPosition.y() + dir.getPosition().y()
                );

        if(this.getMapController().isInBoundaries(newPosition)){
            this.getPlayerController().move(newPosition);
        }
    }

    @Override
    public List<String> getImagePathOfCharacter(final Character character){
        return List.of(
                character.getImagePath(),
                character.getImageUpPath(),
                character.getImageDownPath(),
                character.getImageLeftPath(),
                character.getImageRightPath()
        );
    }

    @Override
    public boolean isEnemyInCell(final Position pos){ return this.model.getEnemies().containsKey(pos); }

    @Override
    public boolean isOver(){ return isEnemyInCell(this.getPlayerController().getPlayerPosition()); }

    @Override
    public Character getCharacterInPosition(Position pos) { return this.model.getEnemies().get(pos); }

    @Override
    public InventoryController getInventoryController() { return this.ic; }
        
    @Override
    public PlayerController getPlayerController() { return this.pc; }

    @Override
    public MenuController getInGameMenuController() { return this.inGameMenuController; }

    @Override
    public MapController getMapController(){ return this.mpc; }

}
