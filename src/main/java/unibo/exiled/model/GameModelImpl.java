package unibo.exiled.model;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.enemy.*;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerImpl;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.map.GameMapImpl;
import unibo.exiled.model.utilities.Position;

import java.util.Random;

public class GameModelImpl implements GameModel {
    private Player player;
    private GameMap map;
    private final EnemyCollection enemyCollection;

    public GameModelImpl(){
        //Constants loading
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        final int moveNumber = Integer.parseInt(Constants.getConstantOf("NUM_MOVES"));
        final double playerExperienceCap = Double.parseDouble(Constants.getConstantOf("PLAYER_EXPERIENCE_CAP"));
        final double defaultExperience = Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_EXPERIENCE"));
        final int playerLevelIncrease = Integer.parseInt(Constants.getConstantOf("PLAYER_LEVEL_INCREASE"));
        final int enemyNumber = Integer.parseInt(Constants.getConstantOf("NUM_ENEMIES"));
        final int mapSize = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));

        this.mapInitialization(mapSize);
        this.playerInitialization( playerExperienceCap,defaultExperience, playerLevelIncrease);
        this.enemyCollection = new EnemyCollectionImpl();
        this.enemyInitialization(enemyNumber);
    }

    private void enemyInitialization(int number){

        //Checks that the number is greater than 0;
        number = number > 0 ? number : 1;

        Random random = new Random();
        Position newEnemyPosition = null;
        EnemyFactory factory = new EnemyFactoryImpl();
        for(int i = 0; i< number ; i++){
            do{
                newEnemyPosition = new Position(random.nextInt(map.getWidth()),random.nextInt(map.getHeight()));
            }while(!isCellEmpty(newEnemyPosition));
            final Enemy newEnemy = factory.createRandom();
            newEnemy.move(newEnemyPosition);
            this.enemyCollection.addEnemy(newEnemy);
        }
    }

    private void mapInitialization(final int size){
        this.map = new GameMapImpl(size);
    }

    private void playerInitialization(final double  playerExperienceCap,final double defaultExperience, final int levelIncrease){
        this.player = new PlayerImpl( playerExperienceCap,defaultExperience, levelIncrease);
        this.player.move(new Position(map.getWidth() / 2, map.getHeight() / 2));
    }

    private boolean isCellEmpty(final Position position){
        if(!map.isInBoundaries(position)){
            return false;
        }
        return !player.getPosition().equals(position) && !enemyCollection.getEnemyFromPosition(position).isPresent();
    }

    @Override
    public Player getPlayer(){
        return this.player;
    }

    @Override
    public GameMap getMap(){ return this.map; }

    @Override
    public EnemyCollection getEnemies() {
        return this.enemyCollection;
    }
}
