package unibo.exiled.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameModel {
    private static int defPosX;
    private static int defPosY;
    private static int size;
    private static GameModel model;

    @BeforeAll
    public static void initialize(){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        defPosX = Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_X"));
        defPosY = Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_Y"));
        size = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));
        model = new GameModel(size);
    }

    @Test
    public void testPlayerStartingPosition(){
        assertEquals(new Position(defPosX,defPosY),model.getPlayer().getPosition());
    }

    @Test
    public void testMapDimensions(){
        assertEquals(size,model.getMap().getWidth());
        assertEquals(size,model.getMap().getHeight());
    }
}
