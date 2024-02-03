package unibo.exiled.model.game;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

import java.util.Optional;
import java.util.Set;

/**
 * The model of the game, its core.
 */
public interface GameModel {
    /**
     * Moves the player in the selected direction.
     *
     * @param dir The direction where to move the player.
     */
    void movePlayer(Direction dir);

    /**
     * Moves the enemies in random directions.
     */
    void moveEnemies();

    /**
     * Gets the evaluated attribute of the player.
     *
     * @param id The attribute identifier to get.
     * @return A double representing the evaluated value of the attribute.
     */
    double getPlayerAttributeOf(AttributeIdentifier id);

    /**
     * Gets the level of the player.
     *
     * @return A double representing the level of the player.
     */
    double getPlayerLevel();

    /**
     * Gets the elemental class of the player.
     *
     * @return The elemental class of the player.
     */
    ElementalType getPlayerClass();

    /**
     * Gets the size of the map.
     *
     * @return An integer representing the size of the map.
     */
    int getMapSize();

    /**
     * Gets the position of the player.
     *
     * @return The position of the player.
     */
    Position getPlayerPosition();

    /**
     * Gets the character in the selected cell.
     *
     * @param pos The cell where to search for the character.
     * @return An optional containing the character if found, empty otherwise.
     */
    Optional<GameCharacter> getCharacterFromPosition(Position pos);

    /**
     * Gets the type of the selected cell.
     *
     * @param position The position of the cell to check.
     * @return A CellType enum representing the type of the cell.
     */
    CellType getCellTypeOf(Position position);

    /**
     * Gets a set of every magic move in the game.
     *
     * @return A set of MagicMoves.
     */
    Set<MagicMove> getMagicMoves();
}
