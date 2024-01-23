package unibo.exiled.model.character.attributes;

import java.util.Optional;

public interface Attribute {
    Optional<Double> getValue();
    Optional<Double> getModifier();
    double getEvaluated();
    void setValue(final double newValue);
    void setModifier(final double newModifier);
}
