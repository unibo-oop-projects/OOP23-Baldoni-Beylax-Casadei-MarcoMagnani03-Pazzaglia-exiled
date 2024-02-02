package unibo.exiled.model.character.attributes;

public record AdditiveAttributeImpl(double value) implements AdditiveAttribute {

    @Override
    public boolean isModifier() {
        return false;
    }

    @Override
    public boolean isValue() {
        return true;
    }
}
