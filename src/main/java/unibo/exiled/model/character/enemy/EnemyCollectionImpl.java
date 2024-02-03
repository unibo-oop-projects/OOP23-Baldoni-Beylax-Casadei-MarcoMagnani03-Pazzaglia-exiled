package unibo.exiled.model.character.enemy;

import unibo.exiled.model.utilities.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.Optional;
import java.util.Iterator;

/**
 * An iterable set of enemies.
 */
public final class EnemyCollectionImpl implements EnemyCollection {
    private final Set<Enemy> enemies;

    /**
     * The constructor of the enemy collection, initializes the Set.
     */
    public EnemyCollectionImpl() {
        this.enemies = new HashSet<>();
    }

    @Override
    public void addEnemy(final Enemy enemy) {
        this.enemies.add(enemy);
    }

    @Override
    public void removeEnemy(final Enemy enemy) {
        this.enemies.remove(enemy);
    }

    @Override
    public Set<Enemy> getEnemies() {
        return Collections.unmodifiableSet(this.enemies);
    }

    @Override
    public Optional<Enemy> getEnemyFromPosition(final Position position) {
        return this.enemies.stream().filter(enemy -> enemy.getPosition().equals(position)).findFirst();
    }

    @Override
    public Iterator<Enemy> iterator() {
        return this.enemies.iterator();
    }
}
