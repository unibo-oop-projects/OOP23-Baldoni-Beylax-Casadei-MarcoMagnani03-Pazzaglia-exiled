package unibo.exiled.model.character.attributes;

import java.util.Map;

public class AttributeFactoryImpl implements AttributeFactory {

    private Map<AttributeIdentifier, Attribute> fromValues(final double health, final double healthModifier, final double attack, final double defense, final double healthCap) {
        return Map.of(AttributeIdentifier.HEALTH, new CombinedAttributeImpl(health, healthModifier), AttributeIdentifier.ATTACK, new MultiplierAttributeImpl(attack), AttributeIdentifier.DEFENSE, new MultiplierAttributeImpl(defense), AttributeIdentifier.HEALTHCAP, new AdditiveAttributeImpl(healthCap));
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createPlayerAttributes() {
        return fromValues(100, 1, 1, 1, 100);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createGoblinAttributes() {
        return fromValues(10, 1, 1, 1, 10);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createBrutusAttributes() {
        return fromValues(20, 1, 1, 1, 20);
    }
}
