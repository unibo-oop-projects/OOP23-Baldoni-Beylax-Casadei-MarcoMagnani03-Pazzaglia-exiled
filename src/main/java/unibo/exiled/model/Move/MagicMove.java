package unibo.exiled.model.Move;

import unibo.exiled.model.ElementalType;

public interface MagicMove {
    String getName();
    double getPower();
    ElementalType getType();
}
