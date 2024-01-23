package unibo.exiled.model;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.utilities.Position;

import java.util.ArrayList;

import java.util.List;

public class GameModel {
    private final Player player;
    private final GameMap map;
    private final Menu menu;
    private final List<Enemy> enemies;
    private final int enemyNumber;

    public GameModel(final int mapSize){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        this.menu = new MenuImpl();
        this.map = new GameMapImpl(mapSize);
        final Position startingPlayerPosition = new Position(
                Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_X")),
                Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_Y")));
        this.player = new PlayerImpl(
                startingPlayerPosition,
                Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE")),
                Integer.parseInt(Constants.getConstantOf("PLAYER_LEVEL_INCREASE")));
        this.enemies = new ArrayList<>();
        this.enemyNumber = Integer.parseInt(Constants.getConstantOf("NUM_ENEMIES"));
    }

    public Player getPlayer(){
        return this.player;
    }
    public Menu getMenu(){return  this.menu;}
    public GameMap getMap(){
        return this.map;
    }
}
