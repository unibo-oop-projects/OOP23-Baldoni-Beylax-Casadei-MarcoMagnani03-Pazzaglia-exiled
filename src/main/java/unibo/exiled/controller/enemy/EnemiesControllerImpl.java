package unibo.exiled.controller.enemy;

import java.util.Random;

import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.model.utilities.Positions;

public class EnemiesControllerImpl implements EnemiesController {
    private static final int RANGE_PLAYER_ENEMY = 2;

    private final GameModel model; 
    private final EnemyCollection enemies;

    public EnemiesControllerImpl(final GameModel model){
        this.model = model;
        this.enemies = model.getEnemies();
    }

    @Override
    public void moveEnemies() {
        final Random rnd = new Random();
        Direction rndDirection;
    
        for (Enemy enemy : enemies) {
            final Position currentEnemyPosition = enemy.getPosition();
            if (!isEnemyNearThePlayer(enemy)) {
                do {
                    rndDirection = Direction.values()[rnd.nextInt(4)];
                } while (!model.getMap().isInBoundaries(Positions.sum(currentEnemyPosition, rndDirection.getPosition())));
                enemy.move(Positions.sum(currentEnemyPosition, rndDirection.getPosition()));
            } else {
                // If the enemy and the player are close by a certain range of cells, 
                // then the enemy will try to chase the player.
                final Position playerPosition = model.getPlayer().getPosition();
    
                int distance = calculateDistance(currentEnemyPosition, playerPosition);

                if(distance != 0){
                    Direction chaseDirection = calculateChaseDirection(currentEnemyPosition, playerPosition);
                    enemy.move(Positions.sum(currentEnemyPosition, chaseDirection.getPosition()));
                }
            }
        }
    }

    private int calculateDistance(Position pos1, Position pos2) {
        int deltaX = pos1.x() - pos2.x();
        int deltaY = pos1.y() - pos2.y();
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private Direction calculateChaseDirection(Position currentEnemyPosition, Position playerPosition) {
        int deltaX = playerPosition.x() - currentEnemyPosition.x();
        int deltaY = playerPosition.y() - currentEnemyPosition.y();
    
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            return (deltaX > 0) ? Direction.EAST : Direction.WEST;
        } else {
            return (deltaY > 0) ? Direction.SOUTH : Direction.NORTH;
        }
    }
    

    @Override
    public EnemyCollection getEnemies() {
        return this.enemies;
    }

    
    private boolean isEnemyNearThePlayer(Enemy enemy) {
        final Position playerPosition = model.getPlayer().getPosition();
        final Position enemyPosition = enemy.getPosition();

        int verticalDistance = Math.abs(playerPosition.y() - enemyPosition.y());
        int horizontalDistance = Math.abs(playerPosition.x() - enemyPosition.x());

        if (verticalDistance <= RANGE_PLAYER_ENEMY && horizontalDistance <= RANGE_PLAYER_ENEMY) {
            return true; 
        }
        
        return false;
    }

    
}
