package unibo.exiled.model.character.player;

import unibo.exiled.model.utilities.ElementalType;

public class PlayerClassImpl implements PlayerClass {

    private final ElementalType elementalType;

    public PlayerClassImpl(ElementalType elementalType){
        this.elementalType = elementalType;
    }
    @Override
    public double getAttributeModifier() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttributeModifier'");
    }

    @Override
    public ElementalType getElementalType() {
        return this.elementalType;
    }
    
}
