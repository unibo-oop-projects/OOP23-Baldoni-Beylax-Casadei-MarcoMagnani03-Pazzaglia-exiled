package unibo.exiled.model.map;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMap {
    private GameMap map;
    private static final int DEFAULT_SIZE = 10;
    @BeforeEach
    public void testInitialize(){
        assertThrows(IllegalArgumentException.class, ()->{
            map = new GameMapImpl(DEFAULT_SIZE + 1);
        });
        map = new GameMapImpl(DEFAULT_SIZE);
        assertNotNull(map);
    }

    @Test
    public void testGetSize(){
        assertEquals(map.getHeight(),DEFAULT_SIZE);
        assertEquals(map.getWidth(),DEFAULT_SIZE);
    }

    @Test
    public void testAreas(){

    }
}
