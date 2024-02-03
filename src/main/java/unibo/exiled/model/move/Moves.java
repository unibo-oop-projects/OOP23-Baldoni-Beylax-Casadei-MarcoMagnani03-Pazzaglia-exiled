package unibo.exiled.model.move;

import java.util.Set;
import java.util.Optional;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

import unibo.exiled.model.utilities.ElementalType;

/**
 * A container of every move usable in the game.
 */
public final class Moves {

    private static final MagicMoveFactoryImpl MOVE_FACTORY = new MagicMoveFactoryImpl();

    private static final Set<MagicMove> MAGIC_MOVES = Set.of(
            MOVE_FACTORY.createNormalMagicMove(MoveNames.COLPACCIO.getName(),
                    "Hits the enemy without much enthusiasm.", 5),
            MOVE_FACTORY.createNormalMagicMove(MoveNames.COLPONE.getName(),
                    "Hits the enemy with a bit more enthusiasm.", 8),
            MOVE_FACTORY.createFireMagicMove(MoveNames.FIREBALL.getName(),
                    "Throws a really sad fireball at the enemy.", 5),
            MOVE_FACTORY.createGrassMagicMove(MoveNames.LEAFBLADE.getName(),
                    "Summons a blunted blade made of leaves.", 5),
            MOVE_FACTORY.createBoltMagicMove(MoveNames.LIGHTBULB.getName(),
                    "Lit oneself body to blind the enemy, not very effective.", 5),
            MOVE_FACTORY.createWaterMagicMove(MoveNames.WATERPISTOL.getName(),
                    "Spits water from the mouth, pretty disgusting but nothing more.", 5)
    );

    private Moves() {
    }

    /**
     * Gets first move found with the selected name.
     *
     * @param name The name of the move to search.
     * @return An optional containing the move if a move with the selected name exists, Optional.empty() otherwise.
     */
    public static Optional<MagicMove> getMoveByName(final String name) {
        final String lowerName = name.toLowerCase();
        return MAGIC_MOVES.stream().filter(move -> move.name().toLowerCase().equals(lowerName)).findFirst();
    }

    /**
     * Gets every move in the game.
     *
     * @return An unmodifiable set of every move in the game.
     */
    public static Set<MagicMove> getAllMagicMoves() {
        return Collections.unmodifiableSet(MAGIC_MOVES);
    }

    /**
     * Gets every move of the selected type.
     *
     * @param type The type of the moves to get.
     * @return An unmodifiable set of moves of the selected type.
     */
    public static Set<MagicMove> getAllMagicMovesOfType(final ElementalType type) {
        return MAGIC_MOVES.stream()
                .filter(magicMove -> magicMove.type() == type)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Gets a random move of the selected type.
     *
     * @param type The type of the move to get.
     * @return An optional containing the random move of the selected
     * type if at least one is found, optional.Empty()
     * otherwise.
     */
    public static Optional<MagicMove> getRandomMagicMoveByType(final ElementalType type) {
        List<MagicMove> movesOfType = MAGIC_MOVES.stream()
                .filter(magicMove -> magicMove.type() == type)
                .toList();

        if (movesOfType.isEmpty()) {
            return Optional.empty();
        }
        final Random random = new Random();

        return Optional.of(movesOfType.get(random.nextInt(movesOfType.size())));
    }

    /**
     * Gets a random move.
     * @return A random move of any type.
     */
    public static MagicMove getTotallyRandomMove() {
        Random random = new Random();
        return MAGIC_MOVES.stream().toList().get(random.nextInt(MAGIC_MOVES.size()));
    }
}
