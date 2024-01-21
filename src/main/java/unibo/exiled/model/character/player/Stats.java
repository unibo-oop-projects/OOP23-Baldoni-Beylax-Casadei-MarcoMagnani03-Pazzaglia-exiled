package unibo.exiled.model.character.player;

public interface Stats {

    /**
     * Set the starting value of the stats.
     * @param value is the default value to set.
     */
    void setDefaultValue(final double value);

    /**
     * Increase the stats value. E.g. increase the attack stats.
     * @param value is the value to increase.
     */
    void increase(final double value);
    
    /**
     * Decrease the stats value. E.g. decrease the defense stats.
     * @param value is the value to decrease.
     */
    void decrease(final double value);
}
