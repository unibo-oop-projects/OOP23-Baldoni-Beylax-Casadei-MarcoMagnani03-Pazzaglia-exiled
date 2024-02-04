package unibo.exiled.model.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class TestGameModel {

    private static int defaultMapSize;
    private static GameModel gameModel;

    @BeforeAll
    static void initializeConstants() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        defaultMapSize = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));
        gameModel = new GameModelImpl();
    }

    @Test
    void testConstructorAndInitialization() {
        assertNotNull(gameModel);
        assertEquals(defaultMapSize, gameModel.getMapSize());
        assertNotNull(gameModel.getPlayerPosition());
        assertNotNull(gameModel.getCharacterFromPosition(gameModel.getPlayerPosition()));
        assertNotNull(gameModel.getCellTypeOf(gameModel.getPlayerPosition()));
        assertNotNull(gameModel.getItems());
        assertEquals(0, gameModel.getPlayerLevel());
        assertNotNull(gameModel.getPlayerClass());
        assertNotNull(gameModel.getPlayerMoveSet());
    }

    @Test
    void testMovePlayer() {
        final Position positionBeforeMoving = gameModel.getPlayerPosition();
        gameModel.movePlayer(Direction.NORTH);
        assertEquals(Positions.sum(positionBeforeMoving, Direction.NORTH.getPosition()), gameModel.getPlayerPosition());
    }
}
