package unibo.exiled.model;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyFactory;
import unibo.exiled.model.character.enemy.EnemyFactoryImpl;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.Menu;
import unibo.exiled.model.menu.MenuImpl;
import unibo.exiled.model.menu.MenuItem;
import unibo.exiled.model.utilities.Position;

import java.util.*;

public class GameModelImpl implements GameModel {
    private Player player;
    private GameMap map;
    private Menu startMenu;
    private Menu inGameMenu;
    private Map<Position,Enemy> enemiesScattering;

    public GameModelImpl(){

        //Constants loading
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        final int moveNumber = Integer.parseInt(Constants.getConstantOf("NUM_MOVES"));
        final double defaultExperience = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE"));
        final int playerLevelIncrease=Integer.parseInt(Constants.getConstantOf("PLAYER_LEVEL_INCREASE"));
        final int playerStartingPositionX = Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_X"));
        final int playerStartingPositionY = Integer.parseInt(Constants.getConstantOf("PLAYER_STARTING_POSITION_Y"));
        final int enemyNumber = Integer.parseInt(Constants.getConstantOf("NUM_ENEMIES"));
        final int mapSize = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));

        this.menuInitialization();
        this.mapInitialization(mapSize);
        this.playerInitialization(
                playerStartingPositionX,
                playerStartingPositionY,
                defaultExperience,
                playerLevelIncrease,
                moveNumber);
        this.enemiesInitialization(enemyNumber);
    }

    private void enemiesInitialization(final int enemyNumber){
        this.enemiesScattering = new HashMap<>();
        Random rand = new Random();
        final EnemyFactory factory = new EnemyFactoryImpl();
        for(int i = 0; i < enemyNumber; i++){
            Position enemyPosition;
            do{
                enemyPosition = new Position(rand.nextInt(this.map.getWidth()), rand.nextInt(this.map.getHeight()));
            } while(enemyPosition == player.getPosition() || !map.isInBoundaries(enemyPosition));
            this.enemiesScattering.put(enemyPosition,factory.createGoblin());
        }
    }

    private void menuInitialization(){
        this.startMenu = new MenuImpl();
        this.inGameMenu = new MenuImpl();
        this.startMenu.addMenuItem(new MenuItem("NEW GAME", Command.NEW_GAME));
        this.startMenu.addMenuItem(new MenuItem("QUIT", Command.QUIT));
        this.inGameMenu.addMenuItem(new MenuItem("CLOSE MENU", Command.CLOSE_MENU));
        this.inGameMenu.addMenuItem(new MenuItem("QUIT", Command.QUIT));
    }

    private void mapInitialization(final int size){
        this.map = new GameMapImpl(size);
    }

    private void playerInitialization(final int x,
                                      final int y,
                                      final double defaultExperience,
                                      final int levelIncrease,
                                      final int movesNumber){
        final Position startingPlayerPosition = new Position(x,y);
        this.player = new PlayerImpl(startingPlayerPosition,defaultExperience,levelIncrease,movesNumber);
    }

    @Override
    public Player getPlayer(){
        return this.player;
    }

    @Override
    public Menu getStartMenu() {
        return this.startMenu;
    }

    @Override
    public Menu getInGameMenu() {
        return this.inGameMenu;
    }

    @Override
    public GameMap getMap(){
        return this.map;
    }

    @Override
    public Map<Position, Enemy> getEnemies() {
        return Collections.unmodifiableMap(this.enemiesScattering);
    }

}
