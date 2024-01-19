package unibo.exiled.model.item;

interface ItemFactory {
    Item createHealingItem(final String name,final String description,final double healingValue);
    Item createPowerUpItem(final String name,final String description,final double powerUpValue,final int duration);
    Item createUnUsableItem(final String name,final String description);
}
