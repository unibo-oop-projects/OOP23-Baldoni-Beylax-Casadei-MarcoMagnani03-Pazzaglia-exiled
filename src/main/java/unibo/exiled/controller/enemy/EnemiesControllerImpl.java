package unibo.exiled.controller.enemy;

import java.util.Random;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.map.GameMap;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;

public class EnemiesControllerImpl implements EnemiesController {

    private final EnemyCollection enemies;

    public EnemiesControllerImpl(final EnemyCollection enemies){
        this.enemies = enemies;
    }

    @Override
    public void moveEnemies(final GameMap map, final Player player) {
        final Random rnd = new Random();
        Direction rndDirection;

        for (Enemy enemy : enemies) {
            final Position currentEnemyPosition = enemy.getPosition();
            do{
                rndDirection = Direction.values()[rnd.nextInt(4)];
            }while(!map.isInBoundaries(Positions.sum(currentEnemyPosition, rndDirection.getPosition())));
            enemy.move(Positions.sum(currentEnemyPosition, rndDirection.getPosition()));
        }
    }

    @Override
    public EnemyCollection getEnemies() {
        return this.enemies;
    }

    
    private boolean isAnyEnemyNearThePlayer(final Player player) {
        return false;
    }

    
}
