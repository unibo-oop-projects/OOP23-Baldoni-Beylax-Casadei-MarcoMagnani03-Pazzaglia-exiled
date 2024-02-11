package unibo.exiled.model.move;

import org.junit.jupiter.api.Test;
import unibo.exiled.model.move.factory.MoveSetFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


final class TestMoveSetFactory {

    @Test
    void testDefaultNormalMoveSet() {
        final MoveSetFactoryImpl factory = new MoveSetFactoryImpl();
        final MoveSet moveSet = factory.defaultNormalMoveSet();
        assertNotNull(moveSet);
        // Test if Moves.getMoveByName() and Moves.getMoveByName().get() are called
        assertEquals(1, moveSet.getMagicMoves().size());
    }

    @Test
    void testDefaultFireMoveSet() {
        final MoveSetFactoryImpl factory = new MoveSetFactoryImpl();
        final MoveSet moveSet = factory.defaultFireMoveSet();
        assertNotNull(moveSet);
        // Test if Moves.getMoveByName() and Moves.getMoveByName().get() are called
        assertEquals(1, moveSet.getMagicMoves().size());
    }

    @Test
    void testDefaultGrassMoveSet() {
        final MoveSetFactoryImpl factory = new MoveSetFactoryImpl();
        final MoveSet moveSet = factory.defaultGrassMoveSet();
        assertNotNull(moveSet);
        // Test if Moves.getMoveByName() and Moves.getMoveByName().get() are called
        assertEquals(1, moveSet.getMagicMoves().size());
    }

    @Test
    void testDefaultBoltMoveSet() {
        final MoveSetFactoryImpl factory = new MoveSetFactoryImpl();
        final MoveSet moveSet = factory.defaultBoltMoveSet();
        assertNotNull(moveSet);
        // Test if Moves.getMoveByName() and Moves.getMoveByName().get() are called
        assertEquals(1, moveSet.getMagicMoves().size());
    }

    @Test
    void testDefaultWaterMoveSet() {
        final MoveSetFactoryImpl factory = new MoveSetFactoryImpl();
        final MoveSet moveSet = factory.defaultWaterMoveSet();
        assertNotNull(moveSet);
        // Test if Moves.getMoveByName() and Moves.getMoveByName().get() are called
        assertEquals(1, moveSet.getMagicMoves().size());
    }
}
