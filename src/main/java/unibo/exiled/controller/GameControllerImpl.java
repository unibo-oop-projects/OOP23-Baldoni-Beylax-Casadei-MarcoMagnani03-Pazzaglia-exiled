package unibo.exiled.controller;

import unibo.exiled.controller.enemy.EnemiesController;
import unibo.exiled.controller.enemy.EnemiesControllerImpl;
import unibo.exiled.controller.menu.InGameMenuController;
import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.controller.player.PlayerController;
import unibo.exiled.controller.player.PlayerControllerImpl;
import unibo.exiled.model.GameModel;
import unibo.exiled.model.GameModelImpl;
import unibo.exiled.model.character.Character;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.io.File;
import java.util.List;

public class GameControllerImpl implements GameController {
    private final GameModel model;
    private final PlayerController playerController;
    private final InventoryController inventoryController;
    private final MenuController inGameMenuController;
    private final EnemiesController enemiesController;

    public GameControllerImpl() {
        this.model = new GameModelImpl();
        this.inventoryController = new InventoryController(model.getPlayer());
        this.inGameMenuController = new InGameMenuController();
        this.playerController = new PlayerControllerImpl(model);
        this.enemiesController = new EnemiesControllerImpl(model);
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
    public List<String> getImagePathOfCharacter(final String path, final String name) {
        final String upConst = "_up";
        final String downConst = "_down";
        final String leftConst = "_left";
        final String rightConst = "_right";
        return List.of(
                path,
                name + upConst,
                name + downConst,
                name + leftConst,
                name + rightConst
        );
    }

    @Override
    public GameMap getMap() {
        return this.model.getMap();
    }

    @Override
    public Character getCharacterInPosition(final Position pos) {
        if (this.model.getPlayer().getPosition().equals(pos)) {
            return this.model.getPlayer();
        } else {
            return this.model.getEnemies().getEnemyFromPosition(pos).get();
        }
    }
    
    @Override
    public Enemy getEnemyFromPosition(final Position pos) {
        return this.model.getEnemies().getEnemyFromPosition(pos).get();
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
    public PlayerController getPlayerController() { return this.playerController; }

    @Override
    public EnemiesController getEnemiesController() { return this.enemiesController; }

    @Override
    public void moveEnemies() {
        this.getEnemiesController().moveEnemies();
    }

}
