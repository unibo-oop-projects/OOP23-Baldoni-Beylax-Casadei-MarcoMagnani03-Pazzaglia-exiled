package unibo.exiled.model.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals(defaultMapSize, gameModel.getMapModel().getSize());
        assertNotNull(gameModel.getCharacterModel().getPlayerPosition());
        assertNotNull(gameModel.getCharacterModel()
                .getCharacterFromPosition(gameModel.getCharacterModel().getPlayerPosition()));
        assertNotNull(gameModel.getMapModel().getCellType(gameModel.getCharacterModel().getPlayerPosition()));
        assertNotNull(gameModel.getItemsModel().getItems());
        assertEquals(0, gameModel.getCharacterModel().getPlayerLevel());
        assertNotNull(gameModel.getCharacterModel().getPlayerClass());
        assertNotNull(gameModel.getCharacterModel().getPlayerMoveSet());
    }

    @Test
    void testMovePlayer() {
        final Position positionBeforeMoving = gameModel.getCharacterModel().getPlayerPosition();
        gameModel.getCharacterModel().movePlayer(Direction.NORTH);
        assertEquals(Positions.sum(positionBeforeMoving, Direction.NORTH.getPosition()),
                gameModel.getCharacterModel().getPlayerPosition());
    }
}
