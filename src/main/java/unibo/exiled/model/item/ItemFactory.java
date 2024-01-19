package unibo.exiled.model.item;

import java.util.Optional;

interface ItemFactory {

    Item createItem(final String name,final String description,final Optional<Double> value,final ItemType itemType);
}
