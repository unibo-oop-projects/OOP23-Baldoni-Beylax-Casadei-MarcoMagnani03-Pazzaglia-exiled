package unibo.exiled.controller;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.Position;

/**
 * The controller of the combat between two characters.
 */
public final class CombatController {
    private final Player player;
    private Enemy enemy;

    /**
     * The constructor of the CombatController.
     *
     * @param player The player engaged in the combat.
     */
    public CombatController(final Player player) {
        this.player = player;
    }

    /**
     * Gets the player engaged in the combat.
     *
     * @return The player engaged in the combat.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the enemy engaged in the combat.
     *
     * @return The enemy engaged in the combat.
     */
    public Enemy getEnemy() {
        return this.enemy;
    }

    /**
     * Sets the current enemy.
     *
     * @param enemy the enemy that will combat.
     */
    public void setEnemy(final Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Gets the Move Set of the player.
     *
     * @return The MoveSet of the player.
     */
    public MoveSet getPlayerMoveSet() {
        return this.player.getMoveSet();
    }

    /**
     * Gets the Move Set of the enemy.
     *
     * @return The MoveSet of the enemy.
     */
    public MoveSet getEnemyMoveSet() {
        return this.enemy.getMoveSet();
    }

    /**
     * Performs an attack action from one of the two characters engaged in the combat.
     *
     * @param isPlayerAttacking True if the player is attacking, false if the enemy is attacking.
     */
    public void attack(final boolean isPlayerAttacking) {
        if (isPlayerAttacking) {
            // this.enemy.increaseAttributeValue(AttributeIdentifier.HEALTH, -10000);
            this.enemy.move(new Position(1, 1));
        }
    }
}
