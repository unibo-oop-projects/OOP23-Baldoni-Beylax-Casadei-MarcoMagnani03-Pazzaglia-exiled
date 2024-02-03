package unibo.exiled.model.move;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the MoveSet creation.
 */
class TestMoveSet {

    private static final double WATER_DEFAULT = 12.0;
    private static final double BOLT_DEFAULT = 8.0;
    private static final double GRASS_DEFAULT = 15.0;
    private static final double FIRE_DEFAULT = 10.0;
    private static final double EXTRA_POWER = 5.0;
    private static final int NUM_MOVES = 4;
    private final MagicMoveFactory moveFactory = new MagicMoveFactoryImpl();

    @Test
    void testGetMagicMoves() {
        final MoveSet moveSet = new MoveSetImpl(NUM_MOVES);
        assertTrue(moveSet.getMagicMoves().isEmpty());
    }

    @Test
    void testAddMagicMove() {
        final MoveSet moveSet = new MoveSetImpl(NUM_MOVES);
        final MagicMove fireMove = moveFactory.createFireMagicMove("Fire", "Powerful fire move", FIRE_DEFAULT);

        assertTrue(moveSet.addMagicMove(fireMove));
        assertEquals(1, moveSet.getMagicMoves().size());

        final MagicMove boltMove = moveFactory.createBoltMagicMove("Bolt", "Electric shock", BOLT_DEFAULT);
        final MagicMove waterMove = moveFactory.createWaterMagicMove("Water", "Aqua attack", WATER_DEFAULT);
        final MagicMove grassMove = moveFactory.createGrassMagicMove("Grass",
                "Nature's force", GRASS_DEFAULT);

        assertTrue(moveSet.addMagicMove(boltMove));
        assertTrue(moveSet.addMagicMove(waterMove));
        assertTrue(moveSet.addMagicMove(grassMove));

        assertFalse(moveSet.addMagicMove(moveFactory.createFireMagicMove("ExtraMove",
                "Extra move", EXTRA_POWER)));
    }

    @Test
    void testChangeMoves() {
        final MoveSet moveSet = new MoveSetImpl(NUM_MOVES);
        final MagicMove fireMove = moveFactory.createFireMagicMove("Fire", "Powerful fire move", FIRE_DEFAULT);
        final MagicMove boltMove = moveFactory.createBoltMagicMove("Bolt", "Electric shock", BOLT_DEFAULT);

        moveSet.addMagicMove(fireMove);
        assertTrue(moveSet.getMagicMoves().contains(fireMove));

        moveSet.changeMoves(fireMove, boltMove);
        assertFalse(moveSet.getMagicMoves().contains(fireMove));
        assertTrue(moveSet.getMagicMoves().contains(boltMove));

        final MagicMove waterMove = moveFactory.createWaterMagicMove("Water", "Aqua attack", WATER_DEFAULT);
        assertFalse(moveSet.changeMoves(waterMove, boltMove));
    }
}
