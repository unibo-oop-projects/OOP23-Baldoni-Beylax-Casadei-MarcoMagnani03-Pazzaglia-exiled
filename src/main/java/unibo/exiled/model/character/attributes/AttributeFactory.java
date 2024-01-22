package unibo.exiled.model.character.attributes;

/**
 * Contains the methods to fill the stats with default characters based on the defaults in config.yml file.
 */
public interface AttributeFactory {
    /**
     * The attributes given to a default player.
     * @return The attributes already filled with the constants found in the config file.
     */
    Attributes basicPlayerAttributes();
    Attributes basicEnemyAttributes();
}
