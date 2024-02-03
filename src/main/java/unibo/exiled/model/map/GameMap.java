package unibo.exiled.model.map;

import unibo.exiled.model.utilities.Position;

/**
 * Represents a game map with cells of various types.
 * The map has specific dimensions, height, and width.
 */
public interface GameMap {
    /**
     * Returns the size of the map.
     * 
     * @return An Integer representing the size of the map.
     */
    int getSize();

    /**
     * Returns the type of cell in a certain position.
     * 
     * @param cell The cell position to get the type of.
     * @return The CellType representing the type of the selected cell.
     */
    CellType getCellType(Position cell);

    /**
     * Checks if the selected position is in the boundaries of the map.
     * 
     * @param cell The position to check.
     * @return True if the position is in the boundaries of the map, false
     *         otherwise.
     */
    boolean isInBoundaries(Position cell);
}
