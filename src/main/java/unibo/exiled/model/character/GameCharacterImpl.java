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
     * Increases a generic attribute of a specified value.
     *
     * @param id       The attribute to modify.
     * @param modifier The modifier to add.
     * @param value    The value to add.
     */
    private void increaseAttributes(final AttributeIdentifier id, final double modifier, final double value) {
        final Attribute attributeToModify = this.attributes.get(id);
        final Map<AttributeIdentifier, Attribute> modifiedAttributes = new HashMap<>(this.attributes);
        if (attributeToModify.isModifier() && attributeToModify.isValue()) {
            final CombinedAttributeImpl conv = (CombinedAttributeImpl) attributeToModify;
            modifiedAttributes.replace(id, new CombinedAttributeImpl(conv.value() + value, conv.modifier() + modifier));
        } else if (attributeToModify.isModifier()) {
            final MultiplierAttributeImpl conv = (MultiplierAttributeImpl) attributeToModify;
            modifiedAttributes.replace(id, new MultiplierAttributeImpl(conv.modifier() + modifier));
        } else {
            final AdditiveAttributeImpl conv = (AdditiveAttributeImpl) attributeToModify;
            modifiedAttributes.replace(id, new AdditiveAttributeImpl(conv.value() + value));
        }
        this.attributes = modifiedAttributes;
    }

    @Override
    public final void increaseAttributeModifier(final AttributeIdentifier id, final double modifier) {
        this.increaseAttributes(id, modifier, 0);
    }

    @Override
    public final void increaseAttributeValue(final AttributeIdentifier id, final double value) {
        this.increaseAttributes(id, 0, value);
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
