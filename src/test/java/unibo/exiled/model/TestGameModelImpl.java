package unibo.exiled.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unibo.exiled.config.Constants;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.utilities.Position;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameModelImpl {
    private static GameModelImpl model;

    @BeforeAll
    public static void initialize(){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        model = new GameModelImpl();
    }

    @Test
    public void testPlayerStartingPosition(){
        assertEquals(new Position(model.getMap().getWidth() / 2, model.getMap().getHeight() / 2),
                model.getPlayer().getPosition());
    }

    @Test
    public void testMapDimensions(){
        final int size = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));
        assertEquals(size,model.getMap().getWidth());
        assertEquals(size,model.getMap().getHeight());
    }

    @Test
    public void testCharacterScatteringWithVisualization(){
        for(int i = 0; i < model.getMap().getHeight(); i++){
            System.out.print("|");
            for(int j = 0; j < model.getMap().getWidth(); j++){
                final Position currPos = new Position(j,i);
                if(model.getEnemies().containsKey(currPos)){
                    System.out.print(model.getEnemies().get(currPos).getName().substring(0,1));
                }
                else if(model.getPlayer().getPosition().equals(currPos)){
                    System.out.print("P");
                }
                else{
                    System.out.print("-");
                }
            }
            System.out.println("|");
        }
    }
}
