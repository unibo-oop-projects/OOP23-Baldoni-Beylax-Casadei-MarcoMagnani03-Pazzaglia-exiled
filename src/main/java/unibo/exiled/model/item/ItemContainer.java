package unibo.exiled.model.item;

import unibo.exiled.model.utilities.ItemType;
import java.util.Optional;
import java.util.Set;

/**
 * Interface representing the container for items.
 */
public interface ItemContainer {
    /**
     * Retrieves an item by its name from the container.
     *
     * @param name The name of the item to retrieve.
     * @return An Optional containing the item if found, or an empty Optional if not found.
     */
    Optional<Item> getItemByName(String name);

    /**
     * Retrieves a list of all items in the container.
     *
     * @return A list containing all items in the container.
     */
    Set<Item> getAllItems();

    /**
     * Retrieves a random item of a specified type from the container.
     *
     * @param type The type of the item to retrieve.
     * @return An Optional containing a random item of the specified type if found,
     *         or an empty Optional if no such item is found.
     */
    Optional<Item> getRandomItemByType(ItemType type);
}
