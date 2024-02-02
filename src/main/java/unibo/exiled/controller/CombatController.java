package unibo.exiled.controller;

import java.awt.event.ActionListener;

import unibo.exiled.model.character.*;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.utilities.Position;

public class CombatController {
    private final Player player;
    private Enemy enemy;

    public CombatController(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Enemy getEnemy() {
        return this.enemy;
    }

    /**
     * Sets the current enemy
     * @param enemy the enemy that will combat
     */
    public void setEnemy(final Enemy enemy) {
        this.enemy = enemy;
    }

    public MoveSet getPlayerMoveSet() {
        return this.player.getMoveSet();
    }

    public MoveSet getEnemyMoveSet() {
        return this.enemy.getMoveSet();
    }

    public void attack(boolean isPlayerAttacking) {
        if (isPlayerAttacking) {
            // this.enemy.increaseAttributeValue(AttributeIdentifier.HEALTH, -10000);
            this.enemy.move(new Position(1, 1));
        }
    }
}
