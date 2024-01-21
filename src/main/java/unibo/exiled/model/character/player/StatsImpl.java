package unibo.exiled.model.character.player;

public class StatsImpl implements Stats {

    private double value;
    private int multiplier;

    @Override
    public void setDefaultValue(double value) {
        this.value = value;
    }

    @Override
    public void increase(final double value) {
        this.value += value * this.multiplier;
    }

    @Override
    public void decrease(final double value) {
        this.value -= value * this.multiplier;
    }
    
}
