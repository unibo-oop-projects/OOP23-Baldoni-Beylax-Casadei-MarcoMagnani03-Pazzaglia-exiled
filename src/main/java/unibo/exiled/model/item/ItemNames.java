package unibo.exiled.model.item;

public enum ItemNames {
    HEALTH_POTION("Health Potion"),
    HERB("Herb"),
    BANDAGE("Bandage"),
    STRENGTH_BOOST("Strength Boost"),
    DEFENSE_SHIELD("Defense Shield"),
    CRYSTAL("Crystal");

    private final String name;

    public String getName(){
        return this.name;
    }

    ItemNames(final String name){
        this.name = name;
    }
}
