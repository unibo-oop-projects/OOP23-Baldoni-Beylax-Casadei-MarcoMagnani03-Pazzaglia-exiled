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

    //
    //  CHARACTER METHODS
    //

    /**
     * Gets the image paths for a character.
     *
     * @param folderPath The folder path where the character images are stored.
     * @param name       The name of the character.
     * @return A list containing the paths of the images for the character.
     */
    List<String> getImagePathOfCharacter(String folderPath, String name);

    /**
     * Moves the player in the specified direction.
     *
     * @param direction The direction in which the player should move.
     */
    void movePlayer(Direction direction);

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
     * Gets the class name of the player.
     *
     * @return A string representing the class name of the player.
     */
    String getPlayerClassName();

    /**
     * Gets the position of the player.
     *
     * @return The position of the player.
     */
    Position getPlayerPosition();

    /**
     * Gets the names of every magic move.
     *
     * @return A list of every magic move name.
     */
    List<String> getMagicMoveNames();

    /**
     * Moves the enemies in the game.
     */
    void moveEnemies();

    /**
     * Checks if there is an enemy in the specified cell.
     *
     * @param position The position to check.
     * @return True if there is an enemy in the cell, false otherwise.
     */
    boolean isEnemyInCell(Position position);

    /**
     * Gets the name of the character in the specified position.
     *
     * @param position The position to check.
     * @return The name of the character in the specified position.
     */
    String getNameOfCharacterInPosition(Position position);

    /**
     * Performs an attack routine.
     *
     * @param isPlayerAttacking True if the attacker is the player, false otherwise.
     */
    void attack(boolean isPlayerAttacking);

    //
    //  ITEM METHODS
    //

    /**
     * Attempts to use the specified item from the player's inventory.
     *
     * @param item The item name to use.
     * @return true if the item was successfully used, false otherwise.
     */
    boolean useItem(String item);

    /**
     * Gets an association of items and their quantity.
     *
     * @return A map of Item names and their quantity for the view.
     */
    Map<String, Integer> getItems();

    /**
     * Gets the modification value of the item.
     *
     * @param itemName The item to look for.
     * @return A double value representing how strong is the item.
     */
    double getItemValor(String itemName);

    /**
     * Gets the description of an item based on its name.
     *
     * @param itemName The name of the item.
     * @return A string representing the description of the item.
     */
    String getItemDescription(String itemName);

    /**
     * Gets the type of the item with a certain name.
     *
     * @param itemName The name of the item to look for.
     * @return The ItemType of the selected itemName.
     */
    ItemType getItemType(String itemName);

    /**
     * Gets the name of the boosted attribute of an item.
     *
     * @param itemName The item that boosts the attribute.
     * @return A String representing the name of the boosted attribute.
     */
    String getItemBoostedAttributeName(String itemName);

    /**
     * Gets the duration of an item based of its name.
     *
     * @param itemName The name of the item.
     * @return An integer representing the duration of the item (in turns)
     */
    int getItemDuration(String itemName);

    //
    //  MAP METHODS
    //

    /**
     * Gets the size of the game map.
     *
     * @return An integer representing the size of the game map.
     */
    int getMapSize();

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    boolean isOver();

    /**
     * Gets the cell type at the specified position.
     *
     * @param position The position to check.
     * @return The cell type at the specified position.
     */
    CellType getCellType(Position position);

}
