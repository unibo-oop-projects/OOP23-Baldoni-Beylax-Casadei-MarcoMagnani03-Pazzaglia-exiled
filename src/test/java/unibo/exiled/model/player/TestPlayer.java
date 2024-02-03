package unibo.exiled.model.player;

import org.junit.jupiter.api.BeforeEach;
import unibo.exiled.config.Constants;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.game.GameModelImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.CombinedAttributeImpl;
import unibo.exiled.model.character.attributes.MultiplierAttributeImpl;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;

/**
 * Tests the player and its movements.
 */
class TestPlayer {
    private double defaultHealth;
    private double defaultAttack;
    private double defaultDefense;
    private double defaultExperience;

    /**
     * Initializes before every test.
     */
    @BeforeEach
    @Test
    void testInitialize() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        defaultHealth = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_HEALTH"));
        defaultAttack = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_ATTACK"));
        defaultDefense = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_DEFENSE"));
        defaultExperience = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE"));
    }

    /**
     * Tests the default attributes of the player.
     */
    @Test
    void testDefaultField() {
        assertEquals(((CombinedAttributeImpl) player.getAttributes().get(AttributeIdentifier.HEALTH)).getEvaluated(),
                defaultHealth);
        assertEquals(((MultiplierAttributeImpl) player.getAttributes().get(AttributeIdentifier.ATTACK)).modifier(),
                defaultAttack);
        assertEquals(((MultiplierAttributeImpl) player.getAttributes().get(AttributeIdentifier.DEFENSE)).modifier(),
                defaultDefense);
        assertEquals(player.getExperience(), defaultExperience);
    }

    /**
     * Tests the movement of the player and the map boundaries.
     */
    @Test
    void testMove() {
        final Position currentPosition = player.getPosition();

        player.move(Positions.sum(currentPosition, Direction.NORTH.getPosition()));
        Position expectedPosition = new Position(currentPosition.x(), currentPosition.y() - 1);
        assertEquals(player.getPosition(), expectedPosition);

        player.move(Positions.sum(currentPosition, Direction.SOUTH.getPosition()));
        expectedPosition = new Position(currentPosition.x(), currentPosition.y() + 1);
        assertEquals(player.getPosition(), expectedPosition);

        player.move(Positions.sum(currentPosition, Direction.EAST.getPosition()));
        expectedPosition = new Position(currentPosition.x() + 1, currentPosition.y());
        assertEquals(player.getPosition(), expectedPosition);

        player.move(Positions.sum(currentPosition, Direction.WEST.getPosition()));
        expectedPosition = new Position(currentPosition.x() - 1, currentPosition.y());
        assertEquals(player.getPosition(), expectedPosition);
    }

}
