package unibo.exiled.controller;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.UsableItem;

import java.util.Map;

public record InventoryController(Player player) {
    public boolean useItem(UsableItem item) {
        if (player.getInventory().containsItem(item)) {
            item.use(player);
            player.getInventory().removeItem(item);
            return true;
        } else {
            return false;
        }
    }
    public Map<Item, Integer> getItems() {
        return player.getInventory().getItems();
    }
}
