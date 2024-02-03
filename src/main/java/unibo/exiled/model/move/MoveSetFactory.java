package unibo.exiled.model.move;

/**
 * The MoveSetFactory interface defines methods to create default MoveSets
 * for different types of moves such as Normal, Fire, Grass, Bolt, and Water.
 */
public interface MoveSetFactory {

    /**
     * Creates a default MoveSet for Normal moves.
     *
     * @param movesNumber The number of moves to include in the MoveSet.
     * @return A MoveSet for Normal moves.
     */
    MoveSet defaultNormalMoveSet(int movesNumber);

    /**
     * Creates a default MoveSet for Fire moves.
     *
     * @param movesNumber The number of moves to include in the MoveSet.
     * @return A MoveSet for Fire moves.
     */
    MoveSet defaultFireMoveSet(int movesNumber);

    /**
     * Creates a default MoveSet for Grass moves.
     *
     * @param movesNumber The number of moves to include in the MoveSet.
     * @return A MoveSet for Grass moves.
     */
    MoveSet defaultGrassMoveSet(int movesNumber);

    /**
     * Creates a default MoveSet for Bolt moves.
     *
     * @param movesNumber The number of moves to include in the MoveSet.
     * @return A MoveSet for Bolt moves.
     */
    MoveSet defaultBoltMoveSet(int movesNumber);

    /**
     * Creates a default MoveSet for Water moves.
     *
     * @param movesNumber The number of moves to include in the MoveSet.
     * @return A MoveSet for Water moves.
     */
    MoveSet defaultWaterMoveSet(int movesNumber);
}
