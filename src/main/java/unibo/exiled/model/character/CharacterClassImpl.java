package unibo.exiled.model.character;

import unibo.exiled.model.utilities.ElementalType;

/**
 * The implementation of the player class.
 *
 * @param elementalType The elemental type (class) of the player.
 */
public record CharacterClassImpl(ElementalType elementalType) implements CharacterClass {

    @Override
    public double getAttributeModifier() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttributeModifier'");
    }

}
