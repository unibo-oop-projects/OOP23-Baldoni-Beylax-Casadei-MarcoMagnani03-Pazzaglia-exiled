package unibo.exiled.model.move;

import java.util.*;
import java.util.stream.Collectors;

import unibo.exiled.model.utilities.ElementalType;

public class Moves {
    
    private static final MagicMoveFactoryImpl moveFactory = new MagicMoveFactoryImpl();
    
    private static final Set<MagicMove> magicMoves = Set.of(
        moveFactory.createNormalMagicMove(MoveNames.COLPACCIO.getName(), "Hits the enemy without much enthusiasm.", 5),
        moveFactory.createNormalMagicMove(MoveNames.COLPONE.getName(), "Hits the enemy with a bit more enthusiasm.", 8),
        moveFactory.createFireMagicMove(MoveNames.FIREBALL.getName(), "Throws a really sad fireball at the enemy.", 5),
        moveFactory.createGrassMagicMove(MoveNames.LEAFBLADE.getName(), "Summons a blunted blade made of leaves.", 5),
        moveFactory.createBoltMagicMove(MoveNames.LIGHTBULB.getName(), "Lit oneself body to blind the enemy, not very effective.", 5),
        moveFactory.createWaterMagicMove(MoveNames.WATERPISTOL.getName(), "Spits water from the mouth, pretty disgusting but nothing more.", 5)
    );

    public static Optional<MagicMove> getMoveByName(final String name) {
        final String lowerName = name.toLowerCase();
        return magicMoves.stream().filter(move -> move.name().toLowerCase().equals(lowerName)).findFirst();
    }

    public static Set<MagicMove> getAllMagicMoves() {
        return Collections.unmodifiableSet(magicMoves);
    }

    public static Set<MagicMove> getAllMagicMovesOfType(ElementalType type) {
        return magicMoves.stream()
        .filter(magicMove -> magicMove.type() == type)
        .collect(Collectors.toUnmodifiableSet());
    }

    public static Optional<MagicMove> getRandomMagicMoveByType(ElementalType type) {
        List<MagicMove> movesOfType = magicMoves.stream()
        .filter(magicMove -> magicMove.type() == type)
        .toList();
        
        if (movesOfType.isEmpty()) {
            return Optional.empty();
        }
        Random random = new Random();

        return Optional.of(movesOfType.get(random.nextInt(movesOfType.size())));
    }

    public static MagicMove getTotallyRandomMove() {
        Random random = new Random();
        return magicMoves.stream().toList().get(random.nextInt(magicMoves.size()));
    }
}
