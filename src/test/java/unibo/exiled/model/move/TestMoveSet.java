package unibo.exiled.model.move;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MagicMoveImpl;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetImpl;
import unibo.exiled.model.utilities.ElementalType;

public class TestMoveSet {

    @Test
    void testGetMagicMoves() {
        MoveSet moveSet = new MoveSetImpl();
        assertTrue(moveSet.getMagicMoves().isEmpty());
    }

    @Test
    void testAddMagicMove() {
        MoveSet moveSet = new MoveSetImpl();
        MagicMove fireMove = new MagicMoveImpl("Fire", 10.0, ElementalType.FIRE);
        
        assertTrue(moveSet.addMagicMove(fireMove));
        assertEquals(1, moveSet.getMagicMoves().size());

        MagicMove boltMove = new MagicMoveImpl("Bolt", 8.0, ElementalType.BOLT);
        MagicMove waterMove = new MagicMoveImpl("Water", 12.0, ElementalType.WATER);
        MagicMove grassMove = new MagicMoveImpl("Grass", 15.0, ElementalType.GRASS);

        assertTrue(moveSet.addMagicMove(boltMove));
        assertTrue(moveSet.addMagicMove(waterMove));
        assertTrue(moveSet.addMagicMove(grassMove));

        assertFalse(moveSet.addMagicMove(new MagicMoveImpl("ExtraMove", 5.0, ElementalType.FIRE)));
    }

    @Test
    void testChangeMoves() {
        MoveSet moveSet = new MoveSetImpl();
        MagicMove fireMove = new MagicMoveImpl("Fire", 10.0, ElementalType.FIRE);
        MagicMove boltMove = new MagicMoveImpl("Bolt", 8.0, ElementalType.BOLT);

        moveSet.addMagicMove(fireMove);
        assertTrue(moveSet.getMagicMoves().contains(fireMove));

        moveSet.changeMoves(fireMove, boltMove);
        assertFalse(moveSet.getMagicMoves().contains(fireMove));
        assertTrue(moveSet.getMagicMoves().contains(boltMove));

        MagicMove waterMove = new MagicMoveImpl("Water", 12.0, ElementalType.WATER);
        assertThrows(IllegalArgumentException.class, () -> moveSet.changeMoves(waterMove, boltMove));
    }
}

