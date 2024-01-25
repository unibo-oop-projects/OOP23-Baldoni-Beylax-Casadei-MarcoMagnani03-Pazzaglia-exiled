package unibo.exiled.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameController {
    private static GameController controller;

    @BeforeAll
    static void testInitialization(){
        controller = new GameControllerImpl();
        assertNotNull(controller);
    }

    /**
     * This test checks that the player doesn't change its position if it's going to go out of boundaries.
     */
    @Test
    void testMoveOutOfBoundaries(){
        for(int i = 0; i <= controller.getMapController().map().getHeight() + 1; i++){
            controller.movePlayer(Direction.SOUTH);
        }
        assertEquals(
                new Position(
                        controller.getPlayerController().player().getPosition().x(), controller.getMapController().map().getHeight() - 1),
                controller.getPlayerController().player().getPosition());
    }
}
