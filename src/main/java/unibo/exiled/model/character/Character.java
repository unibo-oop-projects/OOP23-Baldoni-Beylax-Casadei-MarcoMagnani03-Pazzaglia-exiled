package unibo.exiled.model.character;

import unibo.exiled.model.Move.MoveSet;

public interface Character {
    MoveSet getMoveSet();
    void Move();
}
