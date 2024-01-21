package unibo.exiled.model.character.player;

public interface Stats {
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
