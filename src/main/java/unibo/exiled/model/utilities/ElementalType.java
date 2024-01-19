package unibo.exiled.model.utilities;

public enum ElementalType {
    FIRE,
    BOLT,
    WATER,
    GRASS;

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
            default:
                throw new IllegalArgumentException("Tipo elementale non valido: " + this);
        }
    }
}
