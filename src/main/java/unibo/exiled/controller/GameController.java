package unibo.exiled.controller;

import unibo.exiled.controller.enemy.EnemiesController;
import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.controller.player.PlayerController;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Position;

import java.util.List;

/**
 * The main controller interface for the game, providing access to various
 * sub-controllers and game-related functionalities.
 */
public interface GameController {
    /**
     * Gets the inventory-specific controller.
     *
     * @return the InventoryController.
     */
    InventoryController getInventoryController();

    /**
     * Gets the menu-specific controller.
     *
     * @return the MenuController.
     */
    MenuController getInGameMenuController();

    /**
     * Gets the player-specific controller.
     *
     * @return the PlayerController.
     */
    PlayerController getPlayerController();

    /**
     * Gets the enemies controller.
     *
     * @return the EnemiesController.
     */
    EnemiesController getEnemiesController();

    /**
     * Gets the combat controller.
     *
     * @return the CombatController.
     */
    CombatController getCombatController();

    // Image names for the view

    /**
     * Gets the image paths necessary to render the sprites of the characters on the
     * map.
     *
     * @param gameCharacter the gameCharacter which paths need to be fetched.
     * @return a list containing the strings of the paths.
     */
    List<String> getImagePathOfCharacter(GameCharacter gameCharacter);

    /**
     * Gets the image paths necessary to render the sprites of the character with a
     * certain name.
     *
     * @param path The path to the images.
     * @param name The name (file) of the character which strings are to be fetched.
     * @return A list of paths to the right images.
     */
    List<String> getImagePathOfCharacter(String path, String name);

    /**
     * Gets the game map and its methods.
     *
     * @return the GameMap
     */
    GameMap getMap();

    /**
     * Returns the character in the given position, usually an enemy or the player.
     *
     * @param position the position to check.
     * @return the character present in the selected position.
     */
    GameCharacter getCharacterInPosition(Position position);

    /**
     * Return the enemy in the given position.
     *
     * @param position the position to check.
     * @return the enemy present in the selected position.
     */
    Enemy getEnemyFromPosition(Position position);

    /**
     * Checks if there is an enemy in the selected cell.
     *
     * @param pos the position to check.
     * @return true if the position pos contains an enemy, false otherwise.
     */
    boolean isEnemyInCell(Position pos);

    /**
     * Checks if the game has ended (e.g. no more health)
     *
     * @return true if the endgame conditions are met, false otherwise.
     */
    boolean isOver();

    /**
     * Moves the enemies in the game.
     */
    void moveEnemies();
}
