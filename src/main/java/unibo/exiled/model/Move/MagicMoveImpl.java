package unibo.exiled.model.move;

import unibo.exiled.model.ElementalType;

public class MagicMoveImpl implements MagicMove {
    private final String name;
    private final double power;
    private final ElementalType type;

    public MagicMoveImpl(String name,double power, ElementalType type){
        this.name=name;
        this.power=power;
        this.type=type;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPower() {
        return this.power;
    }

    @Override
    public ElementalType getType() {
        return this.type;
    }
    
}
