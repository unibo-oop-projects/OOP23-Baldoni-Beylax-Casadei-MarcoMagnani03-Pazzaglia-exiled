package unibo.exiled.model.character;

import unibo.exiled.model.move.MoveSet;

public interface Character {
    MoveSet getMoveSet();
    void move();
}
