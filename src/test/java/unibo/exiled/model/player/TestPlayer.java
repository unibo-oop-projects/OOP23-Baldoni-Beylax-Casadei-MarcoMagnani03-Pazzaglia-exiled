package unibo.exiled.model.player;

import unibo.exiled.model.character.attributes.AttributeType;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    private static final double DEFAULT_HEALTH = 100.0;
    private static final double DEFAULT_ATTACK = 0.0;
    private static final double DEFAULT_DEFENSE = 0.0;
    private static final int DEFAULT_LEVEL = 0;
    private static final double DEFAULT_EXPERIENCE = 0.0;

    private Player player;

    @BeforeEach
    public void testInitialize(){
        
    }

    @Test
    public void testDefaultField(){
        assertEquals(player.getAttributes().getAttributeOfType(AttributeType.HEALTH).getValue(), DEFAULT_HEALTH);
        assertEquals(player.getAttributes().getAttributeOfType(AttributeType.ATTACK).getValue(), DEFAULT_ATTACK);
        assertEquals(player.getAttributes().getAttributeOfType(AttributeType.DEFENSE).getValue(), DEFAULT_DEFENSE);
        assertEquals(player.getLevel(), DEFAULT_LEVEL);
        assertEquals(player.getExperience(), DEFAULT_EXPERIENCE);

    }
}
