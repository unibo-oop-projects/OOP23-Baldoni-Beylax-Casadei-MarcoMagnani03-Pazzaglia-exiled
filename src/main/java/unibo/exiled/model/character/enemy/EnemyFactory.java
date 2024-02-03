package unibo.exiled.model.character.enemy;

/**
 * The EnemyFactory interface provides methods for creating different types of enemies.
 */
public interface EnemyFactory {
    /**
     * Creates a goblin enemy.
     *
     * @return The created goblin enemy.
     */
    Enemy createGoblin();

    /**
     * Creates a brutus enemy.
     *
     * @return The created brutus enemy.
     */
    Enemy createBrutus();

    /**
     * Creates a random enemy.
     *
     * @return The created random enemy.
     */
    Enemy createRandom();
}
