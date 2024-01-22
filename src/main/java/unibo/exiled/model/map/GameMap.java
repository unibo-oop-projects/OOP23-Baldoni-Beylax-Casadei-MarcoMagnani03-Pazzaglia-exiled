package unibo.exiled.model.map;

import unibo.exiled.model.utilities.Position;

public interface GameMap {
    int getHeight();
    int getWidth();
    CellType getCellType(final Position cell);
    boolean isInBoundaries(final Position cell);
}
