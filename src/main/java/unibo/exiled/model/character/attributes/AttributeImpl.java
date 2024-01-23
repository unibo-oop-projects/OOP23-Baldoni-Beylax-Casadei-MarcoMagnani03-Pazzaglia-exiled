package unibo.exiled.model.character.attributes;

import java.util.Optional;

public class AttributeImpl implements Attribute{

    private Optional<Double> modifier;
    private Optional<Double> value;

    public AttributeImpl(final Optional<Double> modifier, final Optional<Double> value){
        this.modifier = modifier;
        this.value = value;
    }

    @Override
    public Optional<Double> getValue() {
        return this.value;
    }

    @Override
    public Optional<Double> getModifier() {
        return this.modifier;
    }

    @Override
    public double getEvaluated() {
        if(this.modifier.isPresent() && this.value.isPresent()){
            return this.modifier.get() * this.value.get();
        }
        else if(this.value.isPresent()){
            return this.value.get();
        }
        else{
            throw new IllegalStateException("Cannot evaluate an attribute that is a modifier, use getModifier instead");
        }
    }

    @Override
    public void setValue(final double newValue) {
        this.value = Optional.of(newValue);
    }

    @Override
    public void setModifier(double newModifier) {
        this.modifier = Optional.of(newModifier);
    }
}
