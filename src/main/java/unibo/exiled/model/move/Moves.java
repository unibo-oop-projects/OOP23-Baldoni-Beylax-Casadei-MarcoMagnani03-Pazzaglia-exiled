package unibo.exiled.model.move;

import java.util.Set;
import java.util.Optional;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

import unibo.exiled.model.move.factory.MagicMoveFactory;
import unibo.exiled.model.move.factory.MagicMoveFactoryImpl;
import unibo.exiled.utilities.ElementalType;

/**
 * A container of every move usable in the game.
 */
public final class Moves {

    private static final MagicMoveFactory MOVE_FACTORY = new MagicMoveFactoryImpl();
    private static final Random RANDOM = new Random();

    private static final Set<MagicMove> MAGIC_MOVES = Set.of(
            MOVE_FACTORY.createNormalMagicMove(MoveNames.COLPACCIO.getName(),
                    MoveNames.COLPACCIO.getDescription(),
                    5, MoveNames.COLPACCIO.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createNormalMagicMove(MoveNames.COLPONE.getName(),
                    MoveNames.COLPONE.getDescription(),
                    8, MoveNames.COLPONE.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createFireMagicMove(MoveNames.FIREBALL.getName(),
                    MoveNames.FIREBALL.getDescription(),
                    5, MoveNames.FIREBALL.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createGrassMagicMove(MoveNames.LEAFBLADE.getName(),
                    MoveNames.LEAFBLADE.getDescription(),
                    5, MoveNames.LEAFBLADE.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createBoltMagicMove(MoveNames.LIGHTBULB.getName(),
                    MoveNames.LIGHTBULB.getDescription(),
                    5, MoveNames.LIGHTBULB.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createWaterMagicMove(MoveNames.WATERPISTOL.getName(),
                    MoveNames.WATERPISTOL.getDescription(),
                    5, MoveNames.WATERPISTOL.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createFireMagicMove(MoveNames.FLAMEWHIRL.getName(),
                    MoveNames.FLAMEWHIRL.getDescription(),
                    10, MoveNames.FLAMEWHIRL.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createNormalMagicMove(MoveNames.QUICKSLASH.getName(),
                    MoveNames.QUICKSLASH.getDescription(),
                    10, MoveNames.QUICKSLASH.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createGrassMagicMove(MoveNames.PETALSTORM.getName(),
                    MoveNames.PETALSTORM.getDescription(),
                    10, MoveNames.PETALSTORM.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createBoltMagicMove(MoveNames.THUNDERSTRIKE.getName(),
                    MoveNames.THUNDERSTRIKE.getDescription(),
                    10, MoveNames.THUNDERSTRIKE.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createWaterMagicMove(MoveNames.AQUAORB.getName(),
                    MoveNames.AQUAORB.getDescription(),
                    10, MoveNames.AQUAORB.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createFireMagicMove(MoveNames.INFERNO.getName(),
                    MoveNames.INFERNO.getDescription(),
                    50, MoveNames.INFERNO.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createBoltMagicMove(MoveNames.THUNDERSTORM.getName(),
                    MoveNames.THUNDERSTORM.getDescription(),
                    50, MoveNames.THUNDERSTORM.getMinimumLevelToLearnTheMove()),
            MOVE_FACTORY.createBoltMagicMove(MoveNames.LOCOMOVOLT.getName(),
                    MoveNames.LOCOMOVOLT.getDescription(),
                    50, MoveNames.LOCOMOVOLT.getMinimumLevelToLearnTheMove())
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
        return MAGIC_MOVES.stream().filter(move -> move.name().equalsIgnoreCase(name)).findFirst();
    }

    /**
     * Gets every move in the game.
     *
     * @return An unmodifiable set of every move in the game.
     */
    public static Set<MagicMove> getAllMagicMoves() {
        return MAGIC_MOVES;
    }

    /**
     * Gets every move of the selected type.
     *
     * @param type  The type of the moves to get.
     * @param level The necessary level to learn the move.
     * @return An unmodifiable set of moves of the selected type.
     */
    public static Set<MagicMove> getAllMagicMovesOfType(final ElementalType type, final int level) {
        return MAGIC_MOVES.stream()
                .filter(magicMove -> magicMove.type() == type && magicMove.minimumLevelToLearn() <= level)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Gets a random move of the selected type.
     *
     * @param type  The type of the move to get.
     * @param level The necessary level to learn the move.
     * @return An optional containing the random move of the selected
     * type if at least one is found, optional.Empty()
     * otherwise.
     */
    public static Optional<MagicMove> getRandomMagicMoveByType(final ElementalType type, final int level) {
        final List<MagicMove> movesOfType = MAGIC_MOVES.stream()
                .filter(magicMove -> magicMove.type() == type && magicMove.minimumLevelToLearn() <= level)
                .toList();

        if (movesOfType.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(movesOfType.get(RANDOM.nextInt(movesOfType.size())));
    }

    /**
     * Gets a random move.
     *
     * @param level The necessary level to learn the move.
     * @return A random move of any type.
     */
    public static MagicMove getTotallyRandomMove(final int level) {
        return MAGIC_MOVES.stream().filter(magicMove -> magicMove.minimumLevelToLearn() <= level)
                .toList().get(RANDOM.nextInt(MAGIC_MOVES.size()));
    }
}
