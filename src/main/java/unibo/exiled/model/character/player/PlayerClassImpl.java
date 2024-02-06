package unibo.exiled.model.character.player;

import unibo.exiled.model.utilities.ElementalType;

/**
 * The implementation of the player class.
 *
 * @param elementalType The elemental type (class) of the player.
 */
public record PlayerClassImpl(ElementalType elementalType) implements PlayerClass {

    @Override
    public double getAttributeModifier() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttributeModifier'");
    }

}
