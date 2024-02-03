package unibo.exiled.model.character.enemy;

import unibo.exiled.model.character.GameCharacter;

public interface Enemy extends GameCharacter {
    String getName();
    double getDroppedExperience();
}
