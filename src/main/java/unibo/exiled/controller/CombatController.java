package unibo.exiled.controller;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MoveSet;

public class CombatController {
    private final Player player;
    private final Enemy enemy;

    public CombatController(final Player player,final Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    /**
     * @param character the character Player or Enemy that will take damage (defender)
     * @param move      the choosen move of the attacker
     */
    public void combat(final Character character,final MoveSet move) {

    }

    /*public String getPlayerImage() {
        return this.player.getImagePath();
    }

    public String getEnemyImage() {
        return this.enemy.getImagePath();
    }*/

    public MoveSet getPlayerMoveSet() {
        return this.player.getMoveSet();
    }

    public MoveSet getEnemyMoveSet() {
        return this.enemy.getMoveSet();
    }
}
