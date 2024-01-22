package unibo.exiled.model.character.attributes;

public enum AttributeType {
    HEALTH("Health"),
    DEFENSE("Defense"),
    ATTACK("Attack"),
    HEALTHCAP("Health Cap.");

    private final String localization;

    AttributeType(final String name){
        this.localization = name;
    }

    public String getLocalization(){
        return  this.localization;
    }

}
