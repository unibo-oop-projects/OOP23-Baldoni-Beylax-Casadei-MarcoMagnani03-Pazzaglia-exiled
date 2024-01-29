package unibo.exiled.controller;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MoveSet;

public class CombatController {
    private Player player;
    private Enemy enemy;

    public CombatController(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    /**
     * 
     * @param character the character Player or Enemy that will take damage (defender)
     * @param move the choosen move of the attacker
     */
    public void combat(Character character, MoveSet move) {

    }
    
    public String getPlayerImage() {
        return this.player.getImagePath();
    }

    public String getEnemyImage() {
        return this.enemy.getImagePath();
    }

    public MoveSet getPlayerMoveSet() {
        return this.player.getMoveSet();
    }

    public MoveSet getEnemyMoveSet() {
        return this.enemy.getMoveSet();
    }
}
