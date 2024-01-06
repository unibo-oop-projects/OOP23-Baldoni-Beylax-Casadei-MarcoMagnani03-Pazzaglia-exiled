package unibo.exiled.model;

public enum ElementalType {
    FUOCO,
    FULMINE,
    ACQUA,
    ERBA;

    public boolean isStrongAgainst(ElementalType secondMoveType){
        switch (this) {
            case FUOCO:
                return secondMoveType.equals(ERBA);
            case FULMINE:
                return secondMoveType.equals(ACQUA);
            case ERBA:
                return secondMoveType.equals(FULMINE);
            case ACQUA:
                return secondMoveType.equals(FUOCO);
            default:
                throw new IllegalArgumentException("Tipo elementale non valido: " + this);
        }
    }
}
