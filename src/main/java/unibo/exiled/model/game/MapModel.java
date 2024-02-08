package unibo.exiled.model.game;

import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Position;

/**
 * The model that manages the map.
 */
public interface MapModel {
    /**
     * Checks if a position is in the boundaries of the map.
     *
     * @param position The position to check.
     * @return True if the position is in boundaries, false otherwise.
     */
    boolean isInBoundaries(Position position);

    /**
     * Gets the size of the map.
     *
     * @return An integer representing the size of the map, which is a square.
     */
    int getSize();

    /**
     * Gets the type of cell given its position.
     *
     * @param position The position to get the type of.
     * @return A CellType representing the type of the inserted position.
     */
    CellType getCellType(Position position);

    /**
     * Gets the corner of the selected type.
     *
     * @param type The type of the cell.
     * @return The position representing the corner of a selected type.
     */
    Position getCornerOfType(CellType type);

    boolean isCornerOfMap(final Position position);
}
