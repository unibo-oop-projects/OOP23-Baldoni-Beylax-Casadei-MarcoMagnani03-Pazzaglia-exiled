package unibo.exiled.model.item;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InventoryImpl implements Inventory{

    Map<Item,Integer> itemsList;
    
    public InventoryImpl(){
        itemsList = new HashMap<>();
    }

    @Override
    public void addItem(Item item) {
        if(containsItem(item)){
            itemsList.put(item, itemsList.get(item) + 1);
        }
        else{
            itemsList.putIfAbsent(item, 1);
        }
    }

    @Override
    public void removeItem(Item item) {
        if(containsItem(item)){
            int quantity = itemsList.get(item);
            if (quantity > 1) {
                itemsList.put(item, quantity - 1);
            } else if(quantity == 1){
                itemsList.remove(item);
            }
        }
    }

    @Override
    public Integer getItemQuantity(Item item) {
        return itemsList.get(item);
    }

    @Override
    public boolean containsItem(Item item) {
        return itemsList.containsKey(item);
    }

    @Override
    public Map<Item, Integer> getItems() {
        return Collections.unmodifiableMap(itemsList);
    }
}
