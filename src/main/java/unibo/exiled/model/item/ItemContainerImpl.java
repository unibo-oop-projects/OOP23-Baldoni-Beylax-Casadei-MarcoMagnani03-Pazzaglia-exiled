package unibo.exiled.model.item;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import unibo.exiled.model.utilities.ItemType;

public class ItemContainerImpl implements ItemContainer{

    List<Item> items;

    public ItemContainerImpl(){
        items = new LinkedList<>();
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public Optional<Item> getItemByName(String name) {
        return items.stream()
            .filter(item -> item.getName().equals(name))
            .findFirst();
    }

    @Override
    public List<Item> getAllItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public Optional<Item> getRandomItemByType(ItemType type) {
        Random random = new Random();
        List<Item> itemByType = items.stream()
                .filter(items -> items.getType() == type)
                .toList();
        
        if(itemByType.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(itemByType.get(random.nextInt(itemByType.size())));
    }
    
}
