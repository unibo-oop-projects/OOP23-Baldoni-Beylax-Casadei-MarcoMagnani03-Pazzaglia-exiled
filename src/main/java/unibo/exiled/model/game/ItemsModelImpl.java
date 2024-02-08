package unibo.exiled.model.game;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.HealingItem;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemType;
import unibo.exiled.model.item.PowerUpItem;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.Moves;

public class ItemsModelImpl implements ItemsModel {
    private final GameModel model;


    public ItemsModelImpl(GameModel model){
        this.model = model;
    }

    @Override
    public Set<MagicMove> getMagicMoves() {
        return Moves.getAllMagicMoves();
    }

    @Override
    public Map<String, Integer> getItems() {
        return this.model.getCharacterModel().getPlayer().get().getInventory().getItems()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue));
    }

    private Item getItem(final String itemName) {
        // TODO: Lacks isPresent control.
        return this.model.getCharacterModel().getPlayer().get().getInventory().getItems()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(itemName))
                .findFirst().get().getKey();
    }

    @Override
    public String getItemDescription(final String itemName) {
        return getItem(itemName).getDescription();
    }

    @Override
    public double getItemValor(final String itemName) {
        final Item selectedItem = getItem(itemName);
        if (selectedItem instanceof UsableItem) {
            return ((UsableItem) selectedItem).getAmount();
        }
        return 0;
    }

    @Override
    public ItemType getItemType(final String itemName) {
        return getItem(itemName).getType();
    }

    @Override
    public String getItemBoostedAttributeName(final String itemName) {
        final Item selectedItem = getItem(itemName);
        if (selectedItem instanceof PowerUpItem) {
            return ((PowerUpItem) selectedItem).getBoostedAttribute().getName();
        } else if (selectedItem instanceof HealingItem) {
            return AttributeIdentifier.HEALTH.getName();
        }
        return "";
    }

    @Override
    public int getItemDuration(final String itemName) {
        final Item selectedItem = getItem(itemName);
        if (selectedItem instanceof PowerUpItem) {
            return ((PowerUpItem) selectedItem).getDuration();
        }
        return 0;
    }

    @Override
    public boolean useItem(final String item) {
        final Item selectedItem = getItem(item);
        if (selectedItem instanceof UsableItem convertedItem) {
            this.model.getCharacterModel().getPlayer().get().useItem(convertedItem);
            return true;
        }
        return false;
    }

}
