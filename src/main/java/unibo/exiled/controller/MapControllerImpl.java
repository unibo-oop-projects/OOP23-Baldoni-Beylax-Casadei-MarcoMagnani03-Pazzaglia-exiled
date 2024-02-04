package unibo.exiled.controller;

import java.util.Optional;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Position;

/**
 * Implementation of the MapController interface.
 */
public class MapControllerImpl implements MapController {

    private final GameModel model;

    /**
     * The constructor of the game main controller.
     *
     * @param model The game model to manage the game.
     */
    public MapControllerImpl(final GameModel model) {
        this.model = model;
    }

    /**
     * Gets the size of the game map.
     *
     * @return An integer representing the size of the game map.
     */
    @Override
    public int getMapSize() {
        return model.getMapSize();
    }

    /**
     * Gets the type of cell at the specified position on the game map.
     *
     * @param position The position to check on the game map.
     * @return The CellType representing the type of cell at the specified position.
     */
    @Override
    public CellType getCellType(final Position position) {
        return model.getCellTypeOf(position);
    }

    /**
     * Checks if an enemy is present in the cell at the specified position on the game map.
     *
     * @param position The position to check on the game map.
     * @return True if an enemy is present, false otherwise.
     */
    @Override
    public boolean isEnemyInCell(final Position position) {
        final Optional<GameCharacter> gottenCharacter = model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get() instanceof Enemy;
        } else {
            return false;
        }
    }

    /**
     * Gets the name of the character in the cell at the specified position on the game map.
     *
     * @param position The position to check on the game map.
     * @return The name of the character in the cell, or an empty string if no character is present.
     */
    @Override
    public String getNameOfCharacterInPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getName();
        } else {
            return "";
        }
    }
}
