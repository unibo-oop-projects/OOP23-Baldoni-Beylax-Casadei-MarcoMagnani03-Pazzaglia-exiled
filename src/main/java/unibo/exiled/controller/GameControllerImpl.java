package unibo.exiled.controller;

import unibo.exiled.controller.enemy.EnemiesController;
import unibo.exiled.controller.enemy.EnemiesControllerImpl;
import unibo.exiled.controller.menu.InGameMenuController;
import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.controller.player.PlayerController;
import unibo.exiled.controller.player.PlayerControllerImpl;
import unibo.exiled.model.GameModel;
import unibo.exiled.model.GameModelImpl;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Position;

import java.io.File;
import java.util.List;

/**
 * Implementation of the GameController interface.
 */
public class GameControllerImpl implements GameController {
    private final GameModel model;
    private final PlayerController playerController;
    private final InventoryController inventoryController;
    private final MenuController inGameMenuController;
    private final EnemiesController enemiesController;
    private final CombatController combatController;

    /**
     * Constructs a GameControllerImpl, initializing the model and
     * controllers.
     */
    public GameControllerImpl() {
        this.model = new GameModelImpl();
        this.inventoryController = new InventoryController(model.getPlayer());
        this.inGameMenuController = new InGameMenuController();
        this.playerController = new PlayerControllerImpl(model);
        this.enemiesController = new EnemiesControllerImpl(model);
        this.combatController = new CombatController(model.getPlayer());
    }

    /**
     * Retrieves the image path for the given gameCharacter.
     *
     * @param gameCharacter The gameCharacter for which to retrieve the image path.
     * @return The image path for the gameCharacter.
     */
    @Override
    public List<String> getImagePathOfCharacter(final GameCharacter gameCharacter) {
        if (gameCharacter instanceof Enemy) {
            return this.getImagePathOfCharacter("enemy", gameCharacter.getName()
                    + File.separator
                    + gameCharacter.getName());
        } else {
            return this.getImagePathOfCharacter("player", "boy");
        }
    }

    /**
     * Retrieves the image path for the character with the specified path and name.
     *
     * @param path The path of the character images.
     * @param name The name of the character.
     * @return The image path for the character.
     */
    @Override
    public List<String> getImagePathOfCharacter(final String path, final String name) {
        final String upConst = "_up";
        final String downConst = "_down";
        final String leftConst = "_left";
        final String rightConst = "_right";
        return List.of(path, name + upConst, name + downConst, name + leftConst, name + rightConst);
    }

    /**
     * Retrieves the game map.
     *
     * @return The game map.
     */
    @Override
    public GameMap getMap() {
        return this.model.getMap();
    }

    /**
     * Retrieves the character in the specified position.
     *
     * @param pos The position to check.
     * @return The character in the specified position.
     */
    @Override
    public GameCharacter getCharacterInPosition(final Position pos) {
        if (this.model.getPlayer().getPosition().equals(pos)) {
            return this.model.getPlayer();
        } else {
            return this.model.getEnemies().getEnemyFromPosition(pos).get();
        }
    }

    /**
     * Retrieves the enemy in the specified position.
     *
     * @param pos The position to check.
     * @return The enemy in the specified position.
     */
    @Override
    public Enemy getEnemyFromPosition(final Position pos) {
        return this.model.getEnemies().getEnemyFromPosition(pos).get();
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    @Override
    public boolean isOver() {
        return this.playerController.getHealth() <= 0;
    }

    /**
     * Checks if an enemy is in the specified cell.
     *
     * @param pos The position to check.
     * @return True if an enemy is in the cell, false otherwise.
     */
    @Override
    public boolean isEnemyInCell(final Position pos) {
        return this.model.getEnemies().getEnemyFromPosition(pos).isPresent();
    }

    /**
     * Retrieves the inventory controller.
     *
     * @return The inventory controller.
     */
    @Override
    public InventoryController getInventoryController() {
        return this.inventoryController;
    }

    /**
     * Retrieves the in-game menu controller.
     *
     * @return The in-game menu controller.
     */
    @Override
    public MenuController getInGameMenuController() {
        return this.inGameMenuController;
    }

    /**
     * Retrieves the player controller.
     *
     * @return The player controller.
     */
    @Override
    public PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * Retrieves the enemies controller.
     *
     * @return The enemies controller.
     */
    @Override
    public EnemiesController getEnemiesController() {
        return this.enemiesController;
    }

    /**
     * Retrieves the combat controller.
     *
     * @return The combat controller.
     */
    @Override
    public CombatController getCombatController() {
        return this.combatController;
    }

    /**
     * Moves the enemies in the game.
     */
    @Override
    public void moveEnemies() {
        this.getEnemiesController().moveEnemies();
    }
}
