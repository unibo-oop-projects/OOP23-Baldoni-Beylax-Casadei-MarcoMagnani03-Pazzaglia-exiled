package unibo.exiled.model.move;

/**
 * Utility class to manage MoveSets.
 */
public final class MoveSets {
    private MoveSets() {
    }

    /**
     * Creates a copy of a MoveSet.
     *
     * @param moveSet The MoveSet to copy.
     * @return A MoveSet with the same moves of the parameter one.
     */
    public static MoveSet copyOf(final MoveSet moveSet) {
        final MoveSet newMoveSet = new MoveSetImpl(moveSet.getMagicMoves().size());
        for (final MagicMove move : moveSet.getMagicMoves()) {
            newMoveSet.addMagicMove(move);
        }
        return newMoveSet;
    }
}
