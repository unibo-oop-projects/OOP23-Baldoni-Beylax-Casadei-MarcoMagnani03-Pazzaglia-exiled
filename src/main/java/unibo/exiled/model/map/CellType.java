package unibo.exiled.model.map;

/**
 * Enum representing different types of cells on the game map.
 */
public enum CellType {
    /**
     * Represents a plains cell on the game map.
     */
    PLAINS(0),

    /**
     * Represents a swamp cell on the game map.
     */
    SWAMP(1),

    /**
     * Represents a volcano cell on the game map.
     */
    VOLCANO(2),

    /**
     * Represents a storm cell on the game map.
     */
    STORM(3),

    /**
     * Represents a forest cell on the game map.
     */
    FOREST(4);

    private final int index;

    /**
     * Constructs a CellType with the specified index.
     *
     * @param index The index associated with the cell type.
     */
    CellType(final int index) {
        this.index = index;
    }

    /**
     * Gets the index value associated with the cell type.
     *
     * @return The index value of the cell type.
     */
    public int getValue() {
        return this.index;
    }
}
