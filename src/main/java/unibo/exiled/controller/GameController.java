package unibo.exiled.controller;

import unibo.exiled.controller.enemy.EnemiesController;
import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.controller.player.PlayerController;
import unibo.exiled.model.character.Character;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;

public interface GameController {
    /**
     * Gets the inventory-specific controller.
     * @return the InventoryController.
     */
    InventoryController getInventoryController();

    /**
     * Gets the menu-specific controller.
     * @return the MenuController.
     */
    MenuController getInGameMenuController();

    /**
     * Gets the player-specific controller.
     * @return the PlayerController.
     */
    PlayerController getPlayerController();


    /**
     * Gets the enemies controller.
     * @return the EnemiesController.
     */
    EnemiesController getEnemiesController();


    //Image names for the view

    /**
     * Gets the image paths necessary to render the sprites of the characters on the map.
     * @param character the character which paths needs to be fetched.
     * @return a list containing the strings of the paths.
     */
    List<String> getImagePathOfCharacter(final Character character);

    /**
     * Gets the game map and its methods.
     * @return the GameMap
     */
    GameMap getMap();

    /**
     * Returns the character in the given position, usually an enemy or the player.
     * @param position the position to check.
     * @return the character present in the selected position.
     */
    Character getCharacterInPosition(final Position position);

    /**
     * Return the enemy in the given position
     * @param position the position to check
     * @return the enemy present in the selected position.
     */
    Enemy getEnemyFromPosition(final Position position);

    /**
     * Checks if there is an enemy in the selected cell.
     * @param pos the position to check.
     * @return true if the position pos contains an enemy, false otherwise.
     */
    boolean isEnemyInCell(final Position pos);

    /**
     * Checks if the game has ended (e.g. no more health)
     * @return true if the endgame conditions are met, false otherwise.
     */
    boolean isOver();

    /**
     * Moves the player in the specified direction.
     * @param dir is the direction in which the player should move.
     */
    void movePlayer(final Direction dir);

    
    void moveEnemies();
}
