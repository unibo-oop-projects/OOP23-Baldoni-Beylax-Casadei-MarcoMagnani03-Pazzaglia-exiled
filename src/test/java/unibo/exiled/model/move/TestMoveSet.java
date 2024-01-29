package unibo.exiled.model.move;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.utilities.ElementalType;

public class TestMoveSet {

    private final MagicMoveFactory moveFactory = new MagicMoveFactoryImpl();

    @Test
    void testGetMagicMoves() {
        MoveSet moveSet = new MoveSetImpl(4);
        assertTrue(moveSet.getMagicMoves().isEmpty());
    }

    @Test
    void testAddMagicMove() {
        MoveSet moveSet = new MoveSetImpl(4);
        MagicMove fireMove = moveFactory.createFireMagicMove("Fire", "Powerful fire move", 10.0);

        assertTrue(moveSet.addMagicMove(fireMove));
        assertEquals(1, moveSet.getMagicMoves().size());

        MagicMove boltMove = moveFactory.createBoltMagicMove("Bolt", "Electric shock", 8.0);
        MagicMove waterMove = moveFactory.createWaterMagicMove("Water", "Aqua attack", 12.0);
        MagicMove grassMove = moveFactory.createGrassMagicMove("Grass", "Nature's force", 15.0);

        assertTrue(moveSet.addMagicMove(boltMove));
        assertTrue(moveSet.addMagicMove(waterMove));
        assertTrue(moveSet.addMagicMove(grassMove));

        assertFalse(moveSet.addMagicMove(moveFactory.createFireMagicMove("ExtraMove", "Extra move", 5.0)));
    }

    @Test
    void testChangeMoves() {
        MoveSet moveSet = new MoveSetImpl(4);
        MagicMove fireMove = moveFactory.createFireMagicMove("Fire", "Powerful fire move", 10.0);
        MagicMove boltMove = moveFactory.createBoltMagicMove("Bolt", "Electric shock", 8.0);

        moveSet.addMagicMove(fireMove);
        assertTrue(moveSet.getMagicMoves().contains(fireMove));

        moveSet.changeMoves(fireMove, boltMove);
        assertFalse(moveSet.getMagicMoves().contains(fireMove));
        assertTrue(moveSet.getMagicMoves().contains(boltMove));

        MagicMove waterMove = moveFactory.createWaterMagicMove("Water", "Aqua attack", 12.0);
        assertThrows(IllegalArgumentException.class, () -> moveSet.changeMoves(waterMove, boltMove));
    }
}
