package unibo.exiled.model.character.attributes;

public class CombinedAttributeImpl implements CombinedAttribute{

    /**
     * The base value contained in the attribute.
     */
    private final AdditiveAttributeImpl value;
    /**
     * The modifier of the combined attribute.
     */
    private final MultiplierAttributeImpl modifier;

    public CombinedAttributeImpl(final double value, final double modifier){
        this.value = new AdditiveAttributeImpl(value);
        this.modifier = new MultiplierAttributeImpl(modifier);
    }

    @Override
    public double value(){
        return this.value.value();
    }

    @Override
    public double modifier(){
        return this.modifier.modifier();
    }

    @Override
    public double getEvaluated(){
        return this.value.value() * this.modifier.modifier();
    }

    @Override
    public boolean isModifier() {
        return true;
    }

    @Override
    public boolean isValue() {
        return true;
    }
}
