package unibo.exiled.model.character.enemy;

import unibo.exiled.model.utilities.Position;

import java.util.Optional;
import java.util.Set;

public interface EnemyCollection extends Iterable<Enemy> {
    void removeEnemy(Enemy enemy);
    void addEnemy(Enemy enemy);
    Set<Enemy> getEnemies();
    Optional<Enemy> getEnemyFromPosition(Position position);
}
