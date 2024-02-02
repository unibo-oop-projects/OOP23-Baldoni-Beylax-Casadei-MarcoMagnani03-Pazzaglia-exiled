package unibo.exiled.model.character.attributes;

public record MultiplierAttributeImpl(double modifier) implements MultiplierAttribute {
    @Override
    public boolean isModifier() {
        return true;
    }

    @Override
    public boolean isValue() {
        return false;
    }
}
