package unibo.exiled.model.map;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestMap {
    private GameMap map;
    @Test
    public void testInitialize(){
        assertThrows(IllegalArgumentException.class, ()->{
            map = new GameMapImpl(11);
        });
        map = new GameMapImpl(10);
        assertNotNull(map);
    }
}
