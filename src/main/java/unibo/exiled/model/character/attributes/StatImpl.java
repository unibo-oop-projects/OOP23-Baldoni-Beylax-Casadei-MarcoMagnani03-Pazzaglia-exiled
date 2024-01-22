package unibo.exiled.model.character.attributes;

import unibo.exiled.model.character.attributes.Stat;

public class StatImpl implements Stat {

    private double value;
    private double modifier;

    public StatImpl(final double value, final double modifier){
        this.value = value;
        this.modifier = modifier;
    }

    @Override
    public double getValue(){
        return this.value;
    }

    @Override
    public double getModifier(){
        return this.modifier;
    }

    @Override
    public double getEvaluated(){
        return this.modifier * this.value;
    }

    @Override
    public void setModifier(double modifier) {
        this.modifier = modifier;
    }

    @Override
    public void setValue(double newValue) {
        this.value = newValue;
    }
}
