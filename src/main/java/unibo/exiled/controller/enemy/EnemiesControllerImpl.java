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

    public EnemiesControllerImpl(final GameModel model){
        this.model = model;
    }

    @Override
    public void moveEnemies() {
        final EnemyCollection enemies = model.getEnemies();
        final Random rnd = new Random();
        Direction rndDirection;
        
        for (Enemy enemy : enemies) {
            final Position currentEnemyPosition = enemy.getPosition();
            Position newPosition = currentEnemyPosition;
            if (!isEnemyNearThePlayer(enemy)) {
                do {
                    rndDirection = Direction.values()[rnd.nextInt(4)];
                } while (!model.getMap().isInBoundaries(Positions.sum(currentEnemyPosition, rndDirection.getPosition())));
                newPosition = Positions.sum(currentEnemyPosition, rndDirection.getPosition());
            } else {
                /* If the enemy and the player are close by a certain range of cells, 
                then the enemy will try to chase the player. */
                final Position playerPosition = model.getPlayer().getPosition();
    
                int distance = calculateDistance(currentEnemyPosition, playerPosition);

                // This check is used to ensure that the player and the enemy meet when their distance is equal to 0.
                if(distance != 0){
                    Direction chaseDirection = calculateChaseDirection(currentEnemyPosition, playerPosition);
                    newPosition = Positions.sum(currentEnemyPosition, chaseDirection.getPosition());  
                }
            }
            enemy.move(newPosition);
        }
    }

    /**
     * Checks if the specified enemy is near the player.
     * @param enemy the enemy to check.
     * @return if the enemy is near the player.
     */
    private boolean isEnemyNearThePlayer(final Enemy enemy) {
        final Position playerPosition = model.getPlayer().getPosition();
        final Position enemyPosition = enemy.getPosition();
        final int verticalDistance = Math.abs(playerPosition.y() - enemyPosition.y());
        final int horizontalDistance = Math.abs(playerPosition.x() - enemyPosition.x());
        if (verticalDistance <= RANGE_PLAYER_ENEMY && horizontalDistance <= RANGE_PLAYER_ENEMY) {
            return true; 
        }
        return false;
    }

    /**
     * Calculates the distance between two positions.
     * @param pos1 the first position.
     * @param pos2 the second position.
     * @return the distance between the two positions.
     */
    private int calculateDistance(Position pos1, Position pos2) {
        final int deltaX = pos1.x() - pos2.x();
        final int deltaY = pos1.y() - pos2.y();
        return (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Calculates the direction in which an enemy should move to chase the player.
     * @param currentEnemyPosition the current position of the enemy.
     * @param playerPosition the position of the player.
     * @return the direction in which the enemy should move to chase the player.
     */
    private Direction calculateChaseDirection(final Position currentEnemyPosition, final Position playerPosition) {
        final int deltaX = playerPosition.x() - currentEnemyPosition.x();
        final int deltaY = playerPosition.y() - currentEnemyPosition.y();
        
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            return (deltaX > 0) ? Direction.EAST : Direction.WEST;
        } else {
            return (deltaY > 0) ? Direction.SOUTH : Direction.NORTH;
        }
    }
    
    @Override
    public EnemyCollection getEnemies() {
        return this.model.getEnemies();
    }

}
