package unibo.exiled.model.move;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MoveSetImpl implements MoveSet{
    private final Set<MagicMove> magicMoves;
    private final int maxMoves;

    public MoveSetImpl(int movesNumber){
        magicMoves = new HashSet<>(movesNumber);
        maxMoves=movesNumber;
    }

    @Override
    public Set<MagicMove> getMagicMoves() {
        return Collections.unmodifiableSet(magicMoves);
    }

    @Override
    public void changeMoves(MagicMove oldMove, MagicMove newMove) {
        if(magicMoves.contains(oldMove)){
            magicMoves.remove(oldMove);
            magicMoves.add(newMove);
        }
        else{
            throw new IllegalArgumentException("Cannot change moves: oldMove not found in the moveset");
        }
    }

    @Override
    public boolean addMagicMove(MagicMove newMove) {
        if(magicMoves.size()==maxMoves){
            return false;
        }
        else{
            magicMoves.add(newMove);
            return true;
        }
    }
    
}
