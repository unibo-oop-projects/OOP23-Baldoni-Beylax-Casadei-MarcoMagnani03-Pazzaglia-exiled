package unibo.exiled.model.game;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Position;

import java.util.Optional;

/**
 * The model of the game, its core.
 */
public interface GameModel {
    /**
     * Gets the player, the movable character.
     *
     * @return The main player.
     */
    void movePlayer(Direction dir);

    /**
     * Moves the enemies in random directions.
     */
    void moveEnemies();

    /**
     * Gets the evaluated attribute of the player.
     * @param id The attribute identifier to get.
     * @return A double representing the evaluated value of the attribute.
     */
    double getPlayerAttributeOf(AttributeIdentifier id);

    double getPlayerLevel();
    ElementalType getPlayerClass();
    int getMapSize();
    Position getPlayerPosition();
    Optional<GameCharacter> getCharacterFromPosition(final Position pos);
    CellType getCellTypeOf(Position position);
}
