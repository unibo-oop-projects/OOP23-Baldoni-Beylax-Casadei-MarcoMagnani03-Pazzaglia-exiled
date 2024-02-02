package unibo.exiled.model.character.attributes;

public interface MultiplierAttribute extends Attribute{
    /**
     * Returns the modifier of the attribute.
     * @return A double modifier that needs to be multiplied to something.
     */
    double modifier();
}
