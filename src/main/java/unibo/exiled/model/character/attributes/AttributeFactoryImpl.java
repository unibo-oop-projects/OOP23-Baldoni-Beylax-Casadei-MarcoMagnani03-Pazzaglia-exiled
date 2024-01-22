package unibo.exiled.model.character.attributes;

/**
 * Contains some methods to create some basic attribute combinations.
 */
public class AttributeFactoryImpl {
    /**
     * Basic attributes used by the player.
     * @return A set of default attributes already filled with the correct stats.
     */
    public static final Attributes basicAttributes(){
        Attributes attributes = new AttributesImpl();
        attributes.addAttribute(AttributeType.HEALTH, new StatImpl(100,1));
        attributes.addAttribute(AttributeType.DEFENSE,new StatImpl(100,1));
        attributes.addAttribute(AttributeType.ATTACK,new StatImpl(1,1));
        attributes.addAttribute(AttributeType.HEALTHCAP, new StatImpl(200,1));
        return attributes;
    }
}
