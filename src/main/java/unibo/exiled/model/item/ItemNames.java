package unibo.exiled.model.item;

/**
 * Enumeration of item names.
 */
public enum ItemNames {
    /**
     * Health Potion item name.
     */
    HEALTH_POTION("Health Potion"),

    /**
     * Herb item name.
     */
    HERB("Herb"),

    /**
     * Bandage item name.
     */
    BANDAGE("Bandage"),

    /**
     * Strength Boost item name.
     */
    STRENGTH_BOOST("Strength Boost"),

    /**
     * Defense Shield item name.
     */
    DEFENSE_SHIELD("Defense Shield"),

    /**
     * Crystal item name.
     */
    CRYSTAL("Crystal");

    private final String name;

    /**
     * Returns the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Constructor for ItemNames enum.
     *
     * @param name The name of the item.
     */
    ItemNames(final String name) {
        this.name = name;
    }
}
