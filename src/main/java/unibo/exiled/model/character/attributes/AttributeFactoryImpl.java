package unibo.exiled.model.character.attributes;

import java.util.Map;
import java.util.Optional;

public class AttributeFactoryImpl implements AttributeFactory {

    private Map<AttributeIdentifier,Attribute> fromValues(
            final double attackModifier,
            final double healthValue,
            final double healthModifier,
            final double defenseModifier,
            final double healthCapValue,
            final double healthCapModifier){
        return Map.of(
                AttributeIdentifier.ATTACK,new AttributeImpl(Optional.of(attackModifier),Optional.empty()),
                AttributeIdentifier.HEALTH,new AttributeImpl(Optional.of(healthModifier),Optional.of(healthValue)),
                AttributeIdentifier.DEFENSE, new AttributeImpl(Optional.of(defenseModifier),Optional.empty()),
                AttributeIdentifier.HEALTHCAP, new AttributeImpl(Optional.of(healthCapModifier),Optional.of(healthCapValue))
        );
    }

    @Override
    public Map<AttributeIdentifier,Attribute> createBasicAttributes(){
        return this.fromValues(1,100,
                1,1,200,1);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createGoblinAttributes() {
        return this.fromValues(1,10,1,
                1,10,1);
    }
}
