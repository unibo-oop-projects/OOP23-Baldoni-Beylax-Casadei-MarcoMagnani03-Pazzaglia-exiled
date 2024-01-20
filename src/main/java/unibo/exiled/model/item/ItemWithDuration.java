package unibo.exiled.model.item;

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
}
