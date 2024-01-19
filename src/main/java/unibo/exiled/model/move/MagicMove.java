package unibo.exiled.model.move;

import unibo.exiled.model.utilities.ElementalType;

public interface MagicMove {
    String getName();
    double getPower();
    ElementalType getType();
}
