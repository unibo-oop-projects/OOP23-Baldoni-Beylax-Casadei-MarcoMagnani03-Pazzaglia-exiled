package unibo.exiled.model.item;

import unibo.exiled.model.character.player.Player;

/**
 * The ItemWithDuration interface extends the UsableItem interface
 * and defines an additional method to retrieve the duration of an item with a limited effect.
 */
public interface ItemWithDuration extends UsableItem {

    /**
     * Gets the duration for which the item's effect lasts.
     *
     * @return The duration of the item's effect.
     */
    int getDuration();

    /**
     * Disable the item when the duration is 0.
     *
     * @param player The player on which the item is disabled.
     */
    void disable(Player player);

    /**
     * Decrease the duration of the item by 1.
     */
    void decreaseDuration();
}
