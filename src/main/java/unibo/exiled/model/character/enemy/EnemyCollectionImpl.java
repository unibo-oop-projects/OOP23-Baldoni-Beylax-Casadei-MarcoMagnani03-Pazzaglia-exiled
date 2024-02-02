package unibo.exiled.model.character.enemy;

import unibo.exiled.model.utilities.Position;

import java.util.*;

public class EnemyCollectionImpl implements EnemyCollection{
    private final Set<Enemy> enemies;

    public EnemyCollectionImpl(){
        this.enemies = new HashSet<>();
    }

    @Override
    public void addEnemy(final Enemy enemy){
        this.enemies.add(enemy);
    }

    @Override
    public void removeEnemy(final Enemy enemy){
        this.enemies.remove(enemy);
    }

    @Override
    public Set<Enemy> getEnemies(){
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
