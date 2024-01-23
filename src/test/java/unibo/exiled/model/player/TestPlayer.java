package unibo.exiled.model.player;

import unibo.exiled.config.Constants;
import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.attributes.AttributeType;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unibo.exiled.model.character.player.Player;

public class TestPlayer {
    private double defaultHealth;
    private double defaultAttack;
    private double defaultDefense;
    private double defaultExperience;

    private final Player player = new GameModel(10).getPlayer();

    @BeforeEach
    public void testInitialize(){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        defaultHealth = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_HEALTH"));
        defaultAttack = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_ATTACK"));
        defaultDefense = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_DEFENSE"));
        defaultExperience = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE"));
    }

    @Test
    public void testDefaultField(){
        assertEquals(player.getAttributes().getAttributeOfType(AttributeType.HEALTH).getValue(), defaultHealth);
        assertEquals(player.getAttributes().getAttributeOfType(AttributeType.ATTACK).getValue(), defaultAttack);
        assertEquals(player.getAttributes().getAttributeOfType(AttributeType.DEFENSE).getValue(), defaultDefense);
        assertEquals(player.getExperience(), defaultExperience);
    }
}
