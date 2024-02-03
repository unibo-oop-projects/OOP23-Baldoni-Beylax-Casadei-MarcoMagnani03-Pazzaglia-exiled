package unibo.exiled.model.utilities;

/**
 * The ElementalType enum represents the elemental types of player class and
 * also the magical moves in the game.
 * Each elemental type has specific strengths and weaknesses against other
 * types.
 */
public enum ElementalType {
    /**
     * Represents the Fire elemental type.
     */
    FIRE("Fire"),

    /**
     * Represents the Bolt elemental type.
     */
    BOLT("Bolt"),

    /**
     * Represents the Water elemental type.
     */
    WATER("Water"),

    /**
     * Represents the Grass elemental type.
     */
    GRASS("Grass"),

    /**
     * Represents the Normal elemental type.
     */
    NORMAL("Normal");

    private final String name;

    /**
     * Constructs an ElementalType with the specified name.
     *
     * @param name The name of the elemental type.
     */
    ElementalType(final String name) {
        this.name = name;
    }

    /**
     * Gets the name of the elemental type.
     *
     * @return The name of the elemental type.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the current elemental type is strong against the specified
     * elemental type.
     *
     * @param secondMoveType The elemental type to check against.
     * @return true if the current type is strong against the specified type, false
     *         otherwise.
     * @throws IllegalArgumentException if the current elemental type is not valid.
     */
    public boolean isStrongAgainst(final ElementalType secondMoveType) {
        switch (this) {
            case FIRE:
                return secondMoveType.equals(GRASS);
            case BOLT:
                return secondMoveType.equals(WATER);
            case GRASS:
                return secondMoveType.equals(BOLT);
            case WATER:
                return secondMoveType.equals(FIRE);
            case NORMAL:
                return false;
            default:
                throw new IllegalArgumentException("Invalid elemental type: " + this);
        }
    }
}
