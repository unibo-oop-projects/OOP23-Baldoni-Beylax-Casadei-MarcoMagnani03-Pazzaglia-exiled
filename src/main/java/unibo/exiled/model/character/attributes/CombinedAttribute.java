package unibo.exiled.model.character.attributes;

public interface CombinedAttribute extends AdditiveAttribute, MultiplierAttribute{
    /**
     * Calculates the value * modifier in a combined attribute.
     * @return A double consisting of the multiplication between the value and the modifier.
     */
    double getEvaluated();
}
