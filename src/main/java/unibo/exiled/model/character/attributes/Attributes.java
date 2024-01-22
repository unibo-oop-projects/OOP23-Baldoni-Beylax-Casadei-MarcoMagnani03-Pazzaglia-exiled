package unibo.exiled.model.character.attributes;

import java.util.Map;

/**
 * Contains the stats of a character, such as health, attack, defense and more...
 */
public interface Attributes {
    /**
     * Gets the attribute set of the character.
     * @return A map containing the type of stat and the stat value.
     */
    Map<AttributeType, Stat> getAttributes();

    /**
     * Gets the stat of the selected attribute type.
     * @param type The type of the stat to get.
     * @return The selected stat.
     */
    Stat getAttributeOfType(final AttributeType type);

    /**
     * Adds an attribute to the attribute set.
     * @param type The type of the new stat
     * @param stat The stat with the values already inserted.
     */
    void addAttribute(final AttributeType type, final Stat stat);

    /**
     * Removes the attribute of the selected type.
     * @param type The type of the stat to remove.
     */
    void removeAttribute(final AttributeType type);
}
