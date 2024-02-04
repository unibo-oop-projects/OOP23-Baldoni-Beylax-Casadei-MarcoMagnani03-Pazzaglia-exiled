package unibo.exiled.model.game;

import unibo.exiled.model.map.CellType;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.utilities.Position;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The implementation of the model of the map.
 */
public final class MapModelImpl implements MapModel {
    private final GameMap map;

    /**
     * The constructor of the map manager.
     *
     * @param size The size of the map to create.
     */
    public MapModelImpl(final int size) {
        this.map = new GameMapImpl(Objects.requireNonNull(size));
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
}
