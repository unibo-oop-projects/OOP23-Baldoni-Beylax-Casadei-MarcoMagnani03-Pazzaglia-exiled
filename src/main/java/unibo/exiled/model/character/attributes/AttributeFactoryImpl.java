package unibo.exiled.model.character.attributes;

import unibo.exiled.config.Constants;

/**
 * Contains some methods to create some basic attribute combinations.
 */
public class AttributeFactoryImpl implements AttributeFactory {

    @Override
    public Attributes basicPlayerAttributes() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        final Attributes attributes = new AttributesImpl();
        attributes.addAttribute(AttributeType.HEALTH,new StatImpl(Constants.getConstantOf("PLAYER_DEFAULT_HEALTH"),1));
        attributes.addAttribute(AttributeType.ATTACK,new StatImpl(Constants.getConstantOf("PLAYER_DEFAULT_ATTACK"),1));
        attributes.addAttribute(AttributeType.DEFENSE,new StatImpl(Constants.getConstantOf("PLAYER_DEFAULT_DEFENSE"),1));
        attributes.addAttribute(AttributeType.HEALTHCAP,new StatImpl(Constants.getConstantOf("PLAYER_DEFAULT_HEALTH_CAP"),1));
        return attributes;
    }

    @Override
    public Attributes basicEnemyAttributes() {
        return null;
    }
}
