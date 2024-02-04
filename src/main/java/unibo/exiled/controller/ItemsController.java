package unibo.exiled.controller;

import java.util.Map;

import unibo.exiled.model.item.ItemType;

/**
 * Controller for managing the items.
 */
public interface ItemsController {
    /**
     * Attempts to use the specified item from the player's inventory.
     *
     * @param item The item name to use.
     * @return true if the item was successfully used, false otherwise.
     */
    boolean useItem(String item);

    /**
     * Gets an association of items and their quantity.
     *
     * @return A map of Item names and their quantity for the view.
     */
    Map<String, Integer> getItems();

    /**
     * Gets the modification value of the item.
     *
     * @param itemName The item to look for.
     * @return A double value representing how strong is the item.
     */
    double getItemValor(String itemName);

    /**
     * Gets the description of an item based on its name.
     *
     * @param itemName The name of the item.
     * @return A string representing the description of the item.
     */
    String getItemDescription(String itemName);

    /**
     * Gets the type of the item with a certain name.
     *
     * @param itemName The name of the item to look for.
     * @return The ItemType of the selected itemName.
     */
    ItemType getItemType(String itemName);

    /**
     * Gets the name of the boosted attribute of an item.
     *
     * @param itemName The item that boosts the attribute.
     * @return A String representing the name of the boosted attribute.
     */
    String getItemBoostedAttributeName(String itemName);

    /**
     * Gets the duration of an item based of its name.
     *
     * @param itemName The name of the item.
     * @return An integer representing the duration of the item (in turns)
     */
    int getItemDuration(String itemName);
}
