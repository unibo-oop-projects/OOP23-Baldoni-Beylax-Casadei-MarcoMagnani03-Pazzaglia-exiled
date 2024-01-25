package unibo.exiled.controller;

import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.utilities.Position;

public record EnemyController(Enemy enemy) implements CharacterController{
    @Override
    public void move(Position position) {
        this.enemy.move(position);
    }
}
