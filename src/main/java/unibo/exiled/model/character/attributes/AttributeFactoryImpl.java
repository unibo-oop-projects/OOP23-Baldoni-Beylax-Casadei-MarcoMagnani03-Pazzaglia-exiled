package unibo.exiled.model.character.attributes;

import java.util.Map;

/**
 * The implementation of the attribute factory.
 */
public final class AttributeFactoryImpl implements AttributeFactory {

    private final int baseHealth = 10;

    private Map<AttributeIdentifier, Attribute> fromValues(final double health,
                                                           final double healthModifier,
                                                           final double attack,
                                                           final double defense,
                                                           final double healthCap) {
        return Map.of(AttributeIdentifier.HEALTH,
                new CombinedAttributeImpl(health, healthModifier),
                AttributeIdentifier.ATTACK, new MultiplierAttributeImpl(attack),
                AttributeIdentifier.DEFENSE, new MultiplierAttributeImpl(defense),
                AttributeIdentifier.HEALTHCAP, new AdditiveAttributeImpl(healthCap));
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createPlayerAttributes() {

        return fromValues(baseHealth * 10, 1, 1, 1, baseHealth * 10);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createGoblinAttributes() {

        return fromValues(baseHealth, 1, 1, 1, baseHealth);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createBrutusAttributes() {

        return fromValues(baseHealth * 2, 1, 1, 1, baseHealth * 2);
    }
}
