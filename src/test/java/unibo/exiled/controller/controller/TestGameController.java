package unibo.exiled.controller.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.controller.GameControllerImpl;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameController {
    private static int size;
    private static GameController controller;

    @BeforeAll
    static void testInitialization(){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        size = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));
        assertThrows(IllegalArgumentException.class , ()->{
           controller = new GameControllerImpl(size +1);
        });
        controller = new GameControllerImpl(size);
        assertNotNull(controller);
    }

    @Test
    void testMoveOutOfBoundaries(){
        //TODO Spiegazione del perchè
        for(int i = 0; i <= size + 1; i++){
            controller.movePlayer(Direction.SOUTH);
        }
        assertEquals(
                new Position(
                        controller.getPlayerController().getPlayer().getPosition().x(), size - 1),
                controller.getPlayerController().getPlayer().getPosition());
    }
}
