package unibo.exiled.model.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import unibo.exiled.config.Constants;

/**
 * Tests the fallback manager.
 */
class TestFallBackManager {
    private static final int DEFAULT_MAP_SIZE = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));

    @Test
    void testNull() {
        Constants.loadConfiguration(null);
        assertEquals(1.0, Double.parseDouble(Constants.getConstantOf("DEFAULT_MODIFIER")));
    }

    @Test
    void testNonexistentFile() {
        Constants.loadConfiguration("trash.whatever");
        assertEquals(DEFAULT_MAP_SIZE, Integer.parseInt(Constants.getConstantOf("MAP_SIZE")));
    }

    @Test
    void testNonexistentConfigurationModifier() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        assertEquals("1.0", Constants.getConstantOf("WHATEVER_MODIFIER"));
    }

    @Test
    void testLoadingFailure() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        assertThrows(IllegalArgumentException.class, () -> Constants.getConstantOf("WHATEVER"));
    }
}
