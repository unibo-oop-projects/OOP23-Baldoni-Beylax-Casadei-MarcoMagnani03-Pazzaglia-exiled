package unibo.exiled.model.Item;

import java.util.HashMap;
import java.util.Map;

public class InventoryImpl implements Inventory{

    Map<Item,Integer> itemsList;
    
    public InventoryImpl(){
        itemsList = new HashMap<>();
    }

    @Override
    public void addItem(Item item) {
        itemsList.put(item, itemsList.getOrDefault(item, 0) + 1);
    }

    @Override
    public void removeItem(Item item) {
        int quantity = itemsList.get(item);
        if (quantity > 1) {
            itemsList.put(item, quantity - 1);
        } else if(quantity == 1){
            itemsList.remove(item);
        }
    }

    @Override
    public Integer getQuantity(Item item) {
        return itemsList.get(item);
    }

    @Override
    public boolean containsItem(UsableItem item) {
        return itemsList.containsKey(item);
    }
}
