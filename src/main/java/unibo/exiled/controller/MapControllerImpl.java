package unibo.exiled.controller;

import java.util.Optional;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

/**
 * Implementation of the MapController interface.
 */
public final class MapControllerImpl implements MapController {

    private final GameModel model;

    /**
     * The constructor of the game main controller.
     *
     * @param model The game model to manage the game.
     */
    public MapControllerImpl(final GameModel model) {
        this.model = model;
    }

    @Override
    public int getMapSize() {
        return model.getMapSize();
    }

    @Override
    public CellType getCellType(final Position position) {
        return model.getCellTypeOf(position);
    }


    @Override
    public boolean isEnemyInCell(final Position position) {
        final Optional<GameCharacter> gottenCharacter = model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get() instanceof Enemy;
        } else {
            return false;
        }
    }

    @Override
    public String getNameOfCharacterInPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getName();
        } else {
            return "";
        }
    }

    @Override
    public Direction getLastDirectionOfCharacterInPosition(final Position position) {
        return model.getCharacterFromPosition(position).get().getLastDirection();
    }
}
