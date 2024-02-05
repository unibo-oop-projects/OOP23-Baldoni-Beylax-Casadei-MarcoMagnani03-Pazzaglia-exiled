package unibo.exiled.model.character;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.MultiplierAttributeImpl;
import unibo.exiled.model.character.attributes.CombinedAttributeImpl;
import unibo.exiled.model.character.attributes.AdditiveAttributeImpl;
import unibo.exiled.model.character.attributes.CombinedAttribute;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The implementation of a generic character.
 */
public abstract class GameCharacterImpl implements GameCharacter {
    private final String name;
    private boolean isMoving = true;
    /**
     * An association between the identifier of the attribute and its values.
     */
    private Map<AttributeIdentifier, Attribute> attributes;
    private Position position;
    private Direction lastDirection = Direction.SOUTH;

    /**
     * The constructor of the GameCharacter.
     *
     * @param attributes The attributes of the character to build.
     * @param name       The name of the character.
     */
    protected GameCharacterImpl(final String name, final Map<AttributeIdentifier, Attribute> attributes) {
        this.attributes = attributes;
        this.name = name;
    }

    @Override
    public final Direction getLastDirection() {
        return this.lastDirection;
    }

    @Override
    public final void setLastDirection(final Direction direction) {
        this.lastDirection = direction;
    }

    @Override
    public final void move(final Position position) {
        this.position = position;
    }

    @Override
    public final Position getPosition() {
        return this.position;
    }

    @Override
    public final Map<AttributeIdentifier, Attribute> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    /**
     * Modifies a generic attribute by adding or subtracting a value and/or a modifier.
     *
     * @param id       The attribute to modify.
     * @param modifier The modifier to add or subtract.
     * @param value    The value to add or subtract.
     * @param increase True for increase, false for decrease.
     */
    private void modifyAttribute(final AttributeIdentifier id, final double modifier,
    final double value, final boolean increase) {
        final Attribute attributeToModify = this.attributes.get(id);
        final Map<AttributeIdentifier, Attribute> modifiedAttributes = new HashMap<>(this.attributes);

        if (attributeToModify instanceof CombinedAttribute) {
            final CombinedAttribute conv = (CombinedAttribute) attributeToModify;
            modifiedAttributes.replace(id, new CombinedAttributeImpl(
                    increase ? conv.value() + value : conv.value() - value,
                    increase ? conv.modifier() + modifier : conv.modifier() - modifier));
        } else if (attributeToModify instanceof MultiplierAttributeImpl) {
            final MultiplierAttributeImpl conv = (MultiplierAttributeImpl) attributeToModify;
            modifiedAttributes.replace(id, new MultiplierAttributeImpl(
                    increase ? conv.modifier() + modifier : conv.modifier() - modifier));
        } else if (attributeToModify instanceof AdditiveAttributeImpl) {
            final AdditiveAttributeImpl conv = (AdditiveAttributeImpl) attributeToModify;
            modifiedAttributes.replace(id, new AdditiveAttributeImpl(
                    increase ? conv.value() + value : conv.value() - value));
        }

        this.attributes = modifiedAttributes;
    }

    @Override
    public final void increaseAttributeModifier(final AttributeIdentifier id, final double modifier) {
        modifyAttribute(id, modifier, 0, true);
    }

    @Override
    public final void increaseAttributeValue(final AttributeIdentifier id, final double value) {
        modifyAttribute(id, 0, value, true);
    }

    @Override
    public final void decreaseAttributeModifier(final AttributeIdentifier id, final double modifier) {
        modifyAttribute(id, modifier, 0, false);
    }

    @Override
    public final void decreaseAttributeValue(final AttributeIdentifier id, final double value) {
        modifyAttribute(id, 0, value, false);
    }

    @Override
    public final double getHealth() {
        return ((CombinedAttribute) attributes.get(AttributeIdentifier.HEALTH)).getEvaluated();
    }

    @Override
    public final boolean spriteIsMoving() {
        this.isMoving = !isMoving;
        return !this.isMoving;
    }
}
