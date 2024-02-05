package unibo.exiled.model.character.player;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import unibo.exiled.config.Constants;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.utilities.ElementalType;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class TestPlayer {
    private static final int EXPERIENCE_CAP = 100;
    private static final int INITIAL_EXPERIENCE = 0;
    private static final int LEVEL_INCREASE = 10;
    private static final int MOVES_NUMBER = 3;
    private static final int MOVES_LEARNING_INTERVAL = 5;

    private static Player player;

    @BeforeAll
    static void setUp() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        player = new PlayerImpl(EXPERIENCE_CAP, INITIAL_EXPERIENCE, LEVEL_INCREASE, MOVES_NUMBER,
                MOVES_LEARNING_INTERVAL);
    }

    @Test
    void testGetLevel() {
        assertEquals(Integer.parseInt(Constants.getConstantOf("PLAYER_DEFAULT_LEVEL")), player.getLevel());
    }

    @Test
    void testGetExperience() {
        assertEquals(Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE")), player.getExperience());
    }

    @Test
    void testGetInventory() {
        Inventory inventory = player.getInventory();
        assertNotNull(inventory);
        assertEquals(4, inventory.getItems().size()); // It doesn't work correctly because of the
                                                      // "initializeInventory()" test method in PlayerImpl.
    }

    @Test
    void testSetAndGetPlayerClass() {
        player.setPlayerClass(ElementalType.FIRE);
        assertEquals(ElementalType.FIRE, player.getPlayerClass());
    }

    @Test
    void testAddExperience() {
        player.addExperience(150);
        assertEquals(50, player.getExperience());
        assertEquals(1, player.getLevel());
    }

    @Test
    void testLevelUp() {
        player.addExperience(230);
        assertEquals(10, player.getExperience());
        assertEquals(2, player.getLevel());
    }
}
