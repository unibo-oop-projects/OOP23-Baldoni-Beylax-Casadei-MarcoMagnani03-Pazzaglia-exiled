package unibo.exiled.controller.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.exiled.controller.GameControllerImpl;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameControllerImpl {
    private static final int DEFAULT_SIZE = 10;
    private GameControllerImpl gameControllerImpl;
    @BeforeEach
    void testInitialization(){
        assertThrows(IllegalArgumentException.class , ()->{
           gameControllerImpl = new GameControllerImpl(DEFAULT_SIZE + 1);
        });
        gameControllerImpl = new GameControllerImpl(DEFAULT_SIZE);
        assertNotNull(gameControllerImpl);
    }

    @Test
    void testValues(){
        assertEquals(gameControllerImpl.getMapHeight(), DEFAULT_SIZE);
        assertEquals(gameControllerImpl.getMapHeight(), gameControllerImpl.getMapWidth());
    }
}
