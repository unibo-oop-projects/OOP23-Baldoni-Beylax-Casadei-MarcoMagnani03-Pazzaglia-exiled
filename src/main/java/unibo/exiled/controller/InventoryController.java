package unibo.exiled.controller;

import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.UsableItem;

import java.util.Map;

/**
 * Controller class for managing the player's inventory.
 *
 * @param player The player associated with this inventory controller.
 */
public record InventoryController(Player player) {
    /**
     * Attempts to use the specified usable item from the player's inventory.
     *
     * @param item The usable item to use.
     * @return true if the item was successfully used, false otherwise.
     */
    public boolean useItem(final UsableItem item) {
        if (player.getInventory().containsItem(item)) {
            item.use(player);
            player.getInventory().removeItem(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves a map of items and their quantities from the player's inventory.
     *
     * @return A map containing items and their quantities.
     */
    public Map<Item, Integer> getItems() {
        return player.getInventory().getItems();
    }
}
