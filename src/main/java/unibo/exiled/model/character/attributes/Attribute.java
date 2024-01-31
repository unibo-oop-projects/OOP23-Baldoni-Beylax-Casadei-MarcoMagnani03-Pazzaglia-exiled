package unibo.exiled.model.character.attributes;

import java.util.Optional;

/**
 * Represents an attribute of a character in the game.
 * Attributes have a base value, a modifier, and an evaluated value.
 */
public interface Attribute {

    /**
     * Gets the base value of the attribute.
     * @return an optional containing the base value, or empty if not set.
     */
    Optional<Double> getValue();

    /**
     * Gets the modifier applied to the attribute.
     * @return an optional containing the modifier, or empty if not set.
     */
    Optional<Double> getModifier();

    /**
     * Gets the evaluated value of the attribute, which is the product of the base value and the modifier.
     * @return the evaluated value of the attribute.
     */
    double getEvaluated();

    /**
     * Sets the base value of the attribute to the specified new value.
     * @param newValue the new base value of the attribute.
     */
    void setValue(final double newValue);

    /**
     * Sets the modifier of the attribute to the specified new modifier.
     * @param newModifier the new modifier of the attribute.
     */
    void setModifier(final double newModifier);
}
