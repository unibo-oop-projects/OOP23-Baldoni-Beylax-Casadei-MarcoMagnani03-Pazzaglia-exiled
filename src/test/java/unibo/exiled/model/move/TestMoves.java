package unibo.exiled.model.move;

import org.junit.jupiter.api.Test;
import unibo.exiled.utilities.ElementalType;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class TestMoves {

    @Test
    void testGetMoveByName() {
        final Optional<MagicMove> moveOptional = Moves.getMoveByName("Fireball");
        assertTrue(moveOptional.isPresent());
        assertEquals("Fireball", moveOptional.get().name());
    }

    @Test
    void testGetAllMagicMoves() {
        final Set<MagicMove> magicMoves = Moves.getAllMagicMoves();
        assertNotNull(magicMoves);
        assertFalse(magicMoves.isEmpty());
    }

    @Test
    void testGetAllMagicMovesOfType() {
        final Set<MagicMove> fireMoves = Moves.getAllMagicMovesOfType(ElementalType.FIRE, 100);
        assertNotNull(fireMoves);
        assertFalse(fireMoves.isEmpty());
        assertTrue(fireMoves.stream().allMatch(move -> move.type() == ElementalType.FIRE));
    }

    @Test
    void testGetRandomMagicMoveByType() {
        final Optional<MagicMove> fireMoveOptional = Moves.getRandomMagicMoveByType(ElementalType.FIRE, 100);
        assertTrue(fireMoveOptional.isPresent());
        assertEquals(ElementalType.FIRE, fireMoveOptional.get().type());
        final Optional<MagicMove> nonExistentMove = Moves.getRandomMagicMoveByType(null, 100);
        assertEquals(Optional.empty(), nonExistentMove);
    }

    @Test
    void testGetTotallyRandomMove() {
        final MagicMove randomMove = Moves.getTotallyRandomMove(100);
        assertNotNull(randomMove);
        assertTrue(Moves.getAllMagicMoves().contains(randomMove));
    }
}
