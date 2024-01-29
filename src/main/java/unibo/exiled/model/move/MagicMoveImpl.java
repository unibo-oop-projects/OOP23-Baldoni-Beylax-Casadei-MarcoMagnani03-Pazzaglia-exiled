package unibo.exiled.model.move;

import unibo.exiled.model.utilities.ElementalType;

public class MagicMoveImpl implements MagicMove {
    private final String name;
    private final String description;
    private final double power;
    private final ElementalType type;

    public MagicMoveImpl(String name,String description,double power, ElementalType type){
        this.name=name;
        this.description = description;
        this.power=power;
        this.type=type;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
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
