package unibo.exiled.model.item;

import java.util.Optional;

public class ItemFactoryImpl implements ItemFactory{

    @Override
    public Item createItem(String name, String description, Optional<Double> value, ItemType itemType) {
        if (itemType.equals(ItemType.POWERUP)) {
            return new HealingItem(name, description, value.get());
        } else if (itemType.equals(ItemType.HEALING)) {
            return new PowerUpItem(name, description, value.get());
        } else {
            return new StandardItem(name, description);
        }
    }
}
