package unibo.exiled.controller.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.exiled.controller.Controller;
import static org.junit.jupiter.api.Assertions.*;

public class TestController {
    private static final int DEFAULT_SIZE = 10;
    private Controller controller;
    @BeforeEach
    void testInitialization(){
        assertThrows(IllegalArgumentException.class , ()->{
           controller = new Controller(DEFAULT_SIZE + 1);
        });
        controller = new Controller(DEFAULT_SIZE);
        assertNotNull(controller);
    }

    @Test
    void testValues(){
        assertEquals(controller.getMapHeight(), DEFAULT_SIZE);
        assertEquals(controller.getMapHeight(),controller.getMapWidth());
    }
}
