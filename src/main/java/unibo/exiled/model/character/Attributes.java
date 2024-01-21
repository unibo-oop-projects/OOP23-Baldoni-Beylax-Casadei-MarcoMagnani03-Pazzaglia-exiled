package unibo.exiled.model.character;

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
}
