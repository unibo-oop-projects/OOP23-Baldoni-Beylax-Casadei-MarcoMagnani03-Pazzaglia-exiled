package unibo.exiled.model.character;

import unibo.exiled.model.character.player.PlayerAttribute.AttributeType;

public interface Attributes {
    /**
     * Gets the health value of the current character.
     * @return The health value of the current character.
     */
    double getHealth();

    /**
     * Gets the attack value of the current character.
     * @return The attack value of the current character.
     */
    double getAttack();

    /**
     * Gets the multiplicative constant to be applied to the attack stat.
     * @return A double consisting in the multiplicative value.
     */
    double getAttackModifier();

    /**
     * Gets the defense value of the current character.
     * @return The defense value of the current character.
     */
    double getDefense();

    /**
     * Gets the multiplicative constant to be applied to the defense stat.
     * @return A double consisting in the multiplicative value.
     */
    double getDefenseModifier();


    /**
     * Increase the stats value of a particular attribute. E.g. increase the attack stats.
     * @param attr is the attribute to increase.
     */
    void increase(final AttributeType attr, final double value);

    /**
     * Decrease the stats value of a particular attribute. E.g. decrease the defense stats.
     * @param attr is the attribute to decrease.
     */
    void decrease(final AttributeType attr, final double value);
}
