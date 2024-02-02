package unibo.exiled.model.character;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.Attribute;
import unibo.exiled.model.character.attributes.MultiplierAttributeImpl;
import unibo.exiled.model.character.attributes.CombinedAttributeImpl;
import unibo.exiled.model.character.attributes.AdditiveAttributeImpl;
import unibo.exiled.model.character.attributes.CombinedAttribute;
import unibo.exiled.model.utilities.Position;

import java.util.Collections;
import java.util.Map;

/**
 * The implementation of a generic character.
 */
public abstract class CharacterImpl implements Character {
    /**
     * An association between the identifier of the attribute and its values.
     */
    private final Map<AttributeIdentifier, Attribute> attributes;
    private final String name;
    private Position position;

    /**
     * The constructor of the Character.
     *
     * @param attributes The attributes of the character to build.
     * @param name       The name of the character.
     */
    protected CharacterImpl(final String name, final Map<AttributeIdentifier, Attribute> attributes) {
        this.attributes = attributes;
        this.name = name;
    }

    /**
     * Sets the position of the current character.
     * @param position The new position of the character.
     */
    @Override
    public final void move(final Position position) {
        this.position = position;
    }

    /**
     * Gets the position of the character.
     * @return The position of the character.
     */
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
        if (attributeToModify.isModifier() && attributeToModify.isValue()) {
            final CombinedAttributeImpl conv = (CombinedAttributeImpl) attributeToModify;
            this.attributes.replace(id, new CombinedAttributeImpl(conv.value() + value, conv.modifier() + modifier));
        } else if (attributeToModify.isModifier()) {
            final MultiplierAttributeImpl conv = (MultiplierAttributeImpl) attributeToModify;
            this.attributes.replace(id, new MultiplierAttributeImpl(conv.modifier() + modifier));
        } else {
            final AdditiveAttributeImpl conv = (AdditiveAttributeImpl) attributeToModify;
            this.attributes.replace(id, new AdditiveAttributeImpl(conv.value() + value));
        }
    }

    /**
     * Increases a modifier of the specified value.
     *
     * @param id       The modifier to increase.
     * @param modifier The modifier to add to the current value.
     */
    @Override
    public final void increaseAttributeModifier(final AttributeIdentifier id, final double modifier) {
        this.increaseAttributes(id, modifier, 0);
    }

    /**
     * Increases the selected attribute of the specified value.
     *
     * @param id    The attribute to increase.
     * @param value The value to add to the attribute value.
     */
    @Override
    public final void increaseAttributeValue(final AttributeIdentifier id, final double value) {
        this.increaseAttributes(id, 0, value);
    }

    /**
     * Gets the health of the character.
     *
     * @return A double value containing the evaluated attribute of the health.
     */
    @Override
    public double getHealth() {
        return ((CombinedAttribute) attributes.get(AttributeIdentifier.HEALTH)).getEvaluated();
    }
}
