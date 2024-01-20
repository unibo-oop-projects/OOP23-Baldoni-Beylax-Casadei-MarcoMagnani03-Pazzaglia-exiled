package unibo.exiled.model.item;

/**
 * The Inventory interface represents a collection of items in a game or application.
 * It provides methods for managing items such as adding, retrieving quantity, removing, and checking item presence.
 */
public interface Inventory {

    /**
     * Adds the specified item to the inventory.
     *
     * @param item The item to be added to the inventory.
     */
    void addItem(final Item item);

    /**
     * Retrieves the quantity of the specified item in the inventory.
     *
     * @param item The item whose quantity needs to be retrieved.
     * @return The quantity of the specified item in the inventory.
     */
    Integer getQuantity(final Item item);

    /**
     * Removes the specified item from the inventory.
     *
     * @param item The item to be removed from the inventory.
     */
    void removeItem(final Item item);

    /**
     * Checks if the inventory contains the specified usable item.
     *
     * @param item The usable item to check for in the inventory.
     * @return true if the inventory contains the item, false otherwise.
     */
    boolean containsItem(final UsableItem item);
}
