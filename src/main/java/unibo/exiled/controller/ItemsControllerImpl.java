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

    @Override
    public Map<String, Integer> getItems() {
        return model.getItemsModel().getItems();
    }

    @Override
    public String getItemDescription(final String itemName) {
        return model.getItemsModel().getItemDescription(itemName);
    }

    @Override
    public double getItemValor(final String itemName) {
        return model.getItemsModel().getItemValor(itemName);
    }

    @Override
    public ItemType getItemType(final String itemName) {
        return model.getItemsModel().getItemType(itemName);
    }

    @Override
    public String getItemBoostedAttributeName(final String itemName) {
        return model.getItemsModel().getItemBoostedAttributeName(itemName);
    }

    @Override
    public int getItemDuration(final String itemName) {
        return model.getItemsModel().getItemDuration(itemName);
    }

    @Override
    public boolean useItem(final String itemName) {
        return model.getItemsModel().useItem(itemName);
    }
}
