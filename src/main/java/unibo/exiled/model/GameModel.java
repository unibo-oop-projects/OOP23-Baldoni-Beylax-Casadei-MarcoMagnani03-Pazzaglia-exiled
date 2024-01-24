package unibo.exiled.model;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.menu.MenuItem;
import unibo.exiled.model.utilities.Position;

import java.util.ArrayList;

import java.util.List;

public class GameModel {
    private final Player player;
    private final GameMap map;
    private final Menu startMenu;
    private final Menu inGameMenu;
    private final List<Enemy> enemies;
    private final int enemyNumber;
    private final int moveNumber;
    private final double defaultExperience;
    private final int playerLevelIncrease;
    private final int playerStartingPositionX;
    private final int playerStartingPositionY;

    public GameModel(final int mapSize){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);

        this.moveNumber = Integer.parseInt(Constants.getConstantOf("NUM_MOVES"));
        this.defaultExperience = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE"));
        this.playerLevelIncrease=Integer.parseInt(Constants.getConstantOf("PLAYER_LEVEL_INCREASE"));
        this.playerStartingPositionX = Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_X"));
        this.playerStartingPositionY = Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_Y"));
        this.enemyNumber = Integer.parseInt(Constants.getConstantOf("NUM_ENEMIES"));
        
        this.startMenu = new MenuImpl();
        this.inGameMenu = new MenuImpl();
        this.startMenu.addMenuItem(new MenuItem("NEW GAME", Command.NEW_GAME));
        this.startMenu.addMenuItem(new MenuItem("QUIT", Command.QUIT));
        this.inGameMenu.addMenuItem(new MenuItem("CLOSE MENU", Command.CLOSE_MENU));
        this.inGameMenu.addMenuItem(new MenuItem("QUIT", Command.QUIT));

        this.map = new GameMapImpl(mapSize);


        final Position startingPlayerPosition = new Position(this.playerStartingPositionX,playerStartingPositionY);
        this.player = new PlayerImpl(startingPlayerPosition,defaultExperience,playerLevelIncrease,moveNumber);
        this.enemies = new ArrayList<>();
    }

    public Player getPlayer(){
        return this.player;
    }

    public Menu getStartMenu() {
        return this.startMenu;
    }
    
    public Menu getInGameMenu() {
        return this.inGameMenu;
    }

    public GameMap getMap(){
        return this.map;
    }
}
