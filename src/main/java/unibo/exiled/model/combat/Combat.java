package unibo.exiled.model.combat;

import java.util.Optional;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.utilities.Position;

/**
 * The model rapresenting the current combat.
 */
public interface Combat {

    /**
     * Gets the combat position.
     * 
     * @return the combat position.
     */
    Position getCombatPosition();

    /**
     * Sets the combat position.
     * 
     * @param position the combat position.
     */
    void setCombatPosition(Position position);

    /**
     * Gets the combat status.
     * 
     * @return the combat status.
     */
    CombatStatus getCombatStatus();

    /**
     * Sets the combat status.
     * 
     * @param status the new status.
     */
    void setCombatStatus(CombatStatus status);

    /**
     * Returns the player thas is fighting.
     * 
     * @return the player thas is fighting.
     */
    Optional<Player> getPlayer();

    /**
     * Returns the enemy thas is fighiting.
     * 
     * @return the enemy that is fighiting.
     */
    Optional<Enemy> getEnemy();
}
