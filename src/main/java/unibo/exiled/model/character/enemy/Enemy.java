package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.Character;

public interface Enemy extends Character {
    String getName();
    double getDroppedExperience();
}
