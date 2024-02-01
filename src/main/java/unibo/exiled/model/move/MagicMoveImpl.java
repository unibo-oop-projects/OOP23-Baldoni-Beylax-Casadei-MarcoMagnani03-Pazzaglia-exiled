package unibo.exiled.model.move;

import unibo.exiled.model.utilities.ElementalType;

public record MagicMoveImpl(String name, String description, double power, ElementalType type) implements MagicMove { }
