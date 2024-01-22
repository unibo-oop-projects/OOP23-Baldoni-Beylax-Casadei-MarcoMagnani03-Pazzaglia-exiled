package unibo.exiled.model.character.attributes;

public interface Stat {
    /**
     * Gets the value of the stat.
     * @return A double representing the stat value.
     */
    double getValue();

    /**
     * Gets the modifier of the stat.
     * @return A double representing the stat modifier.
     */
    double getModifier();

    /**
     * Gets the multiplication of the modifier and the value.
     * @return A double representing the modifier * value.
     */
    double getEvaluated();

    /**
     * Replaces the value of the modifier.
     * @param modifier The new modifier that is going to replace the old one.
     */
    void setModifier(final double modifier);

    /**
     * Replaces the value of the value.
     * @param newValue The new value that is going to replace the old one.
     */
    void setValue(final double newValue);
}
