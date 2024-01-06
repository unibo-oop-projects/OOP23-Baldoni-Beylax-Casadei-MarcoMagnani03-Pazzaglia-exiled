package unibo.exiled.model.Move;

import java.util.List;

public interface MoveSet {
    public List<MagicMove> getMagicMoves();
    public void changeMoves(MagicMove oldMove, MagicMove newMove);
}
