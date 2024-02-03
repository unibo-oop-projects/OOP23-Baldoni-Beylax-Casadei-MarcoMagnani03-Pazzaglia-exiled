package unibo.exiled.controller;

import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;

/**
 * The game main controller.
 */
public interface GameController {
    /**
     * Gets the images of a character.
     * @return A list containing the paths of the images for the characters.
     */
    List<String> getImagePathOfCharacter(String folderPath, String name);

    /**
     * Gets the health of the player
     * @return A double representing the health of the player.
     */
    double getPlayerHealth();
    double getPlayerLevel();
    int getMapSize();
    String getPlayerClassName();
    void movePlayer(final Direction direction);
    void moveEnemies();
    boolean isOver();
    Position getPlayerPosition();
    boolean isEnemyInCell(final Position position);
    String getNameOfCharacterInPosition(final Position position);
    CellType getCellType(Position position);
}
