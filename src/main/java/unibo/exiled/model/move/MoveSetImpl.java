package unibo.exiled.model.move;

import java.util.HashSet;
import java.util.Set;

public class MoveSetImpl implements MoveSet{
    private final static int MOVESNUMBER=4;
    Set<MagicMove> magicMoves = new HashSet<>();

    @Override
    public Set<MagicMove> getMagicMoves() {
        return this.magicMoves;
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
        if(magicMoves.size()==MOVESNUMBER){
            return false;
        }
        else{
            magicMoves.add(newMove);
            return true;
        }
    }
    
}
