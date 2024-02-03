package unibo.exiled.controller;

import unibo.exiled.model.item.ItemType;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;
import java.util.Map;

/**
 * The main controller for the game.
 */
public interface GameController {

    /**
     * Gets the image paths for a character.
     *
     * @param folderPath The folder path where the character images are stored.
     * @param name       The name of the character.
     * @return A list containing the paths of the images for the character.
     */
    List<String> getImagePathOfCharacter(String folderPath, String name);

    /**
     * Gets the health of the player.
     *
     * @return A double representing the health of the player.
     */
    double getPlayerHealth();

    /**
     * Gets the level of the player.
     *
     * @return A double representing the level of the player.
     */
    double getPlayerLevel();

    /**
     * Gets the size of the game map.
     *
     * @return An integer representing the size of the game map.
     */
    int getMapSize();

    /**
     * Gets the class name of the player.
     *
     * @return A string representing the class name of the player.
     */
    String getPlayerClassName();

    /**
     * Moves the player in the specified direction.
     *
     * @param direction The direction in which the player should move.
     */
    void movePlayer(final Direction direction);

    /**
     * Moves the enemies in the game.
     */
    void moveEnemies();

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    boolean isOver();

    /**
     * Gets the position of the player.
     *
     * @return The position of the player.
     */
    Position getPlayerPosition();

    /**
     * Checks if there is an enemy in the specified cell.
     *
     * @param position The position to check.
     * @return True if there is an enemy in the cell, false otherwise.
     */
    boolean isEnemyInCell(final Position position);

    /**
     * Gets the name of the character in the specified position.
     *
     * @param position The position to check.
     * @return The name of the character in the specified position.
     */
    String getNameOfCharacterInPosition(final Position position);

    /**
     * Gets the cell type at the specified position.
     *
     * @param position The position to check.
     * @return The cell type at the specified position.
     */
    CellType getCellType(Position position);

    List<String> getMagicMoveNames();

    void attack(boolean cond);

    Map<String, Integer> getItems();

    String getItemDescription(String itemName);

    double getItemAmout(String itemName);

    ItemType getItemType(String itemName);

    String getItemBoostedAttributeName(String itemName);

    int getItemDuration(String itemName);
}
