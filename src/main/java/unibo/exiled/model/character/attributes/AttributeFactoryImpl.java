package unibo.exiled.model.character.attributes;

import java.util.Map;

/**
 * The implementation of the attribute factory.
 */
public final class AttributeFactoryImpl implements AttributeFactory {

    private static final int BASE_HEALTH = 10;

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
        return fromValues(BASE_HEALTH * 10, 1, 1, 1, BASE_HEALTH * 10);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createGoblinAttributes() {
        return fromValues(BASE_HEALTH, 1, 1, 1, BASE_HEALTH);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createBrutusAttributes() {
        return fromValues(BASE_HEALTH * 2, 1, 1, 1, BASE_HEALTH * 2);
    }

    @Override
    public Map<AttributeIdentifier, Attribute> createBossAttributes() {
        return fromValues(BASE_HEALTH * 10, 1, 1, 1, BASE_HEALTH * 3);
    }
}
