package unibo.exiled.model.move;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * The implementation of the MoveSet interface.
 */
public final class MoveSetImpl implements MoveSet {
    /**
     * A set of moves that compose the move set.
     */
    private final Set<MagicMove> magicMoves;
    private final int maxMoves;

    /**
     * The constructor of the move set.
     * @param movesNumber The number of the moves composing the move set.
     */
    public MoveSetImpl(final int movesNumber) {
        magicMoves = new HashSet<>(movesNumber);
        maxMoves = movesNumber;
    }

    @Override
    public Set<MagicMove> getMagicMoves() {
        return Collections.unmodifiableSet(magicMoves);
    }

    @Override
    public boolean changeMoves(final MagicMove oldMove, final MagicMove newMove) {
        if (magicMoves.contains(oldMove)) {
            magicMoves.remove(oldMove);
            magicMoves.add(newMove);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addMagicMove(final MagicMove newMove) {
        if (magicMoves.size() == maxMoves) {
            return false;
        } else {
            magicMoves.add(newMove);
            return true;
        }
    }

}
