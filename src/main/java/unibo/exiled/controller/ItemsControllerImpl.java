package unibo.exiled.controller;

import java.util.Map;
import java.util.Objects;

import unibo.exiled.model.item.ItemType;
import unibo.exiled.model.item.ItemsModel;

/**
 * Implementation of the ItemsController interface.
 */
public final class ItemsControllerImpl implements ItemsController {

    private final ItemsModel model;

    /**
     * Constructor for the ItemsControllerImpl.
     *
     * @param model The game model to manage the game.
     */
    public ItemsControllerImpl(final ItemsModel model) {
        this.model = Objects.requireNonNull(model);
    }

    @Override
    public Map<String, Integer> getItems() {
        return model.getItems();
    }

    @Override
    public String getItemDescription(final String itemName) {
        return model.getItemDescription(itemName);
    }

    @Override
    public double getItemValor(final String itemName) {
        return model.getItemValor(itemName);
    }

    @Override
    public ItemType getItemType(final String itemName) {
        return model.getItemType(itemName);
    }

    @Override
    public String getItemBoostedAttributeName(final String itemName) {
        return model.getItemBoostedAttributeName(itemName);
    }

    @Override
    public boolean useItem(final String itemName) {
        return model.useItem(itemName);
    }
}
