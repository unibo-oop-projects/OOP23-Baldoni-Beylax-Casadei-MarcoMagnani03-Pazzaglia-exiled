package unibo.exiled.model.item;

import java.util.Objects;

import unibo.exiled.model.utilities.ItemType;

/**
 * This abstract class implements the Item interface by providing
 * a common core implementation for items in the game.
 * Objects can extend this class to inherit this base implementation and add
 * specific features.
 * The class has a constructor that requires the name and description of the object the type it can
 * be Healing (curative), PowerUp (strengthening) or Standard (unusable object e.g. crystals), and if it is
 * a usable item the value of what it upgrades or heals
 * Examples of items that can extend this class include healing items,
 * power-up items etc.
 */
public abstract class ItemBase implements Item{
    private final String name;
    private final String description;
    private final ItemType itemType;

    public ItemBase(String name,String description,ItemType itemType){
        this.name = name;
        this.description = description;
        this.itemType = itemType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
    @Override
    public ItemType getType(){
        return this.itemType;
    }
}
