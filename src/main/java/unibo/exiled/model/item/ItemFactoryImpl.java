package unibo.exiled.model.item;

import unibo.exiled.model.character.attributes.AttributeIdentifier;

public class ItemFactoryImpl implements ItemFactory{

    @Override
    public Item createHealingItem(final String name, final String description, final double healingValue) {
        return new HealingItem(name,description,healingValue);
    }

    @Override
    public Item createPowerUpItem(final String name,final String description,final double powerUpValue,final int duration,final AttributeIdentifier boostedAttribute) {
        return new PowerUpItem(name,description,powerUpValue,duration,boostedAttribute);
    }

    @Override
    public Item createUnUsableItem(final String name,final String description) {
        return new UnUsableItem(name,description);
    }

}
