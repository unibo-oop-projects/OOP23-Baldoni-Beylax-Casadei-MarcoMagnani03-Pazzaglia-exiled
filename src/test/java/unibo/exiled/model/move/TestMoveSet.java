package unibo.exiled.model.move;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import unibo.exiled.model.move.factory.MagicMoveFactory;
import unibo.exiled.model.move.factory.MagicMoveFactoryImpl;

/**
 * Tests the MoveSet creation and modification.
 */
final class TestMoveSet {

    private static final double WATER_DEFAULT = 12.0;
    private static final double BOLT_DEFAULT = 8.0;
    private static final double GRASS_DEFAULT = 15.0;
    private static final double FIRE_DEFAULT = 10.0;

    private final MagicMoveFactory moveFactory = new MagicMoveFactoryImpl();

    @Test
    void testGetMagicMoves() {
        final MoveSet moveSet = new MoveSetImpl();
        assertTrue(moveSet.getMagicMoves().isEmpty());
    }

    @Test
    void testAddMagicMove() {
        final MoveSet moveSet = new MoveSetImpl();
        final MagicMove fireMove = moveFactory.createFireMagicMove("Fire", "Powerful fire move", FIRE_DEFAULT, 100);

        assertEquals(0, moveSet.getMagicMoves().size());

        final MagicMove boltMove = moveFactory.createBoltMagicMove("Bolt", "Electric shock", BOLT_DEFAULT, 100);
        final MagicMove waterMove = moveFactory.createWaterMagicMove("Water", "Aqua attack", WATER_DEFAULT, 100);
        final MagicMove grassMove = moveFactory.createGrassMagicMove("Grass", "Nature's force", GRASS_DEFAULT, 100);

        moveSet.addMagicMove(boltMove);
        moveSet.addMagicMove(waterMove);

        assertEquals(2, moveSet.getMagicMoves().size());
        moveSet.addMagicMove(grassMove);
        moveSet.addMagicMove(fireMove);
        assertEquals(4, moveSet.getMagicMoves().size());
    }

    @Test
    void testChangeMoves() {
        final MoveSet moveSet = new MoveSetImpl();
        final MagicMove fireMove = moveFactory.createFireMagicMove("Fire", "Powerful fire move", FIRE_DEFAULT, 100);
        final MagicMove boltMove = moveFactory.createBoltMagicMove("Bolt", "Electric shock", BOLT_DEFAULT, 100);

        moveSet.addMagicMove(fireMove);
        assertTrue(moveSet.getMagicMoves().contains(fireMove));

        moveSet.changeMoves(fireMove, boltMove);
        assertFalse(moveSet.getMagicMoves().contains(fireMove));
        assertTrue(moveSet.getMagicMoves().contains(boltMove));

        final MagicMove waterMove = moveFactory.createWaterMagicMove("Water", "Aqua attack", WATER_DEFAULT, 100);
        assertFalse(moveSet.changeMoves(waterMove, boltMove));
    }
}
