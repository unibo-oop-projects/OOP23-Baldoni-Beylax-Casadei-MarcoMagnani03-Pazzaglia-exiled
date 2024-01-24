package unibo.exiled.model.move;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import unibo.exiled.model.utilities.ElementalType;

public class MagicMoveContainerImpl implements MagicMoveContainer{

    private final List<MagicMove> magicMoves;

    public MagicMoveContainerImpl(){
        this.magicMoves = new LinkedList<>();
    }

    @Override
    public void addMagicMove(MagicMove magicMove) {
       magicMoves.add(magicMove);
    }

    @Override
    public Optional<MagicMove> getMagicMoveByName(String name) {
        for (MagicMove magicMove : magicMoves) {
            if (magicMove.getName().equals(name)) {
                return Optional.of(magicMove);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<MagicMove> getAllMagicMoves() {
        return Collections.unmodifiableList(magicMoves);
    }

    @Override
    public Optional<MagicMove> getRandomMagicMoveByType(ElementalType type) {
        Random random = new Random();
        List<MagicMove> movesOfType = magicMoves.stream()
                .filter(magicMove -> magicMove.getType() == type)
                .toList();
        
        if(movesOfType.isEmpty()){
            return Optional.empty();
        }
        
        return Optional.of(movesOfType.get(random.nextInt(movesOfType.size())));
    }
    
}
