package unibo.exiled.controller;

import java.util.Map;
import java.util.Objects;

import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.item.ItemType;

/**
 * Implementation of the ItemsController interface.
 */
public final class ItemsControllerImpl implements ItemsController {

    private final GameModel model;

    /**
     * Constructor for the ItemsControllerImpl.
     *
     * @param model The game model to manage the game.
     */
    public ItemsControllerImpl(final GameModel model) {
        this.model = Objects.requireNonNull(model);
    }

    /**
     * Gets a map of items and their quantities.
     *
     * @return A map representing items and their quantities.
     */
    @Override
    public Map<String, Integer> getItems() {
        return model.getItems();
    }

    /**
     * Gets the description of a specific item.
     *
     * @param itemName The name of the item.
     * @return The description of the item.
     */
    @Override
    public String getItemDescription(final String itemName) {
        return model.getItemDescription(itemName);
    }

    /**
     * Gets the valor of a specific item.
     *
     * @param itemName The name of the item.
     * @return The valor of the item.
     */
    @Override
    public double getItemValor(final String itemName) {
        return model.getItemValor(itemName);
    }

    /**
     * Gets the type of a specific item.
     *
     * @param itemName The name of the item.
     * @return The type of the item.
     */
    @Override
    public ItemType getItemType(final String itemName) {
        return model.getItemType(itemName);
    }

    /**
     * Gets the boosted attribute name of a specific item.
     *
     * @param itemName The name of the item.
     * @return The boosted attribute name of the item.
     */
    @Override
    public String getItemBoostedAttributeName(final String itemName) {
        return model.getItemBoostedAttributeName(itemName);
    }

    /**
     * Gets the duration of a specific item.
     *
     * @param itemName The name of the item.
     * @return The duration of the item.
     */
    @Override
    public int getItemDuration(final String itemName) {
        return model.getItemDuration(itemName);
    }

    /**
     * Uses the specified item.
     *
     * @param itemName The name of the item to use.
     * @return True if the item is used successfully, false otherwise.
     */
    @Override
    public boolean useItem(final String itemName) {
        return model.useItem(itemName);
    }
}
