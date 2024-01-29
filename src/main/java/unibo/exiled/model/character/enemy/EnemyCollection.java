package unibo.exiled.model.character.enemy;

import unibo.exiled.model.utilities.Position;

import java.util.Optional;
import java.util.Set;

public interface EnemyCollection extends Iterable {
    void removeEnemy(final Enemy enemy);
    void addEnemy(final Enemy enemy);
    Set<Enemy> getEnemies();
    Optional<Enemy> getEnemyFromPosition(final Position position);
}
