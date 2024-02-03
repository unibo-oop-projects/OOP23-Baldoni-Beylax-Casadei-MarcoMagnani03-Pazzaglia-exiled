package unibo.exiled.model.item;

/**
 * This class represents a item that can't be used by the player, for example the crystals
 */
public class UnUsableItem extends ItemBase {


    public UnUsableItem(String name, String description) {
        super(name, description,ItemType.RESOURCE);
    }
}
