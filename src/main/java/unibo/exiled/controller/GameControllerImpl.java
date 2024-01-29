package unibo.exiled.controller;

import unibo.exiled.controller.menu.InGameMenuController;
import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.controller.player.PlayerController;
import unibo.exiled.controller.player.PlayerControllerImpl;
import unibo.exiled.model.GameModel;
import unibo.exiled.model.GameModelImpl;
import unibo.exiled.model.character.Character;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Position;

import java.util.List;

public class GameControllerImpl implements GameController {
    private final GameModel model;
    private final PlayerController playerController;
    private final InventoryController inventoryController;
    private final MenuController inGameMenuController;

    public GameControllerImpl() {
        this.model = new GameModelImpl();
        this.inventoryController = new InventoryController(model.getPlayer().getInventory());
        this.inGameMenuController = new InGameMenuController();
        this.playerController = new PlayerControllerImpl(model.getPlayer());
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
    public GameMap getMap() {
        return this.model.getMap();
    }

    @Override
    public Character getCharacterInPosition(final Position pos) {
        if(this.model.getPlayer().getPosition().equals(pos)){
            return this.model.getPlayer();
        }
        else{
           return this.model.getEnemies().getEnemyFromPosition(pos).get();
        }
    }

    @Override
    public boolean isOver() {
        return this.playerController.getHealth() <= 0;
    }

    @Override
    public boolean isEnemyInCell(final Position pos){
        return this.model.getEnemies().getEnemyFromPosition(pos).isPresent();
    }

    @Override
    public InventoryController getInventoryController() { return this.inventoryController; }

    @Override
    public MenuController getInGameMenuController() { return this.inGameMenuController; }

    @Override
    public PlayerController getPlayerController() {
        return this.playerController;
    }

}
