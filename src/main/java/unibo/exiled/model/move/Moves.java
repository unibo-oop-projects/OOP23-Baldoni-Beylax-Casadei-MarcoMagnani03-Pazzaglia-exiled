package unibo.exiled.model.move;

import java.util.*;

import unibo.exiled.model.utilities.ElementalType;

public class Moves{

    private final static Set<MagicMove> magicMoves = Set.of(
            new MagicMoveImpl(MoveNames.COLPACCIO.getName(), "Hits the enemy without much enthusiasm.",5,ElementalType.NORMAL),
            new MagicMoveImpl(MoveNames.COLPONE.getName(), "Hits the enemy with a bit more enthusiasm.",8, ElementalType.NORMAL),
            new MagicMoveImpl(MoveNames.FIREBALL.getName(), "Throws a really sad fireball at the enemy.",5,ElementalType.FIRE),
            new MagicMoveImpl(MoveNames.LEAFBLADE.getName(), "Summons a blunted blade made of leaves.",5,ElementalType.GRASS),
            new MagicMoveImpl(MoveNames.LIGHTBULB.getName(), "Lit oneself body to blind the enemy, not very effective.",5,ElementalType.BOLT),
            new MagicMoveImpl(MoveNames.WATERPISTOL.getName(), "Spits water from the mouth, pretty disgusting but nothing more.",5,ElementalType.WATER)

    );

    public static MagicMove getMoveByName(final String name){
        final String lowerName = name.toLowerCase();
        return magicMoves.stream().filter(move -> move.name().toLowerCase().equals(lowerName)).findFirst().get();
    }

    public static  Set<MagicMove> getAllMagicMoves() {
        return Collections.unmodifiableSet(magicMoves);
    }

    public static Optional<MagicMove> getRandomMagicMoveByType(ElementalType type) {
        Random random = new Random();
        List<MagicMove> movesOfType = magicMoves.stream()
                .filter(magicMove -> magicMove.type() == type)
                .toList();
        
        if(movesOfType.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(movesOfType.get(random.nextInt(movesOfType.size())));
    }
    
}
