package unibo.exiled.model.item;
/**
 * This abstract class implements the {@code Item} interface by providing
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

    public ItemBase(String name,String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    
    
}
