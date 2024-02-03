package unibo.exiled.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the main game model.
 */
final class TestGameModelImpl {
    private static GameModelImpl model;

    /**
     * Initializes the configuration file before every test.
     */
    @BeforeAll
    static void initialize() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        model = new GameModelImpl();
    }

    /**
     * Test if the player starting position is in the center of the map.
     */
    @Test
    void testPlayerStartingPosition() {
        assertEquals(new Position(model.getMap().getWidth() / 2, model.getMap().getHeight() / 2),
                model.getPlayer().getPosition());
    }

    /**
     * Tests if the map dimensions are like the configurations.
     */
    @Test
    void testMapDimensions() {
        final int size = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));
        assertEquals(size, model.getMap().getWidth());
        assertEquals(size, model.getMap().getHeight());
    }
}
