package unibo.exiled.model.character.attributes;

import java.util.Map;
import java.util.Optional;

import unibo.exiled.config.Constants;

public class AttributeFactoryImpl implements AttributeFactory {

    public AttributeFactoryImpl(){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
    }

    private Map<AttributeIdentifier,Attribute> fromValues(
            final double healthValue,
            final double healthCapValue
            ){
        if(healthCapValue < healthValue){
            throw new IllegalArgumentException("The health cap can't be lower than actual health");
        }
        return Map.of(
                AttributeIdentifier.ATTACK,new AttributeImpl(Optional.of(Double.parseDouble(Constants.getConstantOf("DEFAULT_MODIFIER"))),Optional.empty()),
                AttributeIdentifier.HEALTH,new AttributeImpl(Optional.of(Double.parseDouble(Constants.getConstantOf("DEFAULT_MODIFIER"))),Optional.of(healthValue)),
                AttributeIdentifier.DEFENSE, new AttributeImpl(Optional.of(Double.parseDouble(Constants.getConstantOf("DEFAULT_MODIFIER"))),Optional.empty()),
                AttributeIdentifier.HEALTHCAP, new AttributeImpl(Optional.of(Double.parseDouble(Constants.getConstantOf("DEFAULT_MODIFIER"))),Optional.of(healthCapValue))
        );
    }

    private Map<AttributeIdentifier,Attribute> fromValues(
            final double healthValue,
            final double healthModifier,
            final double healthCapValue,
            final double healthCapModifier,
            final double attackModifier,
            final double defenseModifier
            ){
        if(healthCapValue < healthValue){
            throw new IllegalArgumentException("The health cap can't be lower than actual health");
        }
        return Map.of(
                AttributeIdentifier.ATTACK,new AttributeImpl(Optional.of(attackModifier),Optional.empty()),
                AttributeIdentifier.HEALTH,new AttributeImpl(Optional.of(healthModifier),Optional.of(healthValue)),
                AttributeIdentifier.DEFENSE, new AttributeImpl(Optional.of(defenseModifier),Optional.empty()),
                AttributeIdentifier.HEALTHCAP, new AttributeImpl(Optional.of(healthCapModifier),Optional.of(healthCapValue))
        );
    }

    @Override
    public Map<AttributeIdentifier,Attribute> createPlayerAttributes(){
        return this.fromValues(
                    Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_HEALTH")),
                    Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_HEALTH_MODIFIER")),
                    Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_HEALTH_CAP")),
                    Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_HEALTH_CAP_MODIFIER")),
                    Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_ATTACK")),
                    Double.parseDouble(Constants.getConstantOf("PLAYER_DEFAULT_DEFENSE"))
                );
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createGoblinAttributes() {
        return this.fromValues(
                10,
                10
            );
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createBrutusAttributes() {
        return this.fromValues(
                20,
                20
            );
    }
}
