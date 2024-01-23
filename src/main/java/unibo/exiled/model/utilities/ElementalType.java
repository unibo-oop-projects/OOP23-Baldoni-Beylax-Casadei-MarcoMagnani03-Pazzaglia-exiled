package unibo.exiled.model.utilities;

/**
 * The ElementalType enum represents the elemental types of magical moves in the game.
 * Each elemental type has specific strengths and weaknesses against other types.
 */
public enum ElementalType {
    FIRE,
    BOLT,
    WATER,
    NORMAL,
    GRASS;

    /**
     * Checks if the current elemental type is strong against the specified elemental type.
     *
     * @param secondMoveType The elemental type to check against.
     * @return true if the current type is strong against the specified type, false otherwise.
     * @throws IllegalArgumentException if the current elemental type is not valid.
     */
    public boolean isStrongAgainst(ElementalType secondMoveType){
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
                throw new IllegalArgumentException("Tipo elementale non valido: " + this);
        }
    }
}
