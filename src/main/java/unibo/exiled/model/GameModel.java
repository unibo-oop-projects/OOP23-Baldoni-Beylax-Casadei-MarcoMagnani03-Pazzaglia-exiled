package unibo.exiled.model;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.attributes.AttributeType;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.utilities.Position;

public class GameModel {
    private Player player;
    private GameMap map;

    public GameModel(final int mapSize){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        this.map = new GameMapImpl(mapSize);
        final Position startingPlayerPosition = new Position(
                Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_X")),
                Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_Y")));
        this.player = new PlayerImpl(
                startingPlayerPosition,
                Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE")),
                Integer.parseInt(Constants.getConstantOf("PLAYER_LEVEL_INCREASE")));
    }

    public boolean isGameOver(){
        return this.player.getAttributes().getAttributeOfType(AttributeType.HEALTH).getEvaluated() <= 0;
    }

    public Player getPlayer(){
        return this.player;
    }
}
