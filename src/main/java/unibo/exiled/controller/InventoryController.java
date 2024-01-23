package unibo.exiled.controller;

import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.UsableItem;

import java.util.Map;

public class InventoryController {
    private Inventory inventory;

    public InventoryController(final Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean useItem(UsableItem item) {
        if (inventory.containsItem(item)) {
            inventory.removeItem(item);
            return true;
        } else {
            return false;
        }
    }

    public Map<Item, Integer> getItems() {
        return inventory.getItems();
    }
}
