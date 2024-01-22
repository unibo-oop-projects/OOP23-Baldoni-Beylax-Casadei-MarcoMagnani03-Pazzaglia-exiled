package unibo.exiled.model.player;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;

public class TestPlayer {
    private static final double DEFAULT_HEALTH = 100.0;
    private static final double DEFAULT_ATTACK = 0.0;
    private static final double DEFAULT_DEFENSE = 0.0;
    private static final int DEFAULT_LEVEL = 0;
    private static final double DEFAULT_EXPERIENCE = 0.0;

    private Player player;

    @BeforeEach
    public void testInitialize(){
        player = new PlayerImpl();

        assertNotNull(player);
    }

    @Test
    public void testDefaultField(){
        assertEquals(player.getAttributes().getHealth(), DEFAULT_HEALTH);
        assertEquals(player.getAttributes().getAttack(), DEFAULT_ATTACK);
        assertEquals(player.getAttributes().getDefense(), DEFAULT_DEFENSE);
        assertEquals(player.getLevel(), DEFAULT_LEVEL);
        assertEquals(player.getExperience(), DEFAULT_EXPERIENCE);

    }
}
