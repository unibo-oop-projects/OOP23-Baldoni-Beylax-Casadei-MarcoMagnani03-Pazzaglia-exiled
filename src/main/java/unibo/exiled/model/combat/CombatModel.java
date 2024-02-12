package unibo.exiled.model.combat;

import java.util.Optional;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.utilities.Position;

/**
 * The model wrapping the current combat.
 */
public interface CombatModel {

    /**
     * Creates a new combat.
     * 
     * @param position the position of the combat.
     */
    void createCombat(Position position);

    /**
     * Returns the current combat.
     * 
     * @return the current combat.
     */
    Optional<Combat> getCurrentCombat();

    /**
     * Returns the player thas is fighting.
     * 
     * @return the player thas is fighting.
     */
    Optional<Player> getPlayer();

    /**
     * Returns the enemy thas is fighiting.
     * 
     * @param position the position of the enemy.
     * @return the enemy that is fighiting.
     */
    Optional<Enemy> getEnemy(Position position);
}
