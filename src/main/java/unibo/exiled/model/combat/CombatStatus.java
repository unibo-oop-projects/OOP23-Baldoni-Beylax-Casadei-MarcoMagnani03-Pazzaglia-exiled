package unibo.exiled.model.combat;

/**
 * The status of the combat.
 */
public enum CombatStatus {
    /**
     * Default status. Waiting for an input from the user
     */
    IDLE,
    /**
     * Attacking status. The player or the enemy are performing the attack routine.
     */
    ATTACKING,
    /**
     * Defeated status. Where the player or the enemy are defeated.
     */
    DEFEATED
}
