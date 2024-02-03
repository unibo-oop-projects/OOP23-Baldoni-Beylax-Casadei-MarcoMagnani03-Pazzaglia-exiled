package unibo.exiled.model.map;

import unibo.exiled.model.utilities.Position;

public interface GameMap {
    /**
     * Returns the height of the map.
     * @return An Integer representing the height of the map.
     */
    int getHeight();

    /**
     * Returns the width of the map.
     * @return An Integer representing the width of the map.
     */
    int getWidth();

    /**
     * Returns the type of cell in a certain position.
     * @param cell The cell position to get the type of.
     * @return The CellType representing the type of the selected cell.
     */
    CellType getCellType(Position cell);

    /**
     * Checks if the selected position is in the boundaries of the map.
     * @param cell The position to check.
     * @return True if the position is in the boundaries of the map, false otherwise.
     */
    boolean isInBoundaries(Position cell);
}
