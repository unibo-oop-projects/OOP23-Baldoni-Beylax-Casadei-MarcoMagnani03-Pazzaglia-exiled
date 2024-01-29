package unibo.exiled.controller;

import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.controller.player.PlayerController;
import unibo.exiled.model.character.Character;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Position;

import java.util.List;

public interface GameController {
    /**
     * Gets the inventory-specific controller.
     * @return The InventoryController.
     */
    InventoryController getInventoryController();

    /**
     * Gets the menu-specific controller.
     * @return The MenuController.
     */
    MenuController getInGameMenuController();

    /**
     * Gets the player-specific controller.
     * @return The PlayerController.
     */
    PlayerController getPlayerController();

    //Image names for the view

    /**
     * Gets the image paths necessary to render the sprites of the characters on the map.
     * @param character The character which paths needs to be fetched.
     * @return A list containing the strings of the paths.
     */
    List<String> getImagePathOfCharacter(final Character character);

    /**
     * Gets the game map and its methods.
     * @return The GameMap
     */
    GameMap getMap();

    /**
     * Returns the character in the given position, usually an enemy or the player.
     * @param position The position to check.
     * @return The character present in the selected position.
     */
    Character getCharacterInPosition(final Position position);

    /**
     * Checks if there is an enemy in the selected cell.
     * @param pos The position to check.
     * @return True if the position pos contains an enemy, false otherwise.
     */
    boolean isEnemyInCell(final Position pos);

    /**
     * Checks if the game has ended (e.g. no more health)
     * @return True if the endgame conditions are met, false otherwise.
     */
    boolean isOver();
}
