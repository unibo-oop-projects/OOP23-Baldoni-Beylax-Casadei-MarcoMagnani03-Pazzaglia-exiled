package unibo.exiled.controller;

import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.UsableItem;

import java.util.Map;

import javax.swing.*;

public class InventoryController {
    private Inventory inventory;

    public InventoryController(final Inventory inventory) {
        this.inventory = inventory;
    }

    public void useItem(UsableItem item) {
        if (inventory.containsItem(item)) {
            inventory.removeItem(item);
            //NON VA BENE, il controller non sa cos'è un JOptionPane
            JOptionPane.showMessageDialog(null, "Used " + item.getName());
        } else {
            JOptionPane.showMessageDialog(null, "Item not found in the inventory");
        }
    }

    public Map<Item, Integer> getItems() {
        return inventory.getItems();
    }
}
