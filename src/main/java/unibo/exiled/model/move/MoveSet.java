package unibo.exiled.model.move;

import java.util.Set;

public interface MoveSet {
    Set<MagicMove> getMagicMoves();
    void changeMoves(MagicMove oldMove, MagicMove newMove);
}
