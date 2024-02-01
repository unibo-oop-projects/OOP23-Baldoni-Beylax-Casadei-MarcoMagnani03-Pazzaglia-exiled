package unibo.exiled.model.move;

import unibo.exiled.model.utilities.ElementalType;

public class MagicMoveFactoryImpl implements MagicMoveFactory{

    @Override
    public MagicMove createNormalMagicMove(String name, String description, double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.NORMAL);
    }

    @Override
    public MagicMove createFireMagicMove(String name, String description, double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.FIRE);
    }

    @Override
    public MagicMove createWaterMagicMove(String name, String description, double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.WATER);
    }

    @Override
    public MagicMove createBoltMagicMove(String name, String description, double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.BOLT);
    }

    @Override
    public MagicMove createGrassMagicMove(String name, String description, double power) {
        return new MagicMoveImpl(name, description, power, ElementalType.GRASS);
    }
    
}