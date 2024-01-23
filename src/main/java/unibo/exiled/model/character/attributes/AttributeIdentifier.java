package unibo.exiled.model.character.attributes;

public enum AttributeIdentifier {
    ATTACK("Attack"),
    DEFENSE("Defense"),
    HEALTH("Health"),
    HEALTHCAP("Health Cap");

    private final String name;

    AttributeIdentifier(final String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
