package unibo.exiled.model.game;

import java.util.Map;
import java.util.Set;

import unibo.exiled.model.item.ItemType;
import unibo.exiled.model.move.MagicMove;

public interface ItemsModel {
    /**
     * Returns the items of the player.
     *
     * @return The items of the player.
     */
    Map<String, Integer> getItems();

    /**
     * Returns the description of a given item.
     *
     * @param itemName The name of the item.
     * @return the description of a given item.
     */
    String getItemDescription(String itemName);

    /**
     * Returns the valor of a given item.
     *
     * @param itemName The name of the item.
     * @return The valor of the given item.
     */
    double getItemValor(String itemName);

    /**
     * Returns the type of a given item.
     *
     * @param itemName The name of the item.
     * @return The type of the given item.
     */
    ItemType getItemType(String itemName);

    /**
     * Returns the boosted attribute name of a given item.
     *
     * @param itemName The name of the item.
     * @return The boosted attribute name of the given item.
     */
    String getItemBoostedAttributeName(String itemName);

    /**
     * Returns the duration of a given item.
     *
     * @param itemName The name of the item.
     * @return The duration of the given item.
     */
    int getItemDuration(String itemName);

    /**
     * Gets a set of every magic move in the game.
     *
     * @return A set of MagicMoves.
     */
    Set<MagicMove> getMagicMoves();

    /**
     * Attempts to use the specified item from the player's inventory.
     *
     * @param item The item to use.
     * @return true if the item was successfully used, false otherwise.
     */
    boolean useItem(String item);
}
