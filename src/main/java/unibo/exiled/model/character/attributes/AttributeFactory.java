package unibo.exiled.model.character.attributes;

import java.util.Map;

/**
 * A factory of default attributes for characters.
 */
public interface AttributeFactory {
    /**
     * Creates the attributes of the player.<br>
     * <ul>
     *    <li>
     *         Health and HealthCap: 100
     *     </li>
     *     <li>
     *          All modifiers: 1
     *      </li>
     *  </ul>
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createPlayerAttributes();

    /**
     * Creates the attributes of the goblin, the weakest enemy.<br>
     * <ul>
     *    <li>
     *        Health and HealthCap: 10
     *     </li>
     *     <li>
     *         All modifiers: 1
     *     </li>
     * </ul>
     *  @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createGoblinAttributes();

    /**
     * Creates the attributes of the brutus.<br>
     * <ul>
     *      <li>
     *          Health and HealthCap: 20
     *      </li>
     *      <li>
     *          All modifiers: 1
     *      </li>
     *  </ul>
     *  @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createBrutusAttributes();

    /**
     * Creates the attributes of the Boss.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createBossAttributes();

    /**
     * Creates tje attributes for the Whirler.
     *
     * @return A map of attributes with the overly specified values.
     */
    Map<AttributeIdentifier, Attribute> createWhirlerAttributes();
}
