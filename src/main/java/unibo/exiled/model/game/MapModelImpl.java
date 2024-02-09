package unibo.exiled.model.game;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.utilities.Position;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The implementation of the model of the map.
 */
public final class MapModelImpl implements MapModel {
    private final GameMap map;
    private final GameModel model;

    /**
     * The constructor of the map manager.
     *
     * @param model The game model.
     */
    public MapModelImpl(final GameModel model) {

        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        final int size = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));
        this.map = new GameMapImpl(size);
        this.model = model;
    }

    @Override
    public boolean isInBoundaries(final Position position) {
        return this.map.getCellStates().containsKey(position);
    }

    @Override
    public int getSize() {
        return this.map.getSize();
    }

    @Override
    public CellType getCellType(final Position position) {
        if (this.map.getCellStates().containsKey(position)) {
            return this.map.getCellStates().get(position);
        } else {
            throw new NoSuchElementException("The specified position isn't a cell.");
        }
    }

    @Override
    public Position getCornerOfType(final CellType type) {
        return this.map.getCornerPositionOfElement(type);
    }


    @Override
    public boolean isCornerOfMap(final Position position) {
        final int x = position.x();
        final int y = position.y();
        return x == 0 && y == 0
                || x == getSize() && y == getSize()
                || x == 0 && y == getSize()
                || y == 0 && x == getSize();
    }

    @Override
    public Optional<GameCharacter> getCharacterFromPosition(final Position pos) {
        return this.model.getCharacterModel().getCharacterFromPosition(pos);
    }
}
